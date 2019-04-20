package ifmo.webservices;

import ifmo.webservices.errors.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/characters")
@Produces({MediaType.APPLICATION_JSON})
public class CharacterResource {
    @GET
    public List<Character> getCharacters(
            @QueryParam("id") int id,
            @QueryParam("name") String name,
            @QueryParam("heroClass") String heroclass,
            @QueryParam("race") String race,
            @QueryParam("exlevel") int exlevel,
            @QueryParam("hp") int hp,
            @QueryParam("all") boolean all) throws DatabaseException {

        OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
        List<CharacterFieldValue> conditions = new ArrayList<>();
        try {
            if (all != false) return dao.getAllCharacters();
            if (id != 0) conditions.add(new CharacterFieldValue(Field.ID, id));
            if (name != null) conditions.add(new CharacterFieldValue(Field.NAME, name));
            if (heroclass != null) conditions.add(new CharacterFieldValue(Field.HEROCLASS, heroclass));
            if (race != null) conditions.add(new CharacterFieldValue(Field.RACE, race));
            if (exlevel != 0) conditions.add(new CharacterFieldValue(Field.EXLEVEL, exlevel));
            if (hp != 0) conditions.add(new CharacterFieldValue(Field.HP, hp));
            return dao.getCharactersByFields(conditions);
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException(e.getMessage());
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public int addCharacter(Character character) throws
            InvalidNameException, InvalidHeroClassException,
            InvalidRaceException, InvalidExlevelException,
            InvalidHpException, CharacterNotFoundException,
            DatabaseException{
        try {
            checkName(character.getName());
            checkHeroClass(character.getHeroClass());
            checkRace(character.getRace());
            checkExlevel(character.getExlevel());
            checkHp(character.getHp());
            OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
            return dao.addCharacter(character);
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException(e.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean modifyCharacter(Character character) throws
            InvalidNameException, InvalidHeroClassException,
            InvalidRaceException, InvalidExlevelException,
            InvalidHpException, CharacterNotFoundException,
            DatabaseException{

        try {

            checkName(character.getName());
            checkHeroClass(character.getHeroClass());
            checkRace(character.getRace());
            checkExlevel(character.getExlevel());
            checkHp(character.getHp());


            OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
            checkExists(dao, character.getId());
            List<CharacterFieldValue> newValues = new ArrayList<>();


            if (character.getName() != null) newValues.add(new CharacterFieldValue(Field.NAME, character.getName()));
            if (character.getHeroClass() != null)
                newValues.add(new CharacterFieldValue(Field.HEROCLASS, character.getHeroClass()));
            if (character.getRace() != null) newValues.add(new CharacterFieldValue(Field.RACE, character.getRace()));
            if (character.getExlevel() > 0)
                newValues.add(new CharacterFieldValue(Field.EXLEVEL, character.getExlevel()));
            if (character.getExlevel() > 0) newValues.add(new CharacterFieldValue(Field.HP, character.getHp()));
            return dao.modifyCharacter(character.id, newValues);
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException(e.getMessage());
        }
    }

    @DELETE
    public boolean deleteCharacter(@QueryParam("id") int id) throws CharacterNotFoundException, DatabaseException {
        try {
            OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
            checkExists(dao, id);
            return dao.deleteCharacter(id);
        } catch (SQLException e) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException(e.getMessage());
        }
    }

    protected void checkExists(OracleSQLDAO dao, final int id) throws CharacterNotFoundException, SQLException {
        if (dao.getCharactersByFields(new ArrayList<CharacterFieldValue>() {{
            add(new CharacterFieldValue(Field.ID, id));
        }}).size() == 0) {
            throw new CharacterNotFoundException("Character with such id is not found");
        }
    }

    protected void checkName(String name) throws InvalidNameException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException("Character name is not specified");
        }
    }

    protected void checkHeroClass(String heroClass) throws InvalidHeroClassException {
        if (heroClass == null || heroClass.trim().isEmpty()) {
            throw new InvalidHeroClassException("Character HeroClass is not specified");
        }
    }
    protected void checkRace(String race) throws InvalidRaceException {
        if (race == null || race.trim().isEmpty()) {
            throw new InvalidRaceException("Character race is not specified");
        }
    }

    protected void checkExlevel(int exlevel) throws InvalidExlevelException {
        if (exlevel <= 0) {
            throw new InvalidExlevelException("Exlevel should be greater than zero");
        }
    }

    protected void checkHp(int hp) throws InvalidHpException {
        if (hp <= 0) {
            throw new InvalidHpException("Hp should be greater than zero");
        }
    }
}
