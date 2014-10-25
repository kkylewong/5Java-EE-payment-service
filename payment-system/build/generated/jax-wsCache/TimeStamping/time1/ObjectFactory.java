
package time1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the time1 package. 
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

    private final static QName _GetTimeStampingResponse_QNAME = new QName("http://time1/", "getTimeStampingResponse");
    private final static QName _GetTimeStamping_QNAME = new QName("http://time1/", "getTimeStamping");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: time1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetTimeStampingResponse }
     * 
     */
    public GetTimeStampingResponse createGetTimeStampingResponse() {
        return new GetTimeStampingResponse();
    }

    /**
     * Create an instance of {@link GetTimeStamping }
     * 
     */
    public GetTimeStamping createGetTimeStamping() {
        return new GetTimeStamping();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTimeStampingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://time1/", name = "getTimeStampingResponse")
    public JAXBElement<GetTimeStampingResponse> createGetTimeStampingResponse(GetTimeStampingResponse value) {
        return new JAXBElement<GetTimeStampingResponse>(_GetTimeStampingResponse_QNAME, GetTimeStampingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTimeStamping }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://time1/", name = "getTimeStamping")
    public JAXBElement<GetTimeStamping> createGetTimeStamping(GetTimeStamping value) {
        return new JAXBElement<GetTimeStamping>(_GetTimeStamping_QNAME, GetTimeStamping.class, null, value);
    }

}
