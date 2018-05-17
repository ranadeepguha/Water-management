/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Employee;

import java.util.ArrayList;

/**
 *
 *
 */
public class EmployeeDirectory {
    
    private ArrayList<Employee> employeeList;

    public EmployeeDirectory() {
        employeeList=new ArrayList();
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    
//    public Employee createAndAddEmployee(String name){
//        Employee employee=new Employee();
//        employee.setName(name);
//        employeeList.add(employee);
//        return employee;
//    }

    public Employee createEmployee(String name) {
        Employee employee = new Employee();
        employee.setName(name);
        employeeList.add(employee);
        return employee;
    }
    
    public void deleteEmployee(Employee emp){
        employeeList.remove(emp);
    }
}
