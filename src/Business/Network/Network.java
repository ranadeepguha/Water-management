/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Network;

import Business.Enterprise.EnterpriseDirectory;
import java.util.ArrayList;

/**
 *
 *
 */
public class Network {
 
    private String name;
    private int networkID;
    private EnterpriseDirectory enterpriseDirectory;
    
    public Network(){
        enterpriseDirectory = new EnterpriseDirectory();
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnterpriseDirectory getEnterpriseDirectory() {
        return enterpriseDirectory;
    }

    public void setEnterpriseDirectory(EnterpriseDirectory enterpriseDirectory) {
        this.enterpriseDirectory = enterpriseDirectory;
    }

    public int getNetworkID() {
        return networkID;
    }

    
    
    public void populateID(ArrayList<Network> networkList){
        
        int netsize = networkList.size();
        int lastID = networkList.get(netsize).getNetworkID();
        lastID++;
        networkID = lastID;
        
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
