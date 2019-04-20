package ifmo.webservices;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

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
            @QueryParam("all") boolean all) {

        OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
        List<CharacterFieldValue> conditions = new ArrayList<>();

        if (all != false) return dao.getAllCharacters();
        if (id != 0) conditions.add(new CharacterFieldValue(Field.ID, id));
        if (name != null) conditions.add(new CharacterFieldValue(Field.NAME, name));
        if (heroclass != null) conditions.add(new CharacterFieldValue(Field.HEROCLASS, heroclass));
        if (race != null) conditions.add(new CharacterFieldValue(Field.RACE, race));
        if (exlevel != 0) conditions.add(new CharacterFieldValue(Field.EXLEVEL, exlevel));
        if (hp != 0) conditions.add(new CharacterFieldValue(Field.HP, hp));
        return dao.getCharactersByFields(conditions);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public int addCharacter(Character character) {
        OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
        return dao.addCharacter(character);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean modifyCharacter(Character character) {
        OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());

        List<CharacterFieldValue> newValues = new ArrayList<>();


        if (character.getName() != null) newValues.add(new CharacterFieldValue(Field.NAME, character.getName()));
        if (character.getHeroClass() != null)
            newValues.add(new CharacterFieldValue(Field.HEROCLASS, character.getHeroClass()));
        if (character.getRace() != null) newValues.add(new CharacterFieldValue(Field.RACE, character.getRace()));
        if (character.getExlevel() > 0) newValues.add(new CharacterFieldValue(Field.EXLEVEL, character.getExlevel()));
        if (character.getExlevel() > 0) newValues.add(new CharacterFieldValue(Field.HP, character.getHp()));


        return dao.modifyCharacter(character.id, newValues);
    }

    @DELETE
    public boolean deleteCharacter(@QueryParam("id") int id) {
        OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
        return dao.deleteCharacter(id);
    }
}
