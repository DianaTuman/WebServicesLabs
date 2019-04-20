package ifmo.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

    @GET
    public List<Character> getAllCharacters(
            @QueryParam("all") boolean val) {
        OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
        return dao.getAllCharacters();
    }
}
