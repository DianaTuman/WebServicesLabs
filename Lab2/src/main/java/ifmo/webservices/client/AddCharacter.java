package ifmo.webservices.client;

import ifmo.webservices.Character;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addCharacter", propOrder = {
        "character"
})
public class AddCharacter {

    protected Character character;

    /**
     * Gets the value of the character property.
     *
     * @return possible object is
     * {@link Character }
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Sets the value of the character property.
     *
     * @param value allowed object is
     *              {@link Character }
     */
    public void setCharacter(Character value) {
        this.character = value;
    }

}
