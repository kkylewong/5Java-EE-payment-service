/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

/**
 *
 * @author gp225
 */

@NamedQueries({
    @NamedQuery(name="findAllUser",query="SELECT c FROM SystemUser c "),
    @NamedQuery(name = "findWithUsername", query = "SELECT t FROM SystemUser t WHERE t.username = :username"),
    @NamedQuery(name = "findEmailWithUsername", query = "SELECT t.email FROM SystemUser t WHERE t.username = :username"),
    @NamedQuery(name = "findAllWithEmail", query = "SELECT t FROM SystemUser t WHERE t.email = :email"),
    
    @NamedQuery(name = "findMoneyFromUsername", query = "SELECT t.money FROM SystemUser t WHERE t.username = :username"),
    @NamedQuery(name = "findidFromUsername", query = "SELECT t.id FROM SystemUser t WHERE t.username = :username"),
    @NamedQuery(name = "findUsernameWithEmail", query = "SELECT t.username FROM SystemUser t WHERE t.email = :email")
})
@Entity
public class SystemUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private String username;
    private String userpassword;

    
   
    String name;
    String email;
    Integer money;
     

    public SystemUser() {
    }

    public SystemUser(String username, String userpassword, String name, String email, Integer money) {
        this.username = username;
        this.userpassword = userpassword;
        this.name = name;
        this.email = email;
        this.money = money;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.money);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemUser other = (SystemUser) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.money, other.money)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "SystemUser{" + "username=" + username + ", name=" + name + ", email=" + email + ", money=" + money + '}';
    }
    
    public String goBack() {
        return "index";
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("SystemUser: PostConstruct");
    }
    
    @PreDestroy
    public void preDestroy() {
        System.out.println("SystemUser: PreDestroy");
    }
}
