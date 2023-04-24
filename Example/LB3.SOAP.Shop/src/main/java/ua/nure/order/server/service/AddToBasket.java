public class AddToBasket
{
	static private Vector<LED> list;

	public string Basket()
	{
		StringBuilder builder = new StringBuilder();
		for(Appliance ap: list) {
			builder.append("Basket [title=");
			builder.append(title);
			builder.append(", count=");
			builder.append(1);			
			builder.append("]");
			
			}
		return builder.toString();
		
		
	}
	
	private void init()
	{
		list = new Vector();
		list.add(new Appliance("ELENBERG MR 84",null, Category.FIRST, 20000.00));
		list.add(new Appliance("INDESIT LI6 S1E W",null, Category.FIRST, 30000.00));
		list.add(new Appliance("ELENBERG MS 2081 D",null, Category.SECOND, 10000.00));

	}
}