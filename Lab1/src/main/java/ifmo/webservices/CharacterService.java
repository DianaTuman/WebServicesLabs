package ifmo.webservices;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


@WebServiceClient(name = "CharacterService", targetNamespace = "http://webservices.ifmo/", wsdlLocation = "http://localhost:8081/CharacterService?wsdl")
public class CharacterService
        extends Service {

    private final static URL CHARACTERSERVICE_WSDL_LOCATION;
    private final static WebServiceException CHARACTERSERVICE_EXCEPTION;
    private final static QName CHARACTERSERVICE_QNAME = new QName("http://webservices.ifmo/", "CharacterService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8081/CharacterService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CHARACTERSERVICE_WSDL_LOCATION = url;
        CHARACTERSERVICE_EXCEPTION = e;
    }

    public CharacterService() {
        super(__getWsdlLocation(), CHARACTERSERVICE_QNAME);
    }

    public CharacterService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CHARACTERSERVICE_QNAME, features);
    }

    public CharacterService(URL wsdlLocation) {
        super(wsdlLocation, CHARACTERSERVICE_QNAME);
    }

    public CharacterService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CHARACTERSERVICE_QNAME, features);
    }

    public CharacterService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CharacterService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    private static URL __getWsdlLocation() {
        if (CHARACTERSERVICE_EXCEPTION != null) {
            throw CHARACTERSERVICE_EXCEPTION;
        }
        return CHARACTERSERVICE_WSDL_LOCATION;
    }

    /**
     * @return returns CharacterWebService
     */
    @WebEndpoint(name = "CharacterWebServicePort")
    public CharacterWebService getCharacterWebServicePort() {
        return super.getPort(new QName("http://webservices.ifmo/", "CharacterWebServicePort"), CharacterWebService.class);
    }

    /**
     * @param features A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns CharacterWebService
     */
    @WebEndpoint(name = "CharacterWebServicePort")
    public CharacterWebService getCharacterWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.ifmo/", "CharacterWebServicePort"), CharacterWebService.class, features);
    }

}
