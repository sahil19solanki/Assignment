package handlers;

import data.Employee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import interfaces.MyFileHandler;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonFileHandler implements MyFileHandler {
    @Override
    public void read(MyCollection myCollection) {
        Gson gson = new Gson();

        // Define the TypeToken for List<Employee>
        Type employeeListType = new TypeToken<List<Employee>>(){}.getType();

        try (FileReader reader = new FileReader("/Users/sahilsolanki/Documents/jsonMockData.json")) {
            // Deserialize JSON into a List of Employee objects
            List<Employee> employees = gson.fromJson(reader, employeeListType);
            // Iterate through the list and print each employee's details
            for (Employee employee : employees) {
                System.out.println(employee.getFirstName() + " " + employee.getLastName() +
                        ", DOB: " + employee.getDateOfBirth() +
                        ", Experience: " + employee.getExperience());
                myCollection.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(MyCollection myCollection) {
        // Implement write functionality if needed
    }
}
