
package ifmo.webservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for field.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="field"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="id"/&gt;
 *     &lt;enumeration value="name"/&gt;
 *     &lt;enumeration value="heroClass"/&gt;
 *     &lt;enumeration value="race"/&gt;
 *     &lt;enumeration value="exlevel"/&gt;
 *     &lt;enumeration value="hp"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "field")
@XmlEnum
public enum Field {

    @XmlEnumValue("id")
    ID("id"),
    @XmlEnumValue("name")
    NAME("name"),
    @XmlEnumValue("heroClass")
    HEROCLASS("heroClass"),
    @XmlEnumValue("race")
    RACE("race"),
    @XmlEnumValue("exlevel")
    EXLEVEL("exlevel"),
    @XmlEnumValue("hp")
    HP("hp");
    private final String value;

    Field(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Field fromValue(String v) {
        for (Field c: Field.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    @Override
    public String toString() {
        return value;
    }
}
