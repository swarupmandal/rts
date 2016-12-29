package org.appsquad.utility;

import java.util.Map;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

public class InvoiceComposer extends GenericForwardComposer{
	private Session sessions = null;
	private String passData;
	
	private Iframe iFrameDemoInvoice;
	@Wire("#winDemoFlowInvoice")
	private Window winDemoFlowInvoice;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		sessions = Sessions.getCurrent();
		Map<String,Object> argument = (Map<String, Object>) Executions.getCurrent().getArg();
		passData = (String) argument.get("fetchOnvoice");
		iFrameDemoInvoice.setSrc("/InvoiceServlet?key="+passData);
		if(winDemoFlowInvoice==null){
			iFrameDemoInvoice.setPage(null);
			winDemoFlowInvoice.setClosable(true);
		}
    }
	

	@Command
	@NotifyChange("*")
	public void onClick(){
		System.out.println("inside");
		winDemoFlowInvoice.setClosable(true);
		winDemoFlowInvoice.detach();
	}
	
	/**************************************************************************************************************************************************/
	
	public Session getSessions() {
		return sessions;
	}
	public void setSessions(Session sessions) {
		this.sessions = sessions;
	}
	public String getPassData() {
		return passData;
	}
	public void setPassData(String passData) {
		this.passData = passData;
	}
	public Iframe getiFrameDemoInvoice() {
		return iFrameDemoInvoice;
	}
	public void setiFrameDemoInvoice(Iframe iFrameDemoInvoice) {
		this.iFrameDemoInvoice = iFrameDemoInvoice;
	}
	public Window getWinDemoFlowInvoice() {
		return winDemoFlowInvoice;
	}
	public void setWinDemoFlowInvoice(Window winDemoFlowInvoice) {
		this.winDemoFlowInvoice = winDemoFlowInvoice;
	}
}
