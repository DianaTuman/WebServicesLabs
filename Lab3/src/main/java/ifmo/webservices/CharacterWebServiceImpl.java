package ifmo.webservices;

import ifmo.webservices.errors.*;

import javax.inject.Singleton;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(name = "CharacterWebService", serviceName = "CharacterService")
@Singleton
public class CharacterWebServiceImpl implements CharacterWebService {
    private final int MAX_REQUESTS_COUNT = 20;
    private int requestsCount = 0;

    private synchronized void decreaseRequests() {
        if (requestsCount > 0) {
            requestsCount--;
        }
    }

    private synchronized void increaseRequests() throws ThrottlingException {
        if (requestsCount == MAX_REQUESTS_COUNT) {
            throw new ThrottlingException("Maximum requests count of " + MAX_REQUESTS_COUNT + " exceeded",
                    CharacterServiceFault.defaultInstance());
        }
        requestsCount++;
    }


    @WebMethod(operationName = "getAllCharacters")
    public List<Character> getAllCharacters() throws DatabaseException, ThrottlingException {
        increaseRequests();
        OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
        try {
            List<Character> result = dao.getAllCharacters();
            decreaseRequests();
            return result;
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
            decreaseRequests();
            throw new DatabaseException(e.getMessage(), CharacterServiceFault.defaultInstance());
        }
    }

    @WebMethod(operationName = "getCharacters")
    public List<Character> getCharacters(@WebParam(name = "conditions") List<CharacterFieldValue> conditions)
            throws DatabaseException, ThrottlingException {
        increaseRequests();
        OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
        try {
            List<Character> result = dao.getCharactersByFields(conditions);
            decreaseRequests();
            return result;
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
            decreaseRequests();
            throw new DatabaseException(e.getMessage(), CharacterServiceFault.defaultInstance());
        }
    }

    @WebMethod(operationName = "addCharacter")
    public int addCharacter(@WebParam(name = "character") Character character)
            throws InvalidNameException, InvalidHeroClassException,
            InvalidRaceException,
            InvalidHpException,
            InvalidExlevelException, DatabaseException, ThrottlingException {
        increaseRequests();
        checkName(character.getName());
        checkHeroClass(character.getHeroClass());
        checkRace(character.getRace());
        checkExlevel(character.getExlevel());
        checkHp(character.getHp());

        OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
        try {
            int result = dao.addCharacter(character);
            decreaseRequests();
            return result;
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
            decreaseRequests();
            throw new DatabaseException(e.getMessage(), CharacterServiceFault.defaultInstance());
        }
    }

    @WebMethod(operationName = "modifyCharacter")
    public boolean modifyCharacter(@WebParam(name = "id") int id, @WebParam(name = "newValues") List<CharacterFieldValue> newValues)
            throws InvalidNameException,
            InvalidRaceException,
            InvalidHpException,
            InvalidExlevelException,
            CharacterNotFoundException, DatabaseException, ThrottlingException {
        increaseRequests();
        for (CharacterFieldValue fieldValue : newValues) {
            switch (fieldValue.getField()) {
                case NAME:
                    checkName(fieldValue.getValue().toString());
                    break;
                case HEROCLASS:
                    checkName(fieldValue.getValue().toString());
                    break;
                case RACE:
                    checkRace(fieldValue.getValue().toString());
                    break;
                case EXLEVEL:
                    checkExlevel(fieldValue.getValue().toString());
                    break;
                case HP:
                    checkHp(fieldValue.getValue().toString());
                    break;
            }
        }

        OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
        try {
            checkExists(dao, id);
            boolean result = dao.modifyCharacter(id, newValues);
            decreaseRequests();
            return result;
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
            decreaseRequests();
            throw new DatabaseException(e.getMessage(), CharacterServiceFault.defaultInstance());
        }
    }

    @WebMethod(operationName = "deleteCharacter")
    public boolean deleteCharacter(@WebParam(name = "id") int id) throws CharacterNotFoundException, DatabaseException, ThrottlingException {
        increaseRequests();
        OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
        try {
            checkExists(dao, id);
            boolean result = dao.deleteCharacter(id);
            decreaseRequests();
            return result;
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
            decreaseRequests();
            throw new DatabaseException(e.getMessage(), CharacterServiceFault.defaultInstance());
        }
    }

    protected void checkExists(OracleSQLDAO dao, int id) throws CharacterNotFoundException, SQLException {
        if (dao.getCharactersByFields(new ArrayList<CharacterFieldValue>() {{
            add(new CharacterFieldValue(Field.ID, id));
        }}).size() == 0) {
            throw new CharacterNotFoundException("Character with such id is not found",
                    CharacterServiceFault.defaultInstance());
        }
    }

    protected void checkName(String name) throws InvalidNameException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException("Character name is not specified",
                    CharacterServiceFault.defaultInstance());
        }
    }

    protected void checkHeroClass(String heroClass) throws InvalidHeroClassException {
        if (heroClass == null || heroClass.trim().isEmpty()) {
            throw new InvalidHeroClassException("Character heroClass is not specified",
                    CharacterServiceFault.defaultInstance());
        }
    }

    protected void checkRace(String race) throws InvalidRaceException {
        if (race == null || race.trim().isEmpty()) {
            throw new InvalidRaceException("Character race is not specified",
                    CharacterServiceFault.defaultInstance());
        }
    }

    protected void checkHp(String hp) throws InvalidHpException {
        int hpInt = -1;
        try {
            hpInt = Integer.parseInt(hp);
        } catch (NumberFormatException e) {
            throw new InvalidHpException("HP should be number",
                    CharacterServiceFault.defaultInstance());
        }
        checkHp(hpInt);
    }

    protected void checkExlevel(String exlevel) throws InvalidExlevelException {
        int exlevelInt = -1;
        try {
            exlevelInt = Integer.parseInt(exlevel);
        } catch (NumberFormatException e) {
            throw new InvalidExlevelException("Exlevels should be number",
                    CharacterServiceFault.defaultInstance());
        }
        checkExlevel(exlevelInt);
    }

    protected void checkHp(int hp) throws InvalidHpException {
        if (hp <= 0) {
            throw new InvalidHpException("HP should be greater than zero",
                    CharacterServiceFault.defaultInstance());
        }
    }

    protected void checkExlevel(int exlevel) throws InvalidExlevelException {
        if (exlevel <= 0) {
            throw new InvalidExlevelException("Exlevel should be greater than zero",
                    CharacterServiceFault.defaultInstance());
        }
    }
}