//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.04.02 at 03:03:46 PM EEST 
//


package shop.order;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import shop.entity.Product;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Order", propOrder = {
    "product",
    "count"
})
public class Order {

    @XmlElement(required = true)
    protected Product product;
    protected int count;
    protected Status status;

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product value) {
        this.product = value;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int value) {
        this.count = value;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [product=");
		builder.append(product);
		builder.append(", count=");
		builder.append(count);
		builder.append(", status=");
		builder.append(status);
		return builder.toString();
	}

}