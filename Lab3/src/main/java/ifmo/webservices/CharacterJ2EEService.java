package ifmo.webservices;

import ifmo.webservices.errors.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(name = "CharacterWebService", serviceName = "CharacterService")
public class CharacterJ2EEService implements CharacterWebService {

    @WebMethod(operationName = "getAllCharacters")
    public List<Character> getAllCharacters() throws DatabaseException {
        OracleSQLDAO dao = new OracleSQLDAO(getConnection());
        try {
            return dao.getAllCharacters();
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException(e.getMessage(), CharacterServiceFault.defaultInstance());
        }
    }

    @WebMethod(operationName = "getCharacters")
    public List<Character> getCharacters(@WebParam(name = "conditions") List<CharacterFieldValue> conditions)
            throws DatabaseException {
        OracleSQLDAO dao = new OracleSQLDAO(getConnection());
        try {
            return dao.getCharactersByFields(conditions);
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException(e.getMessage(), CharacterServiceFault.defaultInstance());
        }
    }

    @WebMethod(operationName = "addCharacter")
    public int addCharacter(@WebParam(name = "character") Character character)
            throws InvalidNameException,
            InvalidRaceException,
            InvalidHpException,
            InvalidExlevelException, DatabaseException {

        checkName(character.getName());
        checkHeroClass(character.getHeroClass());
        checkRace(character.getRace());
        checkExlevel(character.getExlevel());
        checkHp(character.getHp());

        OracleSQLDAO dao = new OracleSQLDAO(getConnection());
        try {
            return dao.addCharacter(character);
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException(e.getMessage(), CharacterServiceFault.defaultInstance());
        }
    }

    @WebMethod(operationName = "modifyCharacter")
    public boolean modifyCharacter(@WebParam(name = "id") int id, @WebParam(name = "newValues") List<CharacterFieldValue> newValues)
            throws InvalidNameException,
            InvalidRaceException,
            InvalidHpException,
            InvalidExlevelException,
            CharacterNotFoundException, DatabaseException {

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

        OracleSQLDAO dao = new OracleSQLDAO(getConnection());
        try {
            checkExists(dao, id);
            return dao.modifyCharacter(id, newValues);
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException(e.getMessage(), CharacterServiceFault.defaultInstance());
        }
    }

    @WebMethod(operationName = "deleteCharacter")
    public boolean deleteCharacter(@WebParam(name = "id") int id) throws CharacterNotFoundException, DatabaseException {
        OracleSQLDAO dao = new OracleSQLDAO(getConnection());
        try {
            checkExists(dao, id);
            return dao.deleteCharacter(id);
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
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

    protected void checkHeroClass(String heroClass) throws InvalidNameException {
        if (heroClass == null || heroClass.trim().isEmpty()) {
            throw new InvalidNameException("Character heroClass is not specified",
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

    private Connection getConnection() {
        Connection result = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("ifmo-oracle");
            result = dataSource.getConnection();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(CharacterJ2EEService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}