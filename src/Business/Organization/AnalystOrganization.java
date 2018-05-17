/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.AnalystEmployeeRole;
import Business.Role.BankEmployeeRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 *
 */
public class AnalystOrganization extends Organization {
    
    public AnalystOrganization() {
        super(Organization.AnalystType.AnalystOrganization.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles=new ArrayList();
        roles.add(new AnalystEmployeeRole());
        return roles;
    }
}
