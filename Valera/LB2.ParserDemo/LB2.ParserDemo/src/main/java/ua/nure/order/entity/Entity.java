@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entity")
@XmlSeeAlso({
	Catalog.class,
	Dishes.class
})
public class Entity {
	@XmlAttribute(name = "id")
	protected Integer id;
	public Integer getId() {
		return id;
	}
public void setId(Integer value) {
	 this.id = value;
 	}
}
