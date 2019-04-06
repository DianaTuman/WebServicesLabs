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

    @WebMethod(operationName = "addCharacter")
    public int addCharacter(@WebParam(name = "character") Character character) {
        OracleSQLDAO dao = new OracleSQLDAO();
        return dao.addCharacter(character);
    }

    @WebMethod(operationName = "modifyCharacter")
    public boolean modifyCharacter(@WebParam(name = "id") int id, @WebParam(name = "newValues") List<CharacterFieldValue> newValues) {
        OracleSQLDAO dao = new OracleSQLDAO();
        return dao.modifyCharacter(id, newValues);
    }

    @WebMethod(operationName = "deleteCharacter")
    public boolean deleteCharacter(@WebParam(name = "id") int id) {
        OracleSQLDAO dao = new OracleSQLDAO();
        return dao.deleteCharacter(id);
    }
}