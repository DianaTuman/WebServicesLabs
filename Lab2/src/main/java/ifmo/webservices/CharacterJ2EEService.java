package ifmo.webservices;

import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.sql.DataSource;

@WebService(serviceName = "CharacterService")
public class CharacterJ2EEService {
    
    @Resource(lookup = "ifmo-oracle")
    private DataSource dataSource;
    
    @WebMethod(operationName = "getAllCharacters")
    public List<Character> getAllCharacters() {
        OracleSQLDAO dao = new OracleSQLDAO();
        return dao.getAllCharacters();
    }
}
