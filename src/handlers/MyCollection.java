package handlers;

import data.Employee;

import java.util.ArrayList;
import java.util.List;

public class MyCollection {
    List<Employee> employees ;
    public int writeCounter;
    public static int readCounter;
    public MyCollection(){
        employees = new ArrayList<>();
    }
     synchronized void add(Employee e){
        synchronized (e){
            employees.add(e);
            writeCounter++;
        }
    }
    Employee getEmployee(){
        return  employees.get(readCounter++);
    }

    public List<Employee> getAll() {
        return employees;
    }
}
