/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Business.Employee.Employee;
import Business.Role.SystemAdminRole;
import Business.UserAccount.UserAccount;

/**
 *
 *
 */
public class ConfigureASystem {

    public static EcoSystem intializeBusiness() {
        EcoSystem system = EcoSystem.getInstance();

        Employee employee = system.getEmployeeDirectory().createEmployee("Admin");

        UserAccount ua = system.getUserAccountDirectory().createAndAddUserAccount("admin", "admin123", employee, new SystemAdminRole());

        return system;
    }
}
