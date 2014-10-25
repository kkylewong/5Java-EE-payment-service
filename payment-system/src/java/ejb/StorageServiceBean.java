package ejb;



import entity.SystemUser;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.xml.registry.infomodel.User;

@Stateless
public class StorageServiceBean implements StorageService {

    @PersistenceContext
    EntityManager em;
    private User user;

    public StorageServiceBean() {
    }
    
    public void raiseMoney(){
        
    }
    
    @Override
    public synchronized String removeAccountByID(Long id, String username){
        if("super".equals(username)){
            return "removeError";
        }else{
            SystemUser sys_user = em.find(SystemUser.class, id);
        em.remove(sys_user);
        return "removeFinished";
        }
    }

    @Override
    public synchronized List<SystemUser> getUserList() {
        List<SystemUser> systemUser = em.createNamedQuery("findAllUser").getResultList();
        return systemUser;
    }
    
    @Override
    public synchronized List<SystemUser> getUserListFromUsername(String username) {
        List<SystemUser> systemUser = em.createNamedQuery("findWithUsername")
                .setParameter("username", username)
                .getResultList();
        return systemUser;
    }

    @Override
    public synchronized String getUserEmail(String username) {
        String a = (String) em.createNamedQuery("findEmailWithUsername").setParameter("username", username).getSingleResult();
        return a;
    }
    
     @Override
    public synchronized String checkUserName(String email) {
        try {
        return (String)em.createNamedQuery("findUsernameWithEmail").setParameter("email", email).getSingleResult();
        }catch(NoResultException e){
            return "error";
        }
    }

    @PostConstruct
    void postConstruct() {
        System.out.println("StorageServiceBean: PostConstruct");
    }

    @PreDestroy
    void preDestroy() {
        System.out.println("StorageServiceBean: PreDestroy");
    }
}
