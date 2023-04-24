package shop.service;

import java.util.List;

import shop.entity.Product;

public class ProductLine {

	private Restaurant restaurant = Restaurant.getInstance();

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Product> getdishes() {
		System.out.println("category" + category + " name: "+title);
		List<Product> list = restaurant.all(title, category)
				.stream()
				.map(p -> p.getProduct())
				.collect(Collectors.toList());
		return list;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Product> search(@MatrixParam("category") String category) {
		System.out.println("search for category: " + category + " name: "+title);
		List<Product> list = restaurant.all(title, category)
				.stream()
				.map(p -> p.getProduct())
				.collect(Collectors.toList());
		return list;
	}
	
	@PUT
	public string addtoBasket(@MatrixParam("product") List<Product> pr) {
		
	    StringBuilder builder = new StringBuilder();
		for(int i=0; i<pr.size(); i++)
		{
			pr[i].builder.append("Basket [title=");
			pr[i].builder.append(title);
			pr[i].builder.append(", count=");
			pr[i].builder.append(count);			
			pr[i].builder.append("]");
				
				}
			return builder.toString();
		}
		return list;
	}

	@PUT
	public string createOrder(@MatrixParam("basket") Basket basket) {
	
		StringBuilder builder = new StringBuilder();
		builder.append("Order [");
		builder.append(basket);
		builder.append(", status");
		builder.append(status.RESEIVED);			
		builder.append("]");

	return builder.toString();
}
	@POST
	public string changeStatus(@MatrixParam("order") Order order, @MatrixParam("status") string st) {
	
		order.status = st;
		StringBuilder builder = new StringBuilder();
		builder.append("Order [");
		builder.append(order.basket);
		builder.append(", status");
		builder.append(st);			
		builder.append("]");

	return builder.toString();
}

@PUT

public string Basket()
{
	StringBuilder builder = new StringBuilder();
		builder.append("Order [");
		builder.append(basket);
		builder.append(", status");
		builder.append(status.RESEIVED);			
		builder.append("]");

	return builder.toString();
	
	
}
	

}

}