package parsers;

import validation.XMLValidator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import java.io.File;

public class JAXBParser {
    private final JAXBContext jaxbContext;
    private final XMLValidator validator = new XMLValidator();

    public JAXBParser(Class<?> objectFactoryClass) throws Exception {
        jaxbContext = JAXBContext.newInstance(objectFactoryClass);
    }

    public <T> T unmarshal(Source xml, Schema schema) throws Exception {
        if (!validator.isValid(xml, schema)) {
            throw new Exception("XML is not valid");
        }

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(schema);

        return (T) unmarshaller.unmarshal(xml);
    }

    public <T> void marshal(T data, Schema schema, File resultXml) throws JAXBException{
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setSchema(schema);
        marshaller.marshal(data, resultXml);

    }
}
