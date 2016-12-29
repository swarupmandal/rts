package org.appsquad.utility;

import java.util.ArrayList;
import java.util.Map;
import org.appsquad.bean.DemoBean;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

public class TimesheetComposer extends GenericForwardComposer{
		private Iframe iFrameDemoTimesheet;
		@Wire("#winDemoFlowTimesheet")
		private Window winDemoFlowTimesheet;
		private Session sessions = null;
		private String passData;
		
		@Override
		public void doAfterCompose(Component comp) throws Exception {
			super.doAfterCompose(comp);
			sessions = Sessions.getCurrent();
			Map<String,Object> argument = (Map<String, Object>) Executions.getCurrent().getArg();
			passData = (String) argument.get("fetchPath");
			iFrameDemoTimesheet.setSrc("/TimesheetServlet?key="+passData);
			if(winDemoFlowTimesheet==null){
				iFrameDemoTimesheet.setPage(null);
				winDemoFlowTimesheet.setClosable(true);
			}
	    }
		
	
		@Command
		@NotifyChange("*")
		public void onClick(){
			System.out.println("inside");
			winDemoFlowTimesheet.setClosable(true);
			winDemoFlowTimesheet.detach();
		}
		
		/******************************************************************************************************************************************/
		
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
		public Iframe getiFrameDemoTimesheet() {
			return iFrameDemoTimesheet;
		}
		public void setiFrameDemoTimesheet(Iframe iFrameDemoTimesheet) {
			this.iFrameDemoTimesheet = iFrameDemoTimesheet;
		}
}
