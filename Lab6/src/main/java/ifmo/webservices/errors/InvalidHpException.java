package ifmo.webservices.errors;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "ifmo.webservices.errors.CharacterServiceFault")
public class InvalidHpException extends Exception {
    public static InvalidHpException DEFAULT_INSTANCE = new
            InvalidHpException("Character hp cannot be null or negative");

    public InvalidHpException(String message) {
        super(message);
    }
}