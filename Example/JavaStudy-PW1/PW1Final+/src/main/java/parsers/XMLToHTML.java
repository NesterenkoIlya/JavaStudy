package parsers;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

public class XMLToHTML {

    public void convert(Source xml, Source xslt, OutputStream resultHTML ) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer(xslt);
        StreamResult html = new StreamResult(resultHTML);
        transformer.transform(xml, html);
    }
}
