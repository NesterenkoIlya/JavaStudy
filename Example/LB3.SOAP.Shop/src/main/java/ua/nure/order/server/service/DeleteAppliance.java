public class DeleteAppliance
{
	static private Vector<Appliance> list;

   list.remove(3);

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