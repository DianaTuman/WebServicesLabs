package ifmo.webservices;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleSQLDAO {
    private Connection connection;

    public OracleSQLDAO(Connection connection) {
        this.connection = connection;
    }

    private List<Character> getCharacters(String query) throws SQLException {
        List<Character> characters = new ArrayList<Character>();
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
        return characters;
    }

    public List<Character> getAllCharacters() throws SQLException {
        return getCharacters("select * from characters");
    }

    public List<Character> getCharactersByFields(List<CharacterFieldValue> characterRequests)
            throws SQLException {
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

    public int addCharacter(Character character) throws SQLException {
        int id = -1;
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
        return id;
    }

    public boolean modifyCharacter(int id, List<CharacterFieldValue> newValues) throws SQLException {
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

    public boolean deleteCharacter(int id) throws SQLException {
        String query = String.format("delete from characters where id = %5d", id);
        return executeOperation(query);
    }

    private boolean executeOperation(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery(query);
        return true;
    }
}