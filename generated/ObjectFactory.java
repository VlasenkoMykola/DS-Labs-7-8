//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.12.17 at 08:48:47 PM EET 
//


package ua.knu.vlasenko.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.knu.vlasenko.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _XMLDealerStore_QNAME = new QName("http://ips33.csc.knu.ua/vlasenko", "XMLDealerStore");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.knu.vlasenko.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link XMLDealerStore }
     * 
     */
    public XMLDealerStore createXMLDealerStore() {
        return new XMLDealerStore();
    }

    /**
     * Create an instance of {@link Model }
     * 
     */
    public Model createModel() {
        return new Model();
    }

    /**
     * Create an instance of {@link Maker }
     * 
     */
    public Maker createMaker() {
        return new Maker();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLDealerStore }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ips33.csc.knu.ua/vlasenko", name = "XMLDealerStore")
    public JAXBElement<XMLDealerStore> createXMLDealerStore(XMLDealerStore value) {
        return new JAXBElement<XMLDealerStore>(_XMLDealerStore_QNAME, XMLDealerStore.class, null, value);
    }

}