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


    public List<CharacterFieldValue> getConditions() {
        if (conditions == null) {
            conditions = new ArrayList<CharacterFieldValue>();
        }
        return this.conditions;
    }

}
