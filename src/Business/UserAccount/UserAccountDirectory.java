/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.UserAccount;

import Business.Employee.Employee;
import Business.Role.Role;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 *
 */
public class UserAccountDirectory {
    
    private ArrayList<UserAccount> userAccountList;

    public UserAccountDirectory() {
        userAccountList=new ArrayList();
    }

    public ArrayList<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(ArrayList<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }
    
    public UserAccount createAndAddUserAccount(String name, String password, Employee employee, Role role) {

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(name);
        userAccount.setPassword(password);
        userAccount.setEmployee(employee);
        userAccount.setRole(role);
        userAccountList.add(userAccount);
        return userAccount;

    }
    
    public boolean checkIfUsernameIsUnique(String username)
    {
        for(UserAccount userAccount:userAccountList){
            if(userAccount.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }
    
    public UserAccount authenticateUser(String username, String password)
    {
        for(UserAccount userAccount:userAccountList){
            if(userAccount.getUsername().equals(username) && userAccount.getPassword().equals(password)){
                return userAccount;
            }
        }
        return null;
    }
    
}
