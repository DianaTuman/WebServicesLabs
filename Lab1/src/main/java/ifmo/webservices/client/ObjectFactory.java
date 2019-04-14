package ifmo.webservices.client;

import ifmo.webservices.Character;
import ifmo.webservices.CharacterFieldValue;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllCharacters_QNAME = new QName("http://webservices.ifmo/", "getAllCharacters");
    private final static QName _GetAllCharactersResponse_QNAME = new QName("http://webservices.ifmo/", "getAllCharactersResponse");
    private final static QName _GetCharacters_QNAME = new QName("http://webservices.ifmo/", "getCharacters");
    private final static QName _GetCharactersResponse_QNAME = new QName("http://webservices.ifmo/", "getCharactersResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ifmo.webservices
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllCharacters }
     */
    public GetAllCharacters createGetAllCharacters() {
        return new GetAllCharacters();
    }

    /**
     * Create an instance of {@link GetAllCharactersResponse }
     */
    public GetAllCharactersResponse createGetAllCharactersResponse() {
        return new GetAllCharactersResponse();
    }

    /**
     * Create an instance of {@link GetCharacters }
     */
    public GetCharacters createGetCharacters() {
        return new GetCharacters();
    }

    /**
     * Create an instance of {@link GetCharactersResponse }
     */
    public GetCharactersResponse createGetCharactersResponse() {
        return new GetCharactersResponse();
    }

    /**
     * Create an instance of {@link Character }
     */
    public Character createCharacter() {
        return new Character();
    }

    /**
     * Create an instance of {@link CharacterFieldValue }
     */
    public CharacterFieldValue createCharacterCondition() {
        return new CharacterFieldValue();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCharacters }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link GetAllCharacters }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.ifmo/", name = "getAllCharacters")
    public JAXBElement<GetAllCharacters> createGetAllCharacters(GetAllCharacters value) {
        return new JAXBElement<GetAllCharacters>(_GetAllCharacters_QNAME, GetAllCharacters.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCharactersResponse }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link GetAllCharactersResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.ifmo/", name = "getAllCharactersResponse")
    public JAXBElement<GetAllCharactersResponse> createGetAllCharactersResponse(GetAllCharactersResponse value) {
        return new JAXBElement<GetAllCharactersResponse>(_GetAllCharactersResponse_QNAME, GetAllCharactersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCharacters }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link GetCharacters }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.ifmo/", name = "getCharacters")
    public JAXBElement<GetCharacters> createGetCharacters(GetCharacters value) {
        return new JAXBElement<GetCharacters>(_GetCharacters_QNAME, GetCharacters.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCharactersResponse }{@code >}
     *
     * @param value Java instance representing xml element's value.
     * @return the new instance of {@link JAXBElement }{@code <}{@link GetCharactersResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.ifmo/", name = "getCharactersResponse")
    public JAXBElement<GetCharactersResponse> createGetCharactersResponse(GetCharactersResponse value) {
        return new JAXBElement<GetCharactersResponse>(_GetCharactersResponse_QNAME, GetCharactersResponse.class, null, value);
    }

}
