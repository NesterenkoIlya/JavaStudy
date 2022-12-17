package validation;

import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

public class XMLValidator {

    public boolean isValid(Source xml, Schema schema) {
        Validator validator = schema.newValidator();

        try {
            validator.validate(xml);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

