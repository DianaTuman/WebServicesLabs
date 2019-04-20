package ifmo.webservices.errors;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "ifmo.webservices.errors.CharacterServiceFault")
public class CharacterNotFoundException extends Exception {
    public static CharacterNotFoundException DEFAULT_INSTANCE = new
            CharacterNotFoundException("Character with such id was not found");

    public CharacterNotFoundException(String message) {
        super(message);
    }
}