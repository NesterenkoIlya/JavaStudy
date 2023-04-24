public class Client {
	public static void main(String[] args)
	{
		ApplianceService pt = new ApplianceServise().getApplianceServicePort();
		List<Appliance> list = new List<Applince>();
		list.add(new Appliance("ELENBERG MR 84",null, Category.FIRST, 20000.00));
		list.add(new Appliance("INDESIT LI6 S1E W",null, Category.FIRST, 30000.00));
		list.add(new Appliance("ELENBERG MS 2081 D",null, Category.SECOND, 10000.00));

		var variable = pt.getAllApplainces();
		for(var v:variable)
		{
			System.out.println("Appliance name: "+v.title+", category:"+v.category);
		}
		
		var variable2 = pt.SearchByCategory("Холодильники");
		for(var v:variable2)
		{
			System.out.println("Appliance name: "+v.title+", category:"+v.category);
		}
		
		List<Basket> list1 = new List<Basket>();
		list1.add(new Basket(new Appliance("ELENBERG MR 84",null, Category.FIRST, 20000.00)),1);
		list1.add(new Basket(new Appliance("INDESIT LI6 S1E W",null, Category.FIRST, 30000.00)),1);
		list1.add(new Basket(new Appliance("ELENBERG MS 2081 D",null, Category.SECOND, 10000.00)),1);
		
		System.out.println(addToBasket(list1));
		
		pt.addAppliance();
pt.deleteappliance();
		
	}
}