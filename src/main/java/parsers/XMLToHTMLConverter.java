package parsers;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

public class XMLToHTMLConverter {

    public void convert(Source xml, Source xslt, OutputStream resultHtml) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer(xslt);
        StreamResult html = new StreamResult(resultHtml);
        transformer.transform(xml, html);
    }
}
