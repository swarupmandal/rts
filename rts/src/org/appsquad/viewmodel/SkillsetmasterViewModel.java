package org.appsquad.viewmodel;

import org.appsquad.bean.SkillsetMasterbean;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class SkillsetmasterViewModel {

	SkillsetMasterbean skillsetMasterbean=new SkillsetMasterbean();

	public SkillsetMasterbean getSkillsetMasterbean() {
		return skillsetMasterbean;
	}

	public void setSkillsetMasterbean(SkillsetMasterbean skillsetMasterbean) {
		this.skillsetMasterbean = skillsetMasterbean;
	}  
	
	
	@Command
	@NotifyChange("*")
	public void onClickexistingskillSet()
	{
		
	}
	@Command
	@NotifyChange("*")
	public void onClickSetskillsubmit(){
		
	}
}
