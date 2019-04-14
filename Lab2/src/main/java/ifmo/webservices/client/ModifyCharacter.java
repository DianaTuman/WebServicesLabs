package ifmo.webservices.client;

import ifmo.webservices.CharacterFieldValue;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyCharacter", propOrder = {
        "id",
        "newValues"
})
public class ModifyCharacter {

    protected int id;
    protected List<CharacterFieldValue> newValues;

    /**
     * Gets the value of the id property.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the newValues property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the newValues property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharacterFieldValues().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacterFieldValue }
     */
    public List<CharacterFieldValue> getCharacterFieldValues() {
        if (newValues == null) {
            newValues = new ArrayList<CharacterFieldValue>();
        }
        return this.newValues;
    }

}
