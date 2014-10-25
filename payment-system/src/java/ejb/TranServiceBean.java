/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.SystemUser;
import entity.Tran;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrator
 */
@Stateless
public class TranServiceBean implements TranService{
    @PersistenceContext
    EntityManager em;
    
    
    public TranServiceBean(){
    }
    
    @Override
    public String removeTransactionByID(int id){
        Tran tran = em.find(Tran.class, id);
        em.remove(tran);
        return "removeFinished";
    }
    
    @Override 
    public List<SystemUser> getAllByName(String username){
       return (List<SystemUser>) em.find(SystemUser.class, username);
    }
    
    @Override 
    public List<Tran> getAllByProcess(String process){
        List<Tran> tran = em.createNamedQuery("Tran.findAllbyProcess").setParameter("process", process).getResultList();
        return tran;
    }
    
    @Override
    public  List<Tran> getAllfromPayer(String payer)  {
        List<Tran> tran = em.createNamedQuery("Tran.findByPayer").setParameter("payer", payer).getResultList();
        return tran;
    }
    
    @Override
    public  List<Tran> getAllfromPayerAndProcess(String payer, String process)  {
        List<Tran> tran = em.createNamedQuery("Tran.findByPayerProcess")
                .setParameter("payer", payer).setParameter("process", process).getResultList();
        return tran;
    }
    
   
    
    @Override
    public  List<Tran> getAllfromPayee(String payee) {
        List<Tran> tran = em.createNamedQuery("Tran.findByPayee").setParameter("payee", payee).getResultList();
        return tran;
    }
    
     @Override
    public List<Tran> getAllfromPayeeAndProcess(String payee,String process){
         List<Tran> tran = em.createNamedQuery("Tran.findByPayeeProcess")
                 .setParameter("payee", payee).setParameter("process", process).getResultList();
        return tran;
    }
    
   
    
    
    @Override
    public  String getUserEmail(String username) {
        String a = (String) em.createNamedQuery("findEmailWithUsername").setParameter("username", username).getSingleResult();
        return a;
    }
    
    @Override 
    public List<SystemUser> getByQuery(String username){
       List<SystemUser> u = em.createNamedQuery("findWithUsername").setParameter("username", username).getResultList();
       return u;
    }
    
    @Override
    public void getIDbyName(String username){
        List<SystemUser> u = em.createNamedQuery("findWithUsername").setParameter("username", username).getResultList();
        
        Long b = u.get(0).getId();
    }
    
    @Override
    public void addMoney(String email, int increase){
        List<SystemUser> u = em.createNamedQuery("findAllWithEmail").setParameter("email", email).getResultList();
        Long b = u.get(0).getId();
        
        SystemUser emp = em.find(SystemUser.class, b);
        emp.setMoney(emp.getMoney() + increase);
    }
    
    @Override
    public boolean decMoney(String username, int decrease) {
        List<SystemUser> u = em.createNamedQuery("findWithUsername").setParameter("username", username).getResultList();
        Long b = u.get(0).getId();
        
        SystemUser emp = em.find(SystemUser.class, b);
        
       boolean checkBalanceOver0=false;
       if(emp.getMoney() - decrease>0){
        emp.setMoney(emp.getMoney() - decrease);
        checkBalanceOver0= true;
       }
       else{
           checkBalanceOver0=false;
       }
           return checkBalanceOver0;
    }
    
    @Override
    public boolean decMoneyByEmail(String email, int decrease) {
        List<SystemUser> u = em.createNamedQuery("findAllWithEmail").setParameter("email", email).getResultList();
        Long b = u.get(0).getId();
       SystemUser emp = em.find(SystemUser.class, b);
        
       boolean checkBalanceOver0=false;
       if(emp.getMoney() - decrease>0){
        emp.setMoney(emp.getMoney() - decrease);
        checkBalanceOver0= true;
       }
       else{
           checkBalanceOver0=false;
       }
           return checkBalanceOver0;
    }
    
     @Override
    public void changeProcess(int id, String process){
        Tran emp = em.find(Tran.class, id);
        emp.setProcess(process);
    }
    
    @Override
    public  void insertTran(String payer, String payee, int amount, String process) {
        Tran tran = new Tran(payer, payee, amount, process);
        em.persist(tran);
    }
    
    @Override
    public  String checkUserName(String email) {
        try {
        return (String)em.createNamedQuery("findUsernameWithEmail").setParameter("email", email).getSingleResult();
        }catch(NoResultException e){
            return "error";
        }
    }

    
}
