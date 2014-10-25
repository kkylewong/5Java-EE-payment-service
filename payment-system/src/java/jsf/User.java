package jsf;




import ejb.StorageService;
import ejb.TranService;
import ejb.UserRegistrationBean;
import entity.SystemUser;
import entity.Tran;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;
import jsf.VisitorBean;
import time1.TimeStamping_Service;


@Named
@SessionScoped

public class User implements Serializable {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/TimeStamping/TimeStamping.wsdl")
    private TimeStamping_Service service;

	private String name;
        private String payer;
        
        @Pattern(regexp
			= "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]")
        private String payeeMail;
        private Integer amount;
        
        @Pattern(regexp
			= "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]")
        private String requestedPayer;
        private Integer requestedAmount;
        
         @EJB
        private TranService tran_s;
     
        @EJB
        private UserRegistrationBean usrSrv;
        
        @EJB
        private StorageService stor_ser;
        
        private VisitorBean vis;
        
        @EJB
        private TranService trans;
	
	public User() {
		// find who signed in and set name
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		name = request.getRemoteUser();
	}
        
        public String removeTransByID(int id){
            return tran_s.removeTransactionByID(id);
        }
       
        
        public List<Tran> getAllfromPayerFinished(){
            payer = name;
            String a = trans.getUserEmail(payer);
            return tran_s.getAllfromPayerAndProcess(a, "finished");
        }
        
        public List<Tran> getAllfromPayeeFinished(){
            payer = name;
            String a = trans.getUserEmail(payer);
            return tran_s.getAllfromPayeeAndProcess(a, "finished");
        }
        
        public List<Tran> getAllfromPayerRequested(){
            String a = trans.getUserEmail(name);
            return tran_s.getAllfromPayerAndProcess(a, "requested");
        }
        
        public List<Tran> getAllfromPayeeRequested(){
            
            String a = trans.getUserEmail(name);
             return tran_s.getAllfromPayeeAndProcess(a, "requested");
        }
        
        public List<Tran> getAllfrom_payer_Request_reject(){
            String a = trans.getUserEmail(name);
            return tran_s.getAllfromPayerAndProcess(a, "rejected");
        }
        
        public List<Tran> getAllfrom_payee_Request_reject(){
            String a = trans.getUserEmail(name);
            return tran_s.getAllfromPayeeAndProcess(a, "rejected");
        }
        public String insertTran(){
             payer = name;
            if("error".equals(tran_s.checkUserName(payeeMail))){
                
                return "tranError";
            }
            else if(tran_s.decMoney(name, amount)==false) {
                return "notEnoughMoney";
            }
            else{
                String a = trans.getUserEmail(name);
                tran_s.addMoney(payeeMail, amount);
                trans.insertTran( a,  payeeMail, amount, "finished");
                return "transfinished";
            }
        }
        
        public String insertRequest(){
            if("error".equals(tran_s.checkUserName(requestedPayer))){
                return "tranError";
            }
            else{
                String a = trans.getUserEmail(name);
            trans.insertTran( requestedPayer, a, requestedAmount, "requested");
            return "transfinished";
            }
        }
        
        public String changeProcess_request_accept(int id, String payer, String payee, int amount){
            
            if(tran_s.decMoneyByEmail(payer, amount)==false) {
                return "notEnoughMoney";
            }
            else{
                tran_s.addMoney(payee, amount);
                tran_s.changeProcess(id, "finished");
                return "transfinished";
            }
        }
        
        public String changeProcess_request_reject(int id){
            tran_s.changeProcess(id, "rejected");
            return "transfinished";
        }
        
        
        public List<Tran> getAllByFinishedProcess(){
           return tran_s.getAllByProcess("finished");
        }
        
        public List<Tran> getAllByRequestedProcess(){
           return tran_s.getAllByProcess("requested");
        }
        
        public List<Tran> getAllByRejectedProcess(){
           return tran_s.getAllByProcess("rejected");
        }
        
        
        public String getEmailFromName(){
            return trans.getUserEmail(name);
        }

	public String getName() {
		return name;
	}
        
        
	public void setName(String name) {
		this.name = name;
	}


        public String getPayer() {
            return payer;
        }

        public void setPayer(String payer) {
            this.payer = payer;
        }

        public String getPayee() {
            return payeeMail;
        }

        public void setPayee(String payee) {
            this.payeeMail = payee;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public String getRequestedPayer() {
            return requestedPayer;
        }

        public void setRequestedPayer(String requestedPayer) {
            this.requestedPayer = requestedPayer;
        }

        public Integer getRequestedAmount() {
            return requestedAmount;
        }

        public void setRequestedAmount(Integer requestedAmount) {
            this.requestedAmount = requestedAmount;
    }

    private XMLGregorianCalendar getTimeStamping() {
        // 请注意, 注入的 javax.xml.ws.Service 引用以及端口对象不是线程安全的。
        // 如果调用端口操作可能导致争用情况, 则需要进行某种同步。
        time1.TimeStamping port = service.getTimeStampingPort();
        return port.getTimeStamping();
    }

       
        
        
    
    

}
