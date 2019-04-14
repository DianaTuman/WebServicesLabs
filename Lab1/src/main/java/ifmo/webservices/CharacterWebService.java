package ifmo.webservices;

import ifmo.webservices.client.ObjectFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;


@WebService(name = "CharacterWebService", targetNamespace = "http://webservices.ifmo/")
@XmlSeeAlso({
        ObjectFactory.class
})
public interface CharacterWebService {


    /**
     * @return returns java.util.List<ifmo.webservices.Character>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllCharacters", targetNamespace = "http://webservices.ifmo/", className = "ifmo.webservices.client.GetAllCharacters")
    @ResponseWrapper(localName = "getAllCharactersResponse", targetNamespace = "http://webservices.ifmo/", className = "ifmo.webservices.client.GetAllCharactersResponse")
    public List<Character> getAllCharacters();

    /**
     * @param conditions
     * @return returns java.util.List<ifmo.webservices.Character>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getCharacters", targetNamespace = "http://webservices.ifmo/", className = "ifmo.webservices.client.GetCharacters")
    @ResponseWrapper(localName = "getCharactersResponse", targetNamespace = "http://webservices.ifmo/", className = "ifmo.webservices.client.GetCharactersResponse")
    public List<Character> getCharacters(
            @WebParam(name = "conditions", targetNamespace = "")
                    List<CharacterFieldValue> conditions);

}
