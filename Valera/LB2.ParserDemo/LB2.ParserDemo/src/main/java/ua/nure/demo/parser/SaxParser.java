public class SaxParser extends DefaultHandler {
	private static boolean logEnabled = true;
	
	public static void log(Object o) {
		if (logEnabled) {
			System.out.println(o);
		} 
	}
	private String current;
	private Catalogs catalogs; 
	private Catalog catalog;
	private Games pr;
	private Category substr;
	public Catalogs getCatalogs() {
		return catalogs; 
	}
	@Override
	public void error(org.xml.sax.SAXParseException e) throws SAXException {
		throw e; //если не валиден хмл
	};
	public List<Catalog> parse(InputStream in) throws ParserConfigurationException, 
	SAXException, IOException{
		List <Catalog> catalogs = new ArrayList<>();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		//validation
		factory.setFeature("http://xml.org/sax/features/validation", true);
		factory.setFeature("http://apache.org/xml/features/validation/schema", true);
		javax.xml.parsers.SAXParser parser = factory.newSAXParser();
		parser.parse(in, this);
		return catalogs;
	} 
	@Override
	public void startElement(String uri, String localName, String qName, Attributes 
			attributes) throws SAXException{
		System.out.println(localName);
		current = localName;
		if("catalogs".equals(current)){
			catalogs=new Catalogs(); 
			return; 
		}
		if ("catalog".equals(current)) {
			catalog = new Catalog();
			return; 
		}
		if ("Games".equals(current)) {
			pr = new Games();
			return;
		} 
		if ("Categories".equals(current)) {
			substr = new Categories();
			return; 
			}
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String elementText = new String(ch, start, length).trim();

		if (elementText.isEmpty()) {
			System.out.println("empty");
			return; 
		}
		if ("title".equals(current)) {
			pr.setTitle(new String(ch, start, length).trim());
			return; 
		}
		if ("description".equals(current)) {
			pr.setDescription(new String(ch, start, length).trim());
			return; 
		}

		if ("category".equals(current)) {
			pr.setCategory(Category.fromValue(new String(ch, start, 
					length).trim()));
			return; 
		}
		if ("price".equals(current)) {
			pr.setPrice(new BigDecimal(new String(ch, start, length).trim()));
			return; 
		}
		if ("name".equals(current)) {
			substr.setName(new String(new String(ch, start, length).trim()));
			return; 
		}
		if ("desc".equals(current)) {
			substr.setDesc(new String(new String(ch, start, length).trim()));
			return; 
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws
	SAXException {
		if ("Catalog".equals(localName)) {
			catalogs.getCatalog().add(catalog);
			return; 
		}
		if ("Games".equals(localName)) {
			catalog.getGames().add(pr);
			return; 
		}
		if ("Categories".equals(localName)) {
			catalog.getCategories().add(substr);
			return;
		} 
	}
	public static void main(String[] args) throws FileNotFoundException, 
	ParserConfigurationException, SAXException, IOException, TransformerException{
		SaxParser parser = new SaxParser();
		parser.parse(new FileInputStream("Games.xml"));
		Catalogs catalogs = parser.getCatalogs();
		String outputXmlFile = "sax.xml";
		DomParser.Save(catalogs, outputXmlFile);
		System.out.println("Sax parser");
		System.out.println("---------------------");
		System.out.println("Result: \n" +catalogs);
		System.out.println("---------------------"); 
	}
}