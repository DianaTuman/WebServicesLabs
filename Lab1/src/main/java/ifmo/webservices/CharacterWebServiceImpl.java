package ifmo.webservices;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "CharacterWebService", serviceName = "CharacterService")
public class CharacterWebServiceImpl implements CharacterWebService {

    @WebMethod(operationName = "getAllCharacters")
    public List<Character> getAllCharacters() {
        OracleSQLDAO dao = new OracleSQLDAO();
        List<Character> characters = dao.getAllCharacters();
        return characters;
    }

    @WebMethod(operationName = "getCharacters")
    public List<Character> getCharacters(@WebParam(name = "conditions") List<CharacterFieldValue> conditions) {
        OracleSQLDAO dao = new OracleSQLDAO();
        return dao.getCharactersByFields(conditions);
    }
}