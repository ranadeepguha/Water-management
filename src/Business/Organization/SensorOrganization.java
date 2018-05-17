/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.Role;
import Business.Role.StateBodyEmployeeRole;
import java.util.ArrayList;

/**
 *
 *
 */
public class SensorOrganization extends Organization{

    public SensorOrganization() {
        super(Organization.SensorType.SensorOrganization.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
//        ArrayList<Role> roles = new ArrayList();
//        roles.add(new StateBodyEmployeeRole());
        return null;
    }
    
}
