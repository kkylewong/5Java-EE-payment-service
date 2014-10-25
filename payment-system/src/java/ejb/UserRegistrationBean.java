package ejb;

import static com.sun.faces.facelets.util.Path.context;
import entity.SystemUser;
import entity.SystemUserGroup;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author parisis
 */
@Stateless
public class UserRegistrationBean {

    @PersistenceContext
    EntityManager em;
    private DataSource ds;


    public UserRegistrationBean() {
    }
    

    @PostConstruct
    void postConstruct() {
        System.out.println("UserService: PostConstruct");
    }

    @PreDestroy
    void preDestroy() {
        System.out.println("UserService: PreDestroy");
    }
    
    
    public String registerUser(String username, String userpassword, String name, String email, Integer money) {
        
                        
        try {
            SystemUser sys_user;
            SystemUserGroup sys_user_group;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = userpassword;
            md.update(passwd.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String paswdToStoreInDB = bigInt.toString(16);
 
            sys_user = new SystemUser(username, paswdToStoreInDB, name, email, money);
            sys_user_group = new SystemUserGroup(username, "users");

            em.persist(sys_user);
            em.persist(sys_user_group);
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserRegistrationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Account Created";
      
                
    }
    
    public void registerAdmins(String username, String userpassword, String name, String email, Integer money) {
        try {
            SystemUser sys_user;
            SystemUserGroup sys_user_group;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = userpassword;
            md.update(passwd.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String paswdToStoreInDB = bigInt.toString(16);
 
            sys_user = new SystemUser(username, paswdToStoreInDB, name, email, money);
            sys_user_group = new SystemUserGroup(username, "admins");

            em.persist(sys_user);
            em.persist(sys_user_group);
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserRegistrationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      
                
    }
    
}
