package org.appsquad.viewmodel;

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

public class DemoProlayComposer extends GenericForwardComposer {
	private Iframe iFrameDemo;
	@Wire("#winDemoFlow")
	private Window winDemoFlow;
	private Session sessions = null;
	private ArrayList<DemoBean> list = new ArrayList<DemoBean>();
	private String passData;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		sessions = Sessions.getCurrent();
		Map<String,Object> argument = (Map<String, Object>) Executions.getCurrent().getArg();
		list = (ArrayList<DemoBean>) argument.get("fetchID");
		passData = getPdtCodeList(list);
		iFrameDemo.setSrc("/DemoServlet?key="+passData);
		if(winDemoFlow==null){
			iFrameDemo.setPage(null);
			winDemoFlow.setClosable(true);
		}
    }
	
	public String getPdtCodeList(ArrayList<DemoBean> list){
		String passCode = "";
		for(DemoBean bean: list){
			passCode+=bean.getId()+",";
		}
		return passCode = passCode.substring(0, passCode.length()-1);
	}

	@Command
	@NotifyChange("*")
	public void onClick(){
		System.out.println("inside");
		winDemoFlow.setClosable(true);
		winDemoFlow.detach();
	}
	
	@Command
	@NotifyChange("*")
	public void onCloseOperation(@ContextParam(ContextType.TRIGGER_EVENT)Event e){
		BindUtils.postGlobalCommand(null, null, "refreshDownloadingScreen", null);
	}
	
	/******************************************************************************************************************************************/
	
	public Iframe getiFrameDemo() {
		return iFrameDemo;
	}
	public void setiFrameDemo(Iframe iFrameDemo) {
		this.iFrameDemo = iFrameDemo;
	}
	public Window getWinDemoFlow() {
		return winDemoFlow;
	}
	public void setWinDemoFlow(Window winDemoFlow) {
		this.winDemoFlow = winDemoFlow;
	}
	public Session getSessions() {
		return sessions;
	}
	public void setSessions(Session sessions) {
		this.sessions = sessions;
	}
	public ArrayList<DemoBean> getList() {
		return list;
	}
	public void setList(ArrayList<DemoBean> list) {
		this.list = list;
	}
	public String getPassData() {
		return passData;
	}
	public void setPassData(String passData) {
		this.passData = passData;
	}
}
