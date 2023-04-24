@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Computer", propOrder = {
    "title",
    "price",
    "category",
    "description"
})
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Computer.class),
})
public abstract class Computer
    extends Entity
{

    @XmlElement(required = true)
    protected String title;
    protected double price;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String category;
    protected String description;

    public Computer() {
		super();
	}

    public Computer(String params) {
    	System.out.println(params);
    }
    
	public Computer(Long id, String title, double price, String category, String description) {
		super(id);
		this.title = title;
		this.price = price;
		this.category = categories;
		this.description = description;
	}
    public String getTitle() {
        return title;
    }
    public void setTitle(String value) {
        this.title = value;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double value) {
        this.price = value;
    }
  
    public String getDescription() {
        return description;
    }
    public void setDescription(String value) {
        this.description = value;
    }

}