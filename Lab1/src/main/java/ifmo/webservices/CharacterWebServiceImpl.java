package ifmo.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(name = "CharacterWebService", serviceName = "CharacterService")
public class CharacterWebServiceImpl implements CharacterWebService {

    @WebMethod(operationName = "getAllCharacters")
    public List<Character> getAllCharacters() {
        OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
        List<Character> characters = dao.getAllCharacters();
        return characters;
    }

    @WebMethod(operationName = "getCharacters")
    public List<Character> getCharacters(@WebParam(name = "conditions") List<CharacterFieldValue> conditions) {
        OracleSQLDAO dao = new OracleSQLDAO(ConnectionUtil.getConnection());
        return dao.getCharactersByFields(conditions);
    }
}