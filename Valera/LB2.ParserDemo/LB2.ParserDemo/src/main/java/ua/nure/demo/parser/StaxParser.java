public class StaxParser extends DefaultHandler{
	private String xmlFile;
	private Catalogs catalogs;
	public Catalogs getCatalogs() {
		return catalogs;
	}

	public StaxParser(String xmlFile) {
		this.xmlFile = xmlFile;
	}
	public void parse() throws XMLStreamException{
		Catalog catalog = null;
		Games pr = null;
		Categories substr=null;
		String current = null;
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE,true);
		XMLEventReader reader = factory.createXMLEventReader(newStreamSource(xmlFile));
		
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isCharacters()&& event.asCharacters().isWhiteSpace()) {
				continue; 
			}
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				current = startElement.getName().getLocalPart();
				if("catalogs".equals(current)) {
					catalogs=new Catalogs();
					continue; }
				if("catalog".equals(current)) {
					catalog=new Catalog();
					continue; 
				}
				if("Games".equals(current)) {
					pr=new Games();
					continue; 
				}
				if("Categories".equals(current)) {
					substr=new Categories();
					continue; 
					} 
				}
			if (event.isCharacters()) {
				Characters characters = event.asCharacters();
				if ("title".equals(current)) {
					pr.setTitle(characters.getData());
					continue; 
				}
				if ("description".equals(current)) {
					pr.setDescription(characters.getData());
					continue; 
				}
				if ("category".equals(current)) {
					pr.setCategory(Category.fromValue(characters.getData()));
					continue; 
				}
				if ("price".equals(current)) {
					pr.setPrice(new BigDecimal(characters.getData()));
					continue;
				} 
				if ("name".equals(current)) {
					substr.setName(characters.getData());
					continue;
				} 
				if ("desc".equals(current)) {
					substr.setDesc(characters.getData());
					continue;
				} 

				if(event.isEndElement()) { 
					EndElement endElem = event.asEndElement();
					String localName = endElem.getName().getLocalPart();
					if ("сatalog".equals(localName)) {
						catalogs.getCatalog().add(catalog);
						continue; 
					}
					if ("Games".equals(localName)) {
						catalog.getGames().add(pr);
					}

					
				} 
			}
			reader.close();
		}
		
		public static void main(String[] args) throws Exception {
			taxParser parser = new StaxParser("Games.xml");
			parser.parse(); 
			// obtain container
			Catalogs catalogs = parser.getCatalogs();
			String outputXmlFile = "stax.xml";
			DomParser.Save(catalogs, outputXmlFile);
			// we have Test object at this point:
			System.out.println("Stax parser");
			System.out.println("---------------------");
			System.out.println("Result: \n" +catalogs);
			System.out.println("---------------------"); }
	}
}
