import org.example.sportshop.order.Order;
import org.example.sportshop.orders.ObjectFactory;
import org.example.sportshop.orders.Orders;
import org.xml.sax.SAXException;
import parsers.JAXBParser;
import parsers.OrdersDOMParser;
import parsers.OrdersSAXParser;
import parsers.XMLToHTMLConverter;
import validation.XMLValidator;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
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
        Source xml = new StreamSource("src/main/resources/xml/Orders.xml");
        Source xslt = new StreamSource("src/main/resources/xml/listOrder.xslt");
        OutputStream resultHtml = new FileOutputStream("Gleb/src/main/resources/html/orders.html");
        XMLToHTMLConverter converter = new XMLToHTMLConverter();
        converter.convert(xml, xslt, resultHtml);
    }

    private static void testSAXParser() throws Exception {
        Source ordersXml = new StreamSource("Gleb/src/main/resources/xml/Orders.xml");
        Schema schema = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(new File("Gleb/src/main/resources/xml/Orders.xsd"));
        OrdersSAXParser parser = new OrdersSAXParser();

        Orders orders = parser.unmarshal(ordersXml, schema, new FileInputStream("Gleb/src/main/resources/xml/Orders.xml"));
        System.out.println(orders.getOrder());
    }

    private static void testDOMParser() throws Exception {
        Source roomsXml = new StreamSource("Gleb/src/main/resources/xml/Orders.xml");
        Schema schema = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(new File("Gleb/src/main/resources/xml/Orders.xsd"));
        OrdersDOMParser parser = new OrdersDOMParser();

        Orders orders = parser.unmarshal(roomsXml, schema, new FileInputStream("Gleb/src/main/resources/xml/Orders.xml"));
        System.out.println(orders.getOrder());

        List<Order> filteredOrders = orders
                .getOrder()
                .stream()
                .filter(order -> order.getId() == 0)
                .collect(Collectors.toList());

        Orders zerosOrder = new Orders();
        zerosOrder.getOrder().addAll(filteredOrders);
        parser.marshal(zerosOrder, new File("Gleb/src/main/resources/xml/marshalled/zerosOrder.xml"));
    }

    private static void testJAXBParser() throws Exception {
        Source ordersXml = new StreamSource("Gleb/src/main/resources/xml/Orders.xml");
        Schema schema = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                .newSchema(new File("Gleb/src/main/resources/xml/Orders.xsd"));
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
        parser.marshal(zeroOrder, schema, new File("Gleb/src/main/resources/xml/marshalled/ZerosOrder.xml"));
    }

    private static void validateXml() throws SAXException {
        XMLValidator validator = new XMLValidator();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("Gleb/src/main/resources/xml/Orders.xsd"));

        File[] xmlFiles = new File("Gleb/src/main/resources/xml").listFiles();
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
}
