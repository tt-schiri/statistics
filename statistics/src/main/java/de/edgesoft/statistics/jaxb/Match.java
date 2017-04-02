
package de.edgesoft.statistics.jaxb;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.commons.ModelClass;
import de.edgesoft.edgeutils.javafx.SimpleBooleanPropertyAdapter;
import de.edgesoft.edgeutils.javafx.SimpleIntegerPropertyAdapter;
import de.edgesoft.edgeutils.javafx.SimpleObjectPropertyLocalDateAdapter;
import de.edgesoft.edgeutils.javafx.SimpleStringPropertyAdapter;


/**
 * <p>Java class for Match complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Match">
 *   &lt;complexContent>
 *     &lt;extension base="{}ModelClass">
 *       &lt;sequence>
 *         &lt;element name="date" type="{}LocalDateProperty"/>
 *         &lt;element name="title" type="{}StringProperty"/>
 *         &lt;element name="home" type="{}BooleanProperty"/>
 *         &lt;element name="set" type="{}Set" maxOccurs="5"/>
 *         &lt;element name="set-result" type="{}Result"/>
 *         &lt;element name="live-pz-before" type="{}IntegerProperty"/>
 *         &lt;element name="live-pz-other" type="{}IntegerProperty"/>
 *         &lt;element name="live-pz-diff" type="{}IntegerProperty"/>
 *         &lt;element name="live-pz-after" type="{}IntegerProperty"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Match", propOrder = {
    "date",
    "title",
    "home",
    "set",
    "setResult",
    "livePzBefore",
    "livePzOther",
    "livePzDiff",
    "livePzAfter"
})
public class Match
    extends ModelClass
{

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleObjectPropertyLocalDateAdapter.class)
    @XmlSchemaType(name = "date")
    protected SimpleObjectProperty date;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleStringPropertyAdapter.class)
    protected SimpleStringProperty title;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleBooleanPropertyAdapter.class)
    @XmlSchemaType(name = "boolean")
    protected SimpleBooleanProperty home;
    @XmlElement(required = true)
    protected List<Set> set;
    @XmlElement(name = "set-result", required = true)
    protected Result setResult;
    @XmlElement(name = "live-pz-before", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty livePzBefore;
    @XmlElement(name = "live-pz-other", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty livePzOther;
    @XmlElement(name = "live-pz-diff", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty livePzDiff;
    @XmlElement(name = "live-pz-after", required = true, type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty livePzAfter;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleObjectProperty getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(SimpleObjectProperty value) {
        this.date = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleStringProperty getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(SimpleStringProperty value) {
        this.title = value;
    }

    /**
     * Gets the value of the home property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleBooleanProperty getHome() {
        return home;
    }

    /**
     * Sets the value of the home property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHome(SimpleBooleanProperty value) {
        this.home = value;
    }

    /**
     * Gets the value of the set property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the set property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Set }
     * 
     * 
     */
    public List<Set> getSet() {
        if (set == null) {
            set = new ArrayList<Set>();
        }
        return this.set;
    }

    /**
     * Gets the value of the setResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getSetResult() {
        return setResult;
    }

    /**
     * Sets the value of the setResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setSetResult(Result value) {
        this.setResult = value;
    }

    /**
     * Gets the value of the livePzBefore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getLivePzBefore() {
        return livePzBefore;
    }

    /**
     * Sets the value of the livePzBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLivePzBefore(SimpleIntegerProperty value) {
        this.livePzBefore = value;
    }

    /**
     * Gets the value of the livePzOther property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getLivePzOther() {
        return livePzOther;
    }

    /**
     * Sets the value of the livePzOther property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLivePzOther(SimpleIntegerProperty value) {
        this.livePzOther = value;
    }

    /**
     * Gets the value of the livePzDiff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getLivePzDiff() {
        return livePzDiff;
    }

    /**
     * Sets the value of the livePzDiff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLivePzDiff(SimpleIntegerProperty value) {
        this.livePzDiff = value;
    }

    /**
     * Gets the value of the livePzAfter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getLivePzAfter() {
        return livePzAfter;
    }

    /**
     * Sets the value of the livePzAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLivePzAfter(SimpleIntegerProperty value) {
        this.livePzAfter = value;
    }

}
