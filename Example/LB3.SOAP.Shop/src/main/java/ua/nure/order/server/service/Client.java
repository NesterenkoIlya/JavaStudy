public class Client {
	public static void main(String[] args)
	{
		SelectAllAppliances all = new SelectAllAppliances();
		all.getAllAppliances();
		
		SelectByCategory bycateg = new SelectByCategory();
		bycateg.getByCategory("Холодильники");
		
		AddToBasket basket = new AddToBasket();
		basket.Basket();
		
AddAppliance addAppliance = new AddAppliance();
		addAppliance.addAppliance();
		
		DeleteAppliance deleteAppliance = new DeleteAppliance();
		deleteAppliance.deleteAppliance();
	}
}