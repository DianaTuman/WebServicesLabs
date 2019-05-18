package ifmo.webservices.errors;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "ifmo.webservices.errors.CharacterServiceFault")
public class ThrottlingException extends Exception {
    private final CharacterServiceFault fault;

    public ThrottlingException(String message, CharacterServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public ThrottlingException(String message, CharacterServiceFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public CharacterServiceFault getFaultInfo() {
        return fault;
    }
}