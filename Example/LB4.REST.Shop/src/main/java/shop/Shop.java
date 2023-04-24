public class Shop {

	private Shop shop = Shop.getInstance();

	@GET
	public List<Appliance> SelectByCategory(@MatrixParam("category") String category) {
	
		List<Appliance> list = shop.all(title, category)
				.stream()
				.map(p -> p.getAppliance())
				.collect(Collectors.toList());
		return list;
	}
@GET
	public List<Appliance> getAppliance() {
		List<Appliance> list = shop.all(title, category)
				.stream()
				.map(p -> p.getAppliance())
				.collect(Collectors.toList());
		return list;
	}
@PUT
	public string addtoBasket(@MatrixParam("Appliance") List<Appliance> pt) {
		
	    StringBuilder builder = new StringBuilder();
		for(int i=0; i<pt.size(); i++)
		{
			pt[i].builder.append("Basket [title=");
			pt[i].builder.append(title);
			pt[i].builder.append(", count=");
			pt[i].builder.append(1);	
			pt[i].builder.append("]");
				
				}
			}
return builder.toString();

	}
	@PUT
	public string addAppliance(@MatrixParam("Applaince") List<Appliance> app) {
		
app.add(new Appliance("Name",null, Category.SECOND, 10000.00));

	    StringBuilder builder = new StringBuilder();
		for(Appliance ap:list) {
			ap.builder.append("Appliance [");
			if (title != null) {
				ap.builder.append("title=");
				ap.builder.append(title);
				ap.builder.append(", ");
			}
			if (desc != null) {
				ap.builder.append("desc=");
				ap.builder.append(desc);
				ap.builder.append(", ");
			}
			ap.builder.append("price=");
			ap.builder.append(price);
			ap.builder.append(", ");
			if (category != null) {
				ap.builder.append("category=");
				ap.builder.append(category);
				ap.builder.append(", ");
			}
			builder.append("]");
			return builder.toString();
		}

	}
@DELETE
	public string deleteAppliance(@MatrixParam("Appliance") List<Appliance> app) {
		
app.remove(3);

	    StringBuilder builder = new StringBuilder();
		for(Appliance ap:list) {
			ap.builder.append("Appliance [");
			if (title != null) {
				ap.builder.append("title=");
				ap.builder.append(title);
				ap.builder.append(", ");
			}
			if (desc != null) {
				ap.builder.append("desc=");
				ap.builder.append(desc);
				ap.builder.append(", ");
			}
			ap.builder.append("price=");
			ap.builder.append(price);
			ap.builder.append(", ");
			if (category != null) {
				ap.builder.append("category=");
				ap.builder.append(category);
				ap.builder.append(", ");
			}
			builder.append("]");
			return builder.toString();
		}


	}

}