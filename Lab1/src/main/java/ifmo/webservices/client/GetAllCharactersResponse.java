package ifmo.webservices.client;

import ifmo.webservices.Character;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllCharactersResponse", propOrder = {
        "_return"
})
public class GetAllCharactersResponse {

    @XmlElement(name = "return")
    protected List<Character> _return;

    public List<Character> getReturn() {
        if (_return == null) {
            _return = new ArrayList<Character>();
        }
        return this._return;
    }

}
