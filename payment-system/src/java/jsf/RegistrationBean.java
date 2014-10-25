package jsf;

import ejb.StorageService;
import ejb.UserRegistrationBean;
import entity.SystemUser;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

/**
 *
 * @author parisis
 */
@Named
@RequestScoped
public class RegistrationBean {

    @EJB
    UserRegistrationBean usrSrv;
    
    String username;
    String userpassword;
    String name;
    @Pattern(regexp
			= "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]")
    String email;
    Integer money;

     @EJB
    private StorageService commentStore;

    public RegistrationBean() {

    }

    //call the injected EJB
    public String register() {
        usrSrv.registerUser(username, userpassword, name, email, money);
        return "index";
    }
     
     public String register_admin() {
        usrSrv.registerAdmins(username, userpassword, name, email, money);
        return "admin";
    }
     
    
    
    public List<SystemUser> getAllComments() {
        return commentStore.getUserList();
    }
    
    public String goBack() {
        return "index";
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("VisitorBean: PostConstruct");
    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("VisitorBean: PreDestroy");
    }
    
    public void registerAdmin() {
        usrSrv.registerAdmins(username, userpassword, name, email, money);
        
    }
    
    public UserRegistrationBean getUsrSrv() {
        return usrSrv;
    } 

    public void setUsrSrv(UserRegistrationBean usrSrv) {
        this.usrSrv = usrSrv;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
    
    
}
