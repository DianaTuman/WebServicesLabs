package ifmo.webservices.errors;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "ifmo.webservices.errors.CharacterServiceFault")
public class InvalidHeroClassException extends Exception {
    public static InvalidNameException DEFAULT_INSTANCE = new
            InvalidNameException("Hero class cannot be null or empty");

    public InvalidHeroClassException(String message) {
        super(message);
    }
}
