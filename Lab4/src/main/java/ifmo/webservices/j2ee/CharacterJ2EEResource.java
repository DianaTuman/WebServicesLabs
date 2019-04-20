package ifmo.webservices.j2ee;

import ifmo.webservices.*;
import ifmo.webservices.Character;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/characters")
@Produces({MediaType.APPLICATION_JSON})
public class CharacterJ2EEResource {
    @GET
    public List<Character> getCharacters(
            @QueryParam("id") int id,
            @QueryParam("name") String name,
            @QueryParam("heroClass") String heroclass,
            @QueryParam("race") String race,
            @QueryParam("exlevel") int exlevel,
            @QueryParam("hp") int hp,
            @QueryParam("all") boolean all) {

        OracleSQLDAO dao = new OracleSQLDAO(getConnection());
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

    private Connection getConnection() {
        Connection result = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("ifmo-oracle");
            result = dataSource.getConnection();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(CharacterJ2EEResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
