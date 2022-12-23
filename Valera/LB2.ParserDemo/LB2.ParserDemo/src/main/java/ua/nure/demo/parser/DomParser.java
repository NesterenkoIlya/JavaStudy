public class DomParser {
	private static boolean logEnabled = false;
	
	public static void log(Object o) {
		if(logEnabled) {
			System.out.println(o);
		} 
	}
	private Catalog parseCatalog(Node node) {
		Catalog catalog = new Catalog();
		NodeList nodes = node.getChildNodes();

		for(int i=0;i<nodes.getLength();i++) {
			Node item = nodes.item(i);
			log(item.getLocalName());
			if("Games".equals(item.getLocalName())) 
			{
				catalog.getGames().add(parseGames(item));
			}
			if("Catalog".equals(item.getLocalName())) 
			{
				catalog.getCatalog().add(parseCatalog(item));
			}

			return catalog; 
		}
		
		private Games parseGames(Node node) { 
			Games pr = new Games();
			if(node.hasAttributes()) {
				NamedNodeMap atrb = node.getAttributes();
				Node item = atrb.getNamedItem("id");
				log(item.getLocalName()+ "=" +item.getTextContent());
				pr.setId(Integer.parseInt(item.getTextContent()));
			}
			
			NodeList nodes = node.getChildNodes();
			for(int i=0;i<nodes.getLength();i++) {
				Node item = nodes.item(i);
				log(item.getLocalName());
				if("title".equals(item.getLocalName())) {
					log(item.getLocalName() + "=" + item.getTextContent());
					pr.setTitle(item.getTextContent());
				} 
				else if ("description".equals(item.getLocalName())) {
					log(item.getLocalName() + " = " + item.getTextContent());
					pr.setDescription(item.getTextContent());
				}
				else if ("category".equals(item.getLocalName())) {
					log(item.getLocalName() + " = " + item.getTextContent());
					pr.setCategory(Category.fromValue(item.getTextContent()));
				} 
				else if ("price".equals(item.getLocalName())) {
					log(item.getLocalName() + " = " + item.getTextContent());
					pr.setPrice(new BigDecimal(item.getTextContent()));
				} 
			}
			return pr; 
		}
		
		private Catalogs parseCatalogs(Node node) {
			Catalogs sbstr = new Catslogs();
			if(node.hasAttributes()) {
				NamedNodeMap atrb = node.getAttributes();
				Node item = atrb.getNamedItem("id");
				log(item.getLocalName()+ "=" +item.getTextContent());
				sbstr.setId(Integer.parseInt(item.getTextContent()));
			}
			NodeList nodes = node.getChildNodes();
			for(int i=0;i<nodes.getLength();i++) {
				Node item = nodes.item(i);
				log(item.getLocalName());
				if("name".equals(item.getLocalName())) {
					log(item.getLocalName() + "=" + item.getTextContent());
					sbstr.setName(item.getTextContent());
				} 
				else if ("desc".equals(item.getLocalName())) {
					log(item.getLocalName() + " = " + item.getTextContent());
					sbstr.setVolume(new BigDecimal(item.getTextContent()));
				} 
			}
			return sbstr; 
		}

		public Catalogs parse(InputStream in) throws ParserConfigurationException, SAXException, IOException {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			dbf.setFeature("http://xml.org/sax/features/validation", true);
			dbf.setFeature("http://apache.org/xml/features/validation/schema", true);
			DocumentBuilder db=dbf.newDocumentBuilder();
			db.setErrorHandler(new DefaultHandler() {
				@Override
				public void error(SAXParseException e) throws SAXException{
					throw e; 
				}
			});
			
			Document root = db.parse(in);
			Catalogs catalogs = new Catalogs();
			Element e = root.getDocumentElement();
			NodeList xmlCatalogs=e.getElementsByTagName("tns:catalog");
			for (int i=0;i<xmlCatalogs.getLength();i++) {
				catalogs.getCatalog().add(parseCatalog(xmlCatalogs.item(i)));
			}
			return catalogs; 
		}
		
		public static Document getDocument(Catalogs catalogs) throws
	ParserConfigurationException{
			DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc =db.newDocument();
			Element catalogsElement = doc.createElement("catalogs");
			doc.appendChild(catalogsElement);
			for(Catalog catalog: catalogs.getCatalog()) {
				Element catalogElement = doc.createElement("catalog");
				catalogsElement.appendChild(catalogElement);
				for(Games pr:catalog.getGames()) {
					Element gamesElement=doc.createElement("Games");
					catalogElement.appendChild(gamesElement);
					
					Element titleElement=doc.createElement("title");
					titleElement.setTextContent(pr.getTitle());
				gamesElement.appendChild(titleElement);
					
					Element descriptionElement=doc.createElement("description");
					descriptionElement.setTextContent(pr.getDescription());
					gamesElement.appendChild(descriptionElement);

					Element categoryElement=doc.createElement("category");
					categoryElement.setTextContent (pr.getCategory().toString());
					gamesElement.appendChild(categoryElement);
					
					Element priceElement = doc.createElement("price");
					priceElement.setTextContent(pr.getPrice().toString());
					gamesElement.appendChild(priceElement);
				} 
				for(Catalogs substr: catalog.getSubstrates()) {
					Element catalogElement = doc.createElement("Catalogs");
					catalogElement.appendChild(catalogElement);
					
					Element nameElement = doc.createElement("name");
					nameElement.setTextContent(substr.getName());
					catalogElement.appendChild(nameElement);
					
					Element descElement = doc.createElement("desc");
					descElement.setTextContent(substr.getName());
					catalogElement.appendChild(descElement);
				}
			} 
		}
		return doc;
	} 
	public static void Save(Catalogs catalogs, String xmlFile) throws
		ParserConfigurationException, TransformerException {
		Save(getDocument(catalogs), xmlFile);
	}
	public static void Save (Document doc, String xmlFile) 
			throws TransformerException {
		StreamResult result = new StreamResult(new File(xmlFile));
		// set up transformation
		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		// run transformation
		t.transform(new DOMSource(doc), result);
	}
	public static void main(String[] args) throws
ParserConfigurationException,IOException,SAXException, TransformerException{
		DomParser dom = new DomParser();
		InputStream in = new FileInputStream("Games.xml");

		Catalogs catalogs=dom.parse(in);
		String outputXmlFile = "Dom.xml";
		DomParser.Save(catalogs, outputXmlFile);
		// we have Test object at this point:
			System.out.println("Dom parser");
			System.out.println("---------------------");
			System.out.println("Result: \n" +catalogs);
			System.out.println("---------------------");
		} 
	}