package ifmo.webservices.errors;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "ifmo.webservices.errors.CharacterServiceFault")
public class InvalidExlevelException extends Exception {
    public static InvalidExlevelException DEFAULT_INSTANCE = new
            InvalidExlevelException("Character exlevel cannot be null or negative");

    public InvalidExlevelException(String message) {
        super(message);
    }
}