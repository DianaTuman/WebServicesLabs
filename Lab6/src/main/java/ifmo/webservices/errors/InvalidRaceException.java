package ifmo.webservices.errors;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "ifmo.webservices.errors.CharacterServiceFault")
public class InvalidRaceException extends Exception {
    public static InvalidRaceException DEFAULT_INSTANCE = new
            InvalidRaceException("Character race cannot be null or empty");

    public InvalidRaceException(String message) {
        super(message);
    }
}