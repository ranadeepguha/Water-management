/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Organization.Organization.AnalystType;
import Business.Organization.Organization.BankType;
import Business.Organization.Organization.WaterType;
import java.util.ArrayList;

/**
 *
 *
 */
public class OrganizationDirectory {

    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(ArrayList<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public Organization createOrganization(WaterType type) {
        Organization organization = null;
        if (type.getValue().equals(WaterType.CentralBodyOrganization.getValue())) {
            organization = new CentralBodyOrganization();
            organizationList.add(organization);
        } else if (type.getValue().equals(WaterType.StateBodyOrganization.getValue())) {
            organization = new StateBodyOrganization();
            organizationList.add(organization);
        }
        return organization;
    }

    public Organization createOrganization(BankType type) {
        Organization organization = new BankOrganization();
        organizationList.add(organization);
        return organization;
    }

    public Organization createOrganization(AnalystType type) {
        Organization organization = null;
        if (type.getValue().equals(AnalystType.AnalystOrganization.getValue())) {
            organization = new AnalystOrganization();
            organizationList.add(organization);
        } else if (type.getValue().equals(AnalystType.ForecastOrganization.getValue())) {
            organization = new ForecastOrganization();
            organizationList.add(organization);
        }
        return organization;
    }

}
