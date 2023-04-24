public class SelectByCategory
{
	static private Vector<Appliance> list;
	
	public string getByCategory(string cat)
	{
		StringBuilder builder = new StringBuilder();
		for(Appliance ap:list) {
			if(ap.category==cat)
			{
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

			else return "Такой категории нет";
		}	
	}
	
	private void init()
	{
		list = new Vector();
		list.add(new Appliance("ELENBERG MR 84",null, Category.FIRST, 20000.00));
		list.add(new Appliance("INDESIT LI6 S1E W",null, Category.FIRST, 30000.00));
		list.add(new Appliance("ELENBERG MS 2081 D",null, Category.SECOND, 10000.00));
	}
}