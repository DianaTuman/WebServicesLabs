package ifmo.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "characterCondition", propOrder = {
        "field", "value"
})
public class CharacterFieldValue {

    @XmlSchemaType(name = "string")
    protected Field field;
    @XmlSchemaType(name = "string")
    protected Object value;

    public CharacterFieldValue(Field field, Object value) {
        this.field = field;
        this.value = value;
    }

    public CharacterFieldValue() {
    }

    /**
     * Gets the value of the field property.
     *
     * @return possible object is
     * {@link Field }
     */
    public Field getField() {
        return field;
    }

    /**
     * Sets the value of the field property.
     *
     * @param value allowed object is
     *              {@link Field }
     */
    public void setField(Field value) {
        this.field = value;
    }


    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Character filed value {" +
                "field=" + field +
                ", value='" + value +
                "'}";
    }
}
