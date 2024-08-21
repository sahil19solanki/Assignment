package handlers;

import data.Employee;
import interfaces.MyFileHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLFileHandler implements MyFileHandler {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format as needed

    @Override
    public void read(MyCollection myCollection) {
        File xmlFile = new File("/Users/sahilsolanki/Documents/output.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        List<Employee> employeeList = new ArrayList<>();

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("employee");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Extract data from XML elements
                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String dateOfBirthStr = element.getElementsByTagName("dateOfBirth").item(0).getTextContent();
                    double experience = Double.parseDouble(element.getElementsByTagName("experience").item(0).getTextContent());

                    // Parse date of birth
                    Date dateOfBirth;
                    try {
                        dateOfBirth = DATE_FORMAT.parse(dateOfBirthStr);
                    } catch (ParseException e) {
                        // Handle parse exception, possibly set a default value or log an error
                        dateOfBirth = null;
                    }

                    // Create an Employee object
                    Employee employee = new Employee();
                    employee.setFirstName(firstName);
                    employee.setLastName(lastName);
                    employee.setDateOfBirth(dateOfBirth);
                    employee.setExperience(experience);

                    // Add employee to the list and collection
                    employeeList.add(employee);
                    myCollection.add(employee);
                }
            }

            // Print out employee details
            for (Employee employee : employeeList) {
                System.out.println(employee.getFirstName() + " " + employee.getLastName() +
                        ", DOB: " + (employee.getDateOfBirth() != null ? DATE_FORMAT.format(employee.getDateOfBirth()) : "N/A") +
                        ", Experience: " + employee.getExperience());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(MyCollection myCollection) {
        // Implement write functionality if needed
    }
}
