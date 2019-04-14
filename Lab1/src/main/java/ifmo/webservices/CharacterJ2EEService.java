package ifmo.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(name = "CharacterWebService", serviceName = "CharacterService")
public class CharacterJ2EEService implements CharacterWebService {
    @WebMethod(operationName = "getAllCharacters")
    public List<Character> getAllCharacters() {
        OracleSQLDAO dao = new OracleSQLDAO(getConnection());
        List<Character> books = dao.getAllCharacters();
        return books;
    }

    @WebMethod(operationName = "getCharacters")
    public List<Character> getCharacters(@WebParam(name = "conditions") List<CharacterFieldValue> conditions) {
        OracleSQLDAO dao = new OracleSQLDAO(getConnection());
        return dao.getCharactersByFields(conditions);
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
