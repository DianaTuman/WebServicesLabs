
package ifmo.webservices.client;

import ifmo.webservices.Character;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addCharacter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addCharacter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="character" type="{http://webservices.ifmo/}character" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addCharacter", propOrder = {
    "character"
})
public class AddCharacter {

    protected Character character;

    /**
     * Gets the value of the character property.
     * 
     * @return
     *     possible object is
     *     {@link Character }
     *     
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Sets the value of the character property.
     * 
     * @param value
     *     allowed object is
     *     {@link Character }
     *     
     */
    public void setCharacter(Character value) {
        this.character = value;
    }

}
