public class Shop {

	private Shop shop = Shop.getInstance();

	@GET
	public List<Computer> search(@MatrixParam("category") String category) {
	
		List<Computer> list = shop.all(title, category)
				.stream()
				.map(p -> p.getComputer())
				.collect(Collectors.toList());
		return list;
	}
@GET
	public List<Сomputer> getComputer() {
		List<Computer> list = shop.all(title, category)
				.stream()
				.map(p -> p.getComputer())
				.collect(Collectors.toList());
		return list;
	}
	@PUT
	public string addComputer(@MatrixParam("computer") List<Computer> computer) {
		
computer.add(new Computer("Comp1",null, Category.TWO, 5000.00));

	    StringBuilder builder = new StringBuilder();
		for(Computer computer:list) {
			computer.builder.append("Computer [");
			if (title != null) {
				computer.builder.append("title=");
				computer.builder.append(title);
				computer.builder.append(", ");
			}
			if (desc != null) {
				computer.builder.append("desc=");
				computer.builder.append(desc);
				computer.builder.append(", ");
			}
			computer.builder.append("price=");
			computer.builder.append(price);
			computer.builder.append(", ");
			if (category != null) {
				computer.builder.append("category=");
				computer.builder.append(category);
				computer.builder.append(", ");
			}
			builder.append("]");
			return builder.toString();
		}

	}

@DELETE
	public string deleteComputer(@MatrixParam("computer") List<Computer> computer) {
		
computer.remove(3);

	    StringBuilder builder = new StringBuilder();
		for(Computer computer:list) {
			computer.builder.append("Computer [");
			if (title != null) {
				computer.builder.append("title=");
				computer.builder.append(title);
				computer.builder.append(", ");
			}
			if (desc != null) {
				computer.builder.append("desc=");
				computer.builder.append(desc);
				computer.builder.append(", ");
			}
			computer.builder.append("price=");
			computer.builder.append(price);
			computer.builder.append(", ");
			if (category != null) {
				computer.builder.append("category=");
				computer.builder.append(category);
				computer.builder.append(", ");
			}
			builder.append("]");
			return builder.toString();
		}

	}

@POST
	public string updateComputer(@MatrixParam("computer") List<Computer> computer) {
		
 computer.set(0, new Computer("Comp",null, Category.Two, 5000.00));
	    StringBuilder builder = new StringBuilder();
		for(Computer computer:list) {
			computer.builder.append("Computer [");
			if (title != null) {
				computer.builder.append("title=");
				computer.builder.append(title);
				computer.builder.append(", ");
			}
			if (desc != null) {
				computer.builder.append("desc=");
				computer.builder.append(desc);
				computer.builder.append(", ");
			}
			computer.builder.append("price=");
			computer.builder.append(price);
			computer.builder.append(", ");
			if (category != null) {
				computer.builder.append("category=");
				computer.builder.append(category);
				computer.builder.append(", ");
			}
			builder.append("]");
			return builder.toString();
		}

	}



}