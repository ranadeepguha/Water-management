/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.CentralBodyEmployeeRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 *
 */
public class CentralBodyOrganization extends Organization {

    public CentralBodyOrganization() {
        super(Organization.WaterType.CentralBodyOrganization.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new CentralBodyEmployeeRole());
        return roles;
    }
}
