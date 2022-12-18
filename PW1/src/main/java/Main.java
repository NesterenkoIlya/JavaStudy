import org.xml.sax.SAXException;

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

    private static void convertXmlToHtml() throws Exception {

    }

    private static void testSAXParser() throws Exception {

    }

    private static void testDOMParser() throws Exception {

    }

    private static void testJAXBParser() throws Exception {

    }

    private static void validateXml() throws SAXException {

    }
}
