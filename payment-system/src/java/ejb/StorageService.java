/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;


import entity.SystemUser;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gp225
 */
@Local
public interface StorageService {

    public  List<SystemUser> getUserList();
    public  List<SystemUser> getUserListFromUsername(String username);
    public  String getUserEmail(String username);
    public  String checkUserName(String email);
    public  String removeAccountByID(Long id, String username);
}
