package handlers;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import data.Employee;
import interfaces.MyFileHandler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CSVFileHandler implements MyFileHandler {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); // Update format as needed

    @Override
    public void read(MyCollection myCollection) {
        String csvFile = "/Users/sahilsolanki/Documents/MOCK_DATA.csv";

        try (FileReader fileReader = new FileReader(csvFile);
             CSVReader csvReader = new CSVReader(fileReader)) {

            String[] header = csvReader.readNext(); // Read header line (if needed)

            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                // Assuming the order of columns in the CSV file matches the Employee fields
                String firstName = nextRecord[0];
                String lastName = nextRecord[1];
                String dateOfBirthStr = nextRecord[2];
                double experience = Double.parseDouble(nextRecord[3]);

                // Parse date of birth
                Date dateOfBirth;
                try {
                    dateOfBirth = DATE_FORMAT.parse(dateOfBirthStr);
                } catch (ParseException e) {
                    // Handle parse exception, possibly set a default value or log an error
                    dateOfBirth = null;
                }

                Employee employee = new Employee();
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setDateOfBirth(dateOfBirth);
                employee.setExperience(experience);
                myCollection.add(employee);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void write(MyCollection myCollection) {
        String csvFile = "/Users/sahilsolanki/Documents/Written/output.csv";
        List<Employee> employees = myCollection.getAll(); // Assumes MyCollection has a getAll() method
        int limit = Math.min(employees.size(), 100); // Limit to 100 records

        try (FileWriter fileWriter = new FileWriter(csvFile);
             CSVWriter csvWriter = new CSVWriter(fileWriter)) {

            // Write header to CSV file
            String[] header = { "firstName", "lastName", "dateOfBirth", "experience" };
            csvWriter.writeNext(header);

            // Write employee data to CSV file
            for (int i = 0; i < limit; i++) {
                Employee employee = employees.get(i);
                String[] record = {
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDateOfBirth() != null ? DATE_FORMAT.format(employee.getDateOfBirth()) : "N/A",
                        String.valueOf(employee.getExperience())
                };
                csvWriter.writeNext(record);
                myCollection.getEmployee();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
