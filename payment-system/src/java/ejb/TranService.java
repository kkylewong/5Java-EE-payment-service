/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.SystemUser;
import entity.Tran;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrator
 */
@Local
public interface TranService {
    public List<SystemUser> getByQuery(String username);
    public void getIDbyName(String username);
    public boolean decMoney(String username, int decrease);
    public List<SystemUser> getAllByName(String username);
    public void addMoney(String email, int increase);
    public String checkUserName(String email);
    public void insertTran(String payer, String payee, int amount, String process);
    public String getUserEmail(String username);
    public List<Tran> getAllfromPayer(String payer);
    public List<Tran> getAllfromPayee(String payee);
    public List<Tran> getAllfromPayeeAndProcess(String payee,String process);
    public List<Tran> getAllfromPayerAndProcess(String payer, String process);
    public void changeProcess(int id, String process);
    public boolean decMoneyByEmail(String email, int decrease);
    public List<Tran> getAllByProcess(String process);
    public String removeTransactionByID(int id);
}
