package parsers;


import org.example.sportshop.customer.Customer;
import org.example.sportshop.order.Order;
import org.example.sportshop.orders.Orders;
import org.example.sportshop.product.Product;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import validation.XMLValidator;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class OrdersSAXParser extends DefaultHandler {

    private final XMLValidator validator = new XMLValidator();

    private String current = "";
    private Orders orders;
    private Order order;
    private Product product;
    private Customer customer;
    private Order.OrderItem orderItem;

    private boolean border_item = false;

    private boolean border_time =  false;

    private boolean bcount = false;
    private boolean bdesc = false;
    private boolean bprod_name = false;
    private boolean bsypply_date = false;
    private boolean bprice = false;
    private boolean bitem_weight = false;
    private boolean bmodel_year = false;
    private boolean bsize = false;
    private boolean bcolor = false;
    private boolean brating = false;

    private boolean bname = false;
    private boolean bsecond_name = false;
    private boolean bsurname = false;
    private boolean bage = false;
    private boolean bemail = false;
    private boolean bpassword = false;
    private boolean breg_date = false;

    public Orders unmarshal(Source xml, Schema schema, InputStream xmlInputStream) throws Exception {
        if (!validator.isValid(xml, schema)) {
            throw new Exception("XML is not valid");
        }

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        saxParserFactory.newSAXParser().parse(xmlInputStream, this);

        return orders;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        current = localName;
            if(qName.equalsIgnoreCase("orders")){
                orders = new Orders();}
            if(qName.equalsIgnoreCase("order")){
                order = new Order();
                order.setId((short) Integer.parseInt(attributes.getValue("id")));}
            if(qName.equalsIgnoreCase("ord:order_item")) {
                orderItem = new Order.OrderItem();
            }
            if(qName.equalsIgnoreCase("ord:order_time")){
                border_time = true;
            }
            if(qName.equalsIgnoreCase("ord:product")) {
            product = new Product();
            product.setId(Short.parseShort(attributes.getValue("id")));
        }
            if(qName.equalsIgnoreCase("ord:customer")) {
                customer = new Customer();
                customer.setId((short) Integer.parseInt((attributes.getValue("id"))));
            }
            if(qName.equalsIgnoreCase("ord:count")){
                bcount = true;
            }
            if(qName.equalsIgnoreCase("prod:description")) {
                bdesc = true;
            }
            if(qName.equalsIgnoreCase("prod:name")) {
            bprod_name = true;
        }
            if(qName.equalsIgnoreCase("prod:supply_date")){
                bsypply_date = true;
                }
            if(qName.equalsIgnoreCase("prod:price")) {
                bprice = true;
            }
            if(qName.equalsIgnoreCase("prod:item_weight")) {
                bitem_weight = true;
            }
            if(qName.equalsIgnoreCase("prod:model_year")) {
                bmodel_year = true;
            }
            if(qName.equalsIgnoreCase("prod:size")) {
                bsize = true;
            }
            if(qName.equalsIgnoreCase("prod:color")) {
                bcolor = true;
            }
            if(qName.equalsIgnoreCase("prod:rating")) {
                brating = true;
            }
            if(qName.equalsIgnoreCase("cus:name")) {
                bname = true;
            }
            if(qName.equalsIgnoreCase("cus:second_name")) {
                bsecond_name = true;
            }

            if(qName.equalsIgnoreCase("cus:surname")) {
                bsurname = true;
            }

            if(qName.equalsIgnoreCase("cus:age")){
                bage = true;}

            if(qName.equalsIgnoreCase("cus:email")) {
                bemail = true;
            }
            if(qName.equalsIgnoreCase("cus:password")) {
                bpassword = true;
            }
            if(qName.equalsIgnoreCase("cus:registration_date")) {
                breg_date = true;
            }

    }

    @Override
    public void characters(char[] ch, int start, int length)  {
        String value = new String(ch, start, length);

        if(border_time) {
            try {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = format.parse(value);
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.setTime(date);
                    XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
                    order.setOrderTime(xmlGregCal);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                } catch (DatatypeConfigurationException e) {
                    throw new RuntimeException(e);
                }
            border_time = false;
        }
        else if(bcount){
            orderItem.setCount(Integer.parseInt(value));
            bcount = false;
        }
        else if(bdesc){
            product.setDescription(value);
            bdesc = false;
        }
        else if(bprod_name){
            product.setName(value);
            bprod_name = false;
        }
        else if(bsypply_date){
            try{
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(value);
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(date);
                XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
                product.setSupplyDate(xmlGregCal);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            } catch (DatatypeConfigurationException e) {
                throw new RuntimeException(e);
            }
            bsypply_date = false;
        }
        else if(bprice){
            product.setPrice(Integer.parseInt(value));
            bprice = false;
        }
        else if(bitem_weight){
            BigDecimal bigDecimal = new BigDecimal(value);
            product.setItemWeigth(bigDecimal);
            bitem_weight = false;
        }
        else if(bmodel_year){
            product.setModelYear(Integer.parseInt(value));
            bmodel_year = false;
        }
        else if(bsize){
            product.setSize(value);
            bsize = false;
        }
        else if(bcolor){
            bcolor = false;
            product.setColor(value);
        }
        else if(brating){
            brating = false;
            BigDecimal bigDecimal = new BigDecimal(value);
            product.setRating(bigDecimal);
        }
        else if(bname){
            bname = false;
            customer.setName(value);
        }
        else if(bsecond_name){
            bsecond_name = false;
            customer.setSecondName(value);
        }
        else if (bsurname) {
            bsurname = false;
            customer.setSurname(value);
        }
        else if(bage){
            bage = false;
            customer.setAge(Short.parseShort(value));
        }
        else if(bemail){
            bemail = false;
            customer.setEmail(value);
        }
        else if(bpassword){
            bpassword = false;
            customer.setPassword(value);
        }
        else if(breg_date){
            breg_date = false;
            try{
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(value);
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(date);
                XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
                customer.setRegistrationDate(xmlGregCal);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            } catch (DatatypeConfigurationException e) {
                throw new RuntimeException(e);
            }
        }

        current = "";
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (localName.equals("order")) {
            orders.getOrder().add(order);
        } else if (localName.equals("order_item")) {
            order.addOrderItemListElement(orderItem);
        } else if (localName.equals("customer")) {
            order.setCustomer(customer);
        }
        else if (localName.equals("product")){
            orderItem.setProduct(product);
        }
    }
}
