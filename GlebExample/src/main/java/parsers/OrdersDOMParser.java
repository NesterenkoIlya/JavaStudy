package parsers;


import org.example.sportshop.customer.Customer;
import org.example.sportshop.order.Order;
import org.example.sportshop.orders.Orders;
import org.example.sportshop.product.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import validation.XMLValidator;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class OrdersDOMParser {

    private static final String NAME_SPACE = "http://sportshop/orders";

    private final XMLValidator validator = new XMLValidator();

    public Orders unmarshal(Source xml, Schema schema, InputStream xmlInputStream) throws Exception {
        if (!validator.isValid(xml, schema)) {
            throw new Exception("XML is not valid");
        }

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);

        NodeList orderNodes = documentBuilderFactory
                .newDocumentBuilder()
                .parse(xmlInputStream)
                .getElementsByTagNameNS(NAME_SPACE, "order");

        Orders orders = new Orders();
        for (int index = 0; index < orderNodes.getLength(); index++) {
            orders.getOrder().add(unmarshalOrder(orderNodes.item(index)));
        }

        return orders;
    }

    private Order unmarshalOrder(Node node) throws DatatypeConfigurationException, ParseException {
        Order order = new Order();

        order.setId(
                (short) Integer.parseInt(
                        node.getAttributes()
                                .getNamedItem("id")
                                .getNodeValue()
                )
        );

        NodeList orderNodes = node.getChildNodes();
        for (int index = 0; index < orderNodes.getLength(); index++) {
            Node orderNode = orderNodes.item(index);

            switch (orderNode.getNodeName()) {
                case "ord:order_time":
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = format.parse(orderNode.getTextContent());
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.setTime(date);
                    XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
                    order.setOrderTime(xmlGregCal);
                    break;
                case "ord:order_item":
                    NodeList orderItemsNodes = orderNode.getChildNodes();
                    Order.OrderItem orderItem = new Order.OrderItem();
                    for (int jindex = 0; jindex < orderItemsNodes.getLength(); jindex++){
                        Node orderItemNode = orderItemsNodes.item(jindex);
                        switch (orderItemNode.getNodeName()) {
                            case "ord:count":
                                orderItem.setCount(Integer.parseInt(orderItemNode.getTextContent()));
                                break;
                            case "ord:product":
                                Product product = new Product();
                                product.setId(
                                        (short) Integer.parseInt(
                                                orderItemNode.getAttributes()
                                                        .getNamedItem("id")
                                                        .getNodeValue()
                                        )
                                );
                                NodeList productNodes = orderItemNode.getChildNodes();
                                for (int kindex = 0; kindex < productNodes.getLength(); kindex++){
                                    Node productNode = productNodes.item(kindex);
                                    switch (productNode.getNodeName()){
                                        case "prod:description":
                                            product.setDescription(productNode.getTextContent());
                                            break;
                                        case "prod:name":
                                            product.setName(productNode.getTextContent());
                                            break;
                                        case "prod:supply_date":
                                            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                                            Date date1 = format1.parse(productNode.getTextContent());
                                            GregorianCalendar cal1 = new GregorianCalendar();
                                            cal1.setTime(date1);
                                            XMLGregorianCalendar xmlGregCal1 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal1);
                                            product.setSupplyDate(xmlGregCal1);
                                            break;
                                        case "prod:price":
                                            product.setPrice(Integer.parseInt(productNode.getTextContent()));
                                            break;
                                        case "prod:item_weigth":
                                            product.setItemWeigth(BigDecimal.valueOf(Long.parseLong(productNode.getTextContent())));
                                            break;
                                        case "prod:model_year":
                                            product.setModelYear(Integer.parseInt(productNode.getTextContent()));
                                            break;
                                        case "prod:size":
                                            product.setSize(productNode.getTextContent());
                                            break;
                                        case "prod:color":
                                            product.setColor(productNode.getTextContent());
                                            break;
                                        case "prod:rating":
                                            product.setRating(BigDecimal.valueOf((Long.parseLong(productNode.getTextContent()))));
                                            break;
                                        default: break;
                                    }
                                    orderItem.setProduct(product);
                                }
                                break;
                            default: break;
                        }
                    }
                    order.addOrderItemListElement(orderItem);
                    break;
                /**order.setOrderItem(orderNode.getChildNodes());**/
                case "ord:customer":
                    Customer customer = new Customer();
                    NodeList customerNodes = orderNode.getChildNodes();
                    customer.setId(
                            (short) Integer.parseInt(
                                    orderNode.getAttributes()
                                            .getNamedItem("id")
                                            .getNodeValue()
                            )
                    );
                    for(int i = 0; i < customerNodes.getLength(); i++){
                        Node customerNode = customerNodes.item(i);
                        switch (customerNode.getNodeName()){
                            case "cus:name": customer.setName(customerNode.getTextContent());
                                break;
                            case "cus:second_name": customer.setSecondName(customerNode.getTextContent());
                                break;
                            case "cus:surname": customer.setSurname(customerNode.getTextContent());
                                break;
                            case "cus:age": customer.setAge(Short.parseShort(customerNode.getTextContent()));
                                break;
                            case "cus:email": customer.setEmail(customerNode.getTextContent());
                                break;
                            case "cus:password": customer.setPassword(customerNode.getTextContent());
                                break;
                            case "cus:registration_date":
                                DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                                Date date1 = format1.parse(customerNode.getTextContent());
                                GregorianCalendar cal1 = new GregorianCalendar();
                                cal1.setTime(date1);
                                XMLGregorianCalendar xmlGregCal1 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal1);
                                customer.setRegistrationDate(xmlGregCal1);
                                break;
                            default: break;
                        }
                    }
                    order.setCustomer(customer);
                    break;
                default: break;
            }
        }

        return order;
    }

    public void marshal(Orders orders, File resultXml) throws Exception {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element rootElement = document.createElementNS(NAME_SPACE, "orders");
        document.appendChild(rootElement);

        for (Order order : orders.getOrder()) {
            rootElement.appendChild(marshalOrder(order, document));
        }

        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new FileOutputStream(resultXml));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.transform(domSource, streamResult);
    }

    private Node marshalOrder(Order order, Document document) {
        Element orderElement = document.createElementNS(NAME_SPACE, "order");
        orderElement.setAttribute("id", String.valueOf(order.getId()));

        orderElement
                .appendChild(document.createElementNS(NAME_SPACE, "order_time"))
                .appendChild(document.createTextNode(order.getOrderTime()));
        orderElement
                .appendChild(document.createElementNS(NAME_SPACE, "order_item"))
                .appendChild(document.createTextNode(String.valueOf(order.getOrderItem())));
        orderElement
                .appendChild(document.createElementNS(NAME_SPACE, "customer"))
                .appendChild(document.createTextNode(String.valueOf(order.getCustomer())));


        return orderElement;
    }
}
