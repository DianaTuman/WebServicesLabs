package ifmo.webservices;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OracleSQLDAO {
    private Connection connection;

    public OracleSQLDAO(Connection connection) {
        this.connection = connection;
    }

    private List<Character> getCharacters(String query) {
        List<Character> characters = new ArrayList<Character>();

        try {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(Field.fromValue("id").toString());
                String name = rs.getString(Field.fromValue("name").toString());
                String heroClass = rs.getString(Field.fromValue("heroClass").toString());
                String race = rs.getString(Field.fromValue("race").toString());
                int exlevel = rs.getInt(Field.fromValue("exlevel").toString());
                int hp = rs.getInt(Field.fromValue("hp").toString());

                Character character = new Character(race, id, name, hp, heroClass, exlevel);
                characters.add(character);
            }
            connection.close();
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

    public int addCharacter(Character character) {
        int id = -1;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement pstm = connection.prepareStatement(String.format(
                    "insert into characters(name, heroclass, race, exlevel, hp) " +
                            "values('%s', '%s', '%s', %5d, %5d)",
                    character.getName(),
                    character.getHeroClass(),
                    character.getRace(),
                    character.getExlevel(),
                    character.getHp()),
                    new String[]{"id"});

            int i = pstm.executeUpdate();
            if (i > 0) {
                ResultSet rs = pstm.getGeneratedKeys();
                while (rs.next()) {
                    id = Integer.parseInt(rs.getString(i));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public boolean modifyCharacter(int id, List<CharacterFieldValue> newValues) {
        if (newValues.size() == 0) {
            return false;
        }

        StringBuilder query = new StringBuilder("update characters set ");

        for (CharacterFieldValue characterRequest : newValues) {
            String equalExpression = String.format("%s = '%s'", characterRequest.getField().toString().toUpperCase(), characterRequest.getValue());
            query.append(equalExpression);

            if (!characterRequest.equals(newValues.get(newValues.size() - 1))) {
                query.append(", ");
            } else {
                query.append("where ID = ");
                query.append(id);
            }
        }

        return executeOperation(query.toString());
    }

    public boolean deleteCharacter(int id) {
        String query = String.format("delete from characters where id = %5d", id);
        return executeOperation(query);
    }

    private boolean executeOperation(String query) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(OracleSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}