package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-04-10T04:40:16")
@StaticMetamodel(Tran.class)
public class Tran_ { 

    public static volatile SingularAttribute<Tran, Integer> id;
    public static volatile SingularAttribute<Tran, Integer> amount;
    public static volatile SingularAttribute<Tran, String> process;
    public static volatile SingularAttribute<Tran, String> payer;
    public static volatile SingularAttribute<Tran, String> payee;

}