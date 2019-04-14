package ifmo.webservices.errors;

public class CharacterServiceFault {
    protected String message;

    public static CharacterServiceFault defaultInstance() {
        CharacterServiceFault fault = new CharacterServiceFault();
        return fault;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}