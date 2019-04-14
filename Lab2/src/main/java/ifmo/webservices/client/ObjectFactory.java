package ifmo.webservices.client;

import ifmo.webservices.Character;
import ifmo.webservices.CharacterFieldValue;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the ifmo.webservices package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllCharacters_QNAME = new QName("http://webservices.ifmo/", "getAllCharacters");
    private final static QName _GetAllCharactersResponse_QNAME = new QName("http://webservices.ifmo/", "getAllCharactersResponse");
    private final static QName _GetCharacters_QNAME = new QName("http://webservices.ifmo/", "getCharacters");
    private final static QName _GetCharactersResponse_QNAME = new QName("http://webservices.ifmo/", "getCharactersResponse");
    private final static QName _AddCharacter_QNAME = new QName("http://webservices.ifmo/", "addCharacter");
    private final static QName _AddCharacterResponse_QNAME = new QName("http://webservices.ifmo/", "addCharacterResponse");
    private final static QName _DeleteCharacter_QNAME = new QName("http://webservices.ifmo/", "deleteCharacter");
    private final static QName _DeleteCharacterResponse_QNAME = new QName("http://webservices.ifmo/", "deleteCharacterResponse");
    private final static QName _ModifyCharacter_QNAME = new QName("http://webservices.ifmo/", "modifyCharacter");
    private final static QName _ModifyCharacterResponse_QNAME = new QName("http://webservices.ifmo/", "modifyCharacterResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ifmo.webservices
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteCharacter }
     */
    public DeleteCharacter createDeleteCharacter() {
        return new DeleteCharacter();
    }

    /**
     * Create an instance of {@link DeleteCharacterResponse }
     */
    public DeleteCharacterResponse createDeleteCharacterResponse() {
        return new DeleteCharacterResponse();
    }

    /**
     * Create an instance of {@link AddCharacter }
     */
    public AddCharacter createAddCharacter() {
        return new AddCharacter();
    }

    /**
     * Create an instance of {@link AddCharacterResponse }
     */
    public AddCharacterResponse createAddCharacterResponse() {
        return new AddCharacterResponse();
    }

    /**
     * Create an instance of {@link ModifyCharacter }
     */
    public ModifyCharacter createModifyCharacter() {
        return new ModifyCharacter();
    }

    /**
     * Create an instance of {@link ModifyCharacterResponse }
     */
    public ModifyCharacterResponse createModifyCharacterResponse() {
        return new ModifyCharacterResponse();
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


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCharacter }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservices.ifmo/", name = "addCharacter")
    public JAXBElement<AddCharacter> createAddCharacter(AddCharacter value) {
        return new JAXBElement<AddCharacter>(_AddCharacter_QNAME, AddCharacter.class, null, value);
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCharacterResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservices.ifmo/", name = "addCharacterResponse")
    public JAXBElement<AddCharacterResponse> createAddCharacterResponse(AddCharacterResponse value) {
        return new JAXBElement<AddCharacterResponse>(_AddCharacterResponse_QNAME, AddCharacterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCharacter }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservices.ifmo/", name = "deleteCharacter")
    public JAXBElement<DeleteCharacter> createDeleteCharacter(DeleteCharacter value) {
        return new JAXBElement<DeleteCharacter>(_DeleteCharacter_QNAME, DeleteCharacter.class, null, value);
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCharacterResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservices.ifmo/", name = "deleteCharacterResponse")
    public JAXBElement<DeleteCharacterResponse> createDeleteCharacterResponse(DeleteCharacterResponse value) {
        return new JAXBElement<DeleteCharacterResponse>(_DeleteCharacterResponse_QNAME, DeleteCharacterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyCharacter }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservices.ifmo/", name = "modifyCharacter")
    public JAXBElement<ModifyCharacter> createModifyCharacter(ModifyCharacter value) {
        return new JAXBElement<ModifyCharacter>(_ModifyCharacter_QNAME, ModifyCharacter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyCharacterResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservices.ifmo/", name = "modifyCharacterResponse")
    public JAXBElement<ModifyCharacterResponse> createModifyCharacterResponse(ModifyCharacterResponse value) {
        return new JAXBElement<ModifyCharacterResponse>(_ModifyCharacterResponse_QNAME, ModifyCharacterResponse.class, null, value);
    }

}
