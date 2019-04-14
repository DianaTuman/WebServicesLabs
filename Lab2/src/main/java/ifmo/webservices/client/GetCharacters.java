package ifmo.webservices.client;

import ifmo.webservices.CharacterFieldValue;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCharacters", propOrder = {
        "conditions"
})
public class GetCharacters {

    protected List<CharacterFieldValue> conditions;

    /**
     * Gets the value of the conditions property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conditions property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConditions().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacterFieldValue }
     */
    public List<CharacterFieldValue> getConditions() {
        if (conditions == null) {
            conditions = new ArrayList<CharacterFieldValue>();
        }
        return this.conditions;
    }

}
