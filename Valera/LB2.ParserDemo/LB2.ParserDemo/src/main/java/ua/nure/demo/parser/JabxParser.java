public class JaxbParser {
	public static Catalogs loadCatalogs(final String xmlFile,
			final String xsdFileName, Class<?> objectFactory) throws
	JAXBException, SAXException {
		
		JAXBContext jc = JAXBContext.newInstance(objectFactory);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		if (xsdFileName != null) { 
			Schema schema = null;
			if ("".equals(xsdFileName)) {
				schema = sf.newSchema();
			} else { 
				schema = sf.newSchema(new File(xsdFileName));
			}
			unmarshaller.setSchema(schema); 
			
			marshaller.setEventHandler(new ValidationEventHandler() {
				// this method will be invoked if XML is NOT valid
				@Override
				public boolean handleEvent(ValidationEvent event) {
					// at this point we have NOT valid XML document
					// just print message
					System.err.println("====================================");
					System.err.println(xmlFile + " is NOT valid against "+ xsdFileName + ":\n" + event.getMessage());
					System.err.println("====================================");
					// if we place 'return false;' unmarshal method throws
					// exception
					// 'return true;' does not imply any exceptions
					return true; 
				}
			});
		}

		Catalogs catalogs = (Catalogs) unmarshaller.unmarshal(new File(xmlFile));
		return catalogs; 
	}
	
	public static void saveCatalogs (Catalogs catalogs, final String xmlFile,
			final String xsdFile, Class<?> objectFactory) throws JAXBException, 
	SAXException {
		JAXBContext jc = JAXBContext.newInstance(objectFactory);
		Marshaller marshaller = jc.createMarshaller();
		// obtain schema
		SchemaFactory sf = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		// setup validation against XSD
		if (xsdFile != null) {
			Schema schema = sf.newSchema(new File(xsdFile));
			marshaller.setSchema(schema);
			marshaller.setEventHandler(new ValidationEventHandler() {
				@Override
				public boolean handleEvent(ValidationEvent event) {
				// at this point we have NOT valid XML document
				// just print message
				System.err.println("====================================");
				System.err.println(xmlFile + " is NOT valid against "
				+ xsdFile + ":\n" + event.getMessage());
				System.err.println("====================================");
				// if we place 'return false;' marshal method throws
				// exception
				// 'return true;' does not imply any exceptions
				return false; }
			});
		}
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.nure.ua/entity/Games/Games.xsd");
		marshaller.marshal(catalogs, new File(xmlFile));
	}
	
	public static void main1(String[] args) throws JAXBException, 
	FileNotFoundException {
		JAXBContext jc = 
				JAXBContext.newInstance(ua.nure.entity.dishesrest.ObjectFactory.class);
		Unmarshaller um = jc.createUnmarshaller();
		Catalogs catalogs = (Catalogs) um.unmarshal(new
				FileInputStream("Games.xml"));
		System.out.println(catalogs);
	}
	
	public static void main(String[] args) throws JAXBException, SAXException {
		// load Orders object from NOT valid XML (success, just prints validation
		// warning)
		Catalogs catalogs = loadCatalogs("Games.xml", "Games.xsd", ua.nure.entity.dishesrest.ObjectFactory.class);
		// we have Orders object at this point
	System.out.println("Jaxb parser");
		System.out.println("---------------------");
		System.out.println("Result: \n" + catalogs);
		System.out.println("---------------------");
		try {
			saveCatalogs(catalogs, "Games.xml" + ".jaxb.xml", 
					"Games.xsd", ua.nure.entity.shop.ObjectFactory.class);
		} 
		catch (Exception ex) {
			System.err.println("====================================");
			System.err.println("Object tree not valid against XSD.");
			System.err.println(ex.getClass().getName());
			System.err.println("====================================");
		}
	} 
}