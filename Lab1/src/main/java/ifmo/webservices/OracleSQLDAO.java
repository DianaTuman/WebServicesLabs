package ifmo.webservices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OracleSQLDAO {
    private List<Character> getCharacters(String query) {
        List<Character> characters = new ArrayList<Character>();

        try (Connection connection = ConnectionUtil.getConnection()) {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(Field.fromValue("id").toString());
                String name = rs.getString(Field.fromValue("name").toString());
                String heroclass = rs.getString(Field.fromValue("heroClass").toString());
                String race = rs.getString(Field.fromValue("race").toString());
                int level = rs.getInt(Field.fromValue("exlevel").toString());
                int hp = rs.getInt(Field.fromValue("hp").toString());

                Character character = new Character(race, id, name, hp, heroclass, level);
                characters.add(character);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return characters;
    }

    public List<Character> getAllCharacters() {
        return getCharacters("select * from characters");
    }

    public List<Character> getCharactersByFields(List<CharacterFieldValue> characterRequests) {
        StringBuilder query = new StringBuilder("select * from characters where ");

        for (CharacterFieldValue characterRequest : characterRequests) {
            String equalExpression = String.format("%s = '%s'", characterRequest.getField(), characterRequest.getValue());
            query.append(equalExpression);

            if (!characterRequest.equals(characterRequests.get(characterRequests.size() - 1))) {
                query.append(" and ");
            }
        }
        return getCharacters(query.toString());
    }
}