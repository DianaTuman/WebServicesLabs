package ifmo.webservices.errors;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "ifmo.webservices.errors.CharacterServiceFault")
public class InvalidNameException extends Exception {
    public static InvalidNameException DEFAULT_INSTANCE = new
            InvalidNameException("Name cannot be null or empty");

    public InvalidNameException(String message) {
        super(message);
    }
}