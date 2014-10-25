package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-04-10T04:40:16")
@StaticMetamodel(SystemUser.class)
public class SystemUser_ { 

    public static volatile SingularAttribute<SystemUser, Long> id;
    public static volatile SingularAttribute<SystemUser, String> username;
    public static volatile SingularAttribute<SystemUser, String> userpassword;
    public static volatile SingularAttribute<SystemUser, String> email;
    public static volatile SingularAttribute<SystemUser, String> name;
    public static volatile SingularAttribute<SystemUser, Integer> money;
    public static volatile SingularAttribute<SystemUser, Long> version;

}