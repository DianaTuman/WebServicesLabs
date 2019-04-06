package ifmo.webservices.errors;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "ifmo.webservices.errors.CharacterServiceFault")
public class InvalidHpException extends Exception {
    private final CharacterServiceFault fault;

    public InvalidHpException(String message, CharacterServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public InvalidHpException(String message, CharacterServiceFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public CharacterServiceFault getFaultInfo() {
        return fault;
    }
}