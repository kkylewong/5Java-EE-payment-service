/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "TRAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tran.findAll", query = "SELECT t FROM Tran t"),
    @NamedQuery(name = "Tran.findById", query = "SELECT t FROM Tran t WHERE t.id = :id"),
    @NamedQuery(name = "Tran.findByAmount", query = "SELECT t FROM Tran t WHERE t.amount = :amount"),
    @NamedQuery(name = "Tran.findByPayee", query = "SELECT t FROM Tran t WHERE t.payee = :payee"),
    @NamedQuery(name = "Tran.findByPayer", query = "SELECT t FROM Tran t WHERE t.payer = :payer"),
    @NamedQuery(name = "Tran.findByPayerProcess", query = "SELECT t FROM Tran t WHERE t.payer = :payer AND t.process= :process"),
    @NamedQuery(name = "Tran.findByPayeeProcess", query = "SELECT t FROM Tran t WHERE t.payee = :payee AND t.process= :process"),
    @NamedQuery(name = "Tran.findByPayee_PayerProcess", query = "SELECT t FROM Tran t WHERE t.payee = :payee OR t.payer = :payer AND t.process= :process"),
    @NamedQuery(name = "Tran.findAllbyProcess", query = "SELECT t FROM Tran t WHERE t.process= :process")
})
public class Tran implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "AMOUNT")
    private Integer amount;
    @Size(max = 255)
    @Column(name = "PAYEE")
    private String payee;//email
    @Size(max = 255)
    @Column(name = "PAYER")
    private String payer;//email
    @Size(max = 255)
    @Column(name = "PROCESS")
    private String process;

    public Tran() {
    }
    
    public Tran(String payer, String payee, int amount, String process) {
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
        this.process=process;
    }

    public Tran(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
    
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tran)) {
            return false;
        }
        Tran other = (Tran) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tran[ id=" + id + " ]";
    }
    
}
