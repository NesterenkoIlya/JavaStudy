import org.xml.sax.SAXException;
import parsers.JAXBParser;
import parsers.OrdersDOMParser;
import parsers.OrdersSAXParser;
import validation.XMLValidator;
import org.example.eatshop.order.Order;
import org.example.eatshop.orders.ObjectFactory;
import org.example.eatshop.orders.Orders;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import parsers.XMLToHTML;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Validate xml:");
        validateXml();
        System.out.println();

        System.out.println("XML -> HTML");
        convertXmlToHtml();
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
    }

    private static void validateXml() throws SAXException {
        XMLValidator validator = new XMLValidator();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/main/resources/xml/Orders.xsd"));

        File[] xmlFiles = new File("src/main/resources/xml").listFiles();
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
        Source xml = new StreamSource("src/main/resources/xml/Orders.xml");
        Source xslt = new StreamSource("src/main/resources/xml/listOrder.xslt");
        OutputStream resultHTML = Files.newOutputStream(Paths.get("src/main/resources/html/orders.html"));
        XMLToHTML converter = new XMLToHTML();
        converter.convert(xml, xslt, resultHTML);
    }

    private static void testSAXParser() throws Exception {
        Source ordersXml = new StreamSource("src/main/resources/xml/Orders.xml");
        Schema schema = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(new File("src/main/resources/xml/Orders.xsd"));
        OrdersSAXParser parser = new OrdersSAXParser();

        Orders orders = parser.unmarshal(ordersXml, schema, new FileInputStream("src/main/resources/xml/Orders.xml"));
        System.out.println(orders.getOrder());
    }

    private static void testDOMParser() throws Exception {
        Source roomsXml = new StreamSource("src/main/resources/xml/Orders.xml");
        Schema schema = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(new File("src/main/resources/xml/Orders.xsd"));
        OrdersDOMParser parser = new OrdersDOMParser();

        Orders orders = parser.unmarshal(roomsXml, schema, new FileInputStream("src/main/resources/xml/Orders.xml"));
        System.out.println(orders.getOrder());

        List<Order> filteredOrders = orders
                .getOrder()
                .stream()
                .filter(order -> order.getId() == 0)
                .collect(Collectors.toList());

        Orders zerosOrder = new Orders();
        zerosOrder.getOrder().addAll(filteredOrders);
        parser.marshal(zerosOrder, new File("src/main/resources/xml/marshalled/zerosOrder.xml"));
    }

    private static void testJAXBParser() throws Exception {
        Source ordersXml = new StreamSource("src/main/resources/xml/Orders.xml");
        Schema schema = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(new File("src/main/resources/xml/Orders.xsd"));
        JAXBParser parser = new JAXBParser(ObjectFactory.class);

        Orders orders = parser.unmarshal(ordersXml, schema);
        System.out.println(orders.getOrder());

        List<Order> filteredOrder = orders
                .getOrder()
                .stream()
                .filter(order -> order.getId() == 0)
                .collect(Collectors.toList());

        Orders zeroOrder = new Orders();
        zeroOrder.getOrder().addAll(filteredOrder);
        parser.marshal(zeroOrder, schema, new File("src/main/resources/xml/marshalled/ZerosOrder.xml"));
    }
}
