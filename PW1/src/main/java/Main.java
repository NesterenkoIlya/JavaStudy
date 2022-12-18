import org.xml.sax.SAXException;
import validation.XMLValidator;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Validate xml:");
        validateXml();
        System.out.println();

        System.out.println("Testing JAXB parser:");
        testJAXBParser();
        System.out.println();

        System.out.println("Testing DOM parser:");
        testDOMParser();
        System.out.println();

        System.out.println("Testing SAX parser");
        testSAXParser();
        System.out.println();

        System.out.println("XML -> HTML");
        convertXmlToHtml();
    }

    private static void validateXml() throws SAXException {
        XMLValidator validator = new XMLValidator();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("PW1/src/main/resources/xml/Orders.xsd"));

        File[] xmlFiles = new File("PW1/src/main/resources/xml").listFiles();
        if (xmlFiles != null) {
            Arrays.stream(xmlFiles)
                    .filter(file -> file.getName().endsWith(".xml"))
                    .forEach(file -> {
                        Source xml = new StreamSource(file);
                        boolean isValid = validator.isValid(xml, schema);
                        System.out.println(file.getName() + " valid: " + isValid);
                    });
        }
    }

    private static void convertXmlToHtml() throws Exception {

    }

    private static void testSAXParser() throws Exception {

    }

    private static void testDOMParser() throws Exception {

    }

    private static void testJAXBParser() throws Exception {

    }
}
