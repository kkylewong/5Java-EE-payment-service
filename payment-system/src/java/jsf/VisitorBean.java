package jsf;

import ejb.StorageService;

import entity.SystemUser;
import java.io.Serializable;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class VisitorBean implements Serializable {

    private String username;
    private String name;
    private String email;
    private Integer money;
    
    @EJB
    private StorageService commentStore;

    public VisitorBean() {
    }
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            //this method will disassociate the principal from the session (effectively logging him/her out)
            request.logout();
            context.addMessage(null, new FacesMessage("You are logging out!!!!"));
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
        
        return "/faces/index.xhtml";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<SystemUser> getAllComments() {
        return commentStore.getUserList();
    }
    
    public List<SystemUser> getUserComment(String username) {
        return commentStore.getUserListFromUsername(username);
    }
    
    public String removeAccountByID(Long id, String username){
        return commentStore.removeAccountByID(id, username);
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

}
