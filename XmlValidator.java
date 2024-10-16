import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XmlValidator {

    public static boolean validateXMLSchema(File xmlFile, File xsdFile) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
            System.out.println("XML файл корректен.");
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка валидации XML файла: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        File xmlFile = new File("generated_output.xml");  // ваш XML-файл
        File xsdFile = new File("schema.xsd");  // ваш XSD-файл

        boolean isValid = validateXMLSchema(xmlFile, xsdFile);
        if (isValid) {
            System.out.println("XML файл прошел валидацию по XSD.");
        } else {
            System.out.println("XML файл не прошел валидацию по XSD.");
        }
    }
}
