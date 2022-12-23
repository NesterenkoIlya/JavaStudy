@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Category", propOrder = {
		"name",
		"desc",
})
public class Category
	extends Entity
{
	@XmlElement(required = true)
	protected String name;
	@XmlElement(required = true)
	protected String desc;

	public String getName() {
		return name;
	}
	public void setName(String value) {
		this.name = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String value) {
		this.desc = value;
	}
	@Override
	public String toString() {
		return "Category [name=" + name + ", desc=" + desc +  "]"; 
		} 
}
