package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.DemoBean;
import org.appsquad.dao.DemoDao;

public class DemoService {
    private static ArrayList<DemoBean> listForSkillSet = null;
    private static ArrayList<DemoBean> listForSkillSetAndDate = null;
    private static ArrayList<DemoBean> listForSkillSetAndDateAndClient = null;
    
	public static ArrayList<DemoBean> getDetailsForSkillService(DemoBean demoBean){
		return listForSkillSet = DemoDao.getDetailsForSkill(demoBean);
	}
	
	public static ArrayList<DemoBean> getDetailsForSkillAndDateService(DemoBean demoBean){
		return listForSkillSetAndDate = DemoDao.getDetailsForSkillAndDate(demoBean);
	}

	public static ArrayList<DemoBean> getDetailsForSkillAndDateAndClientService(DemoBean demoBean){
		return listForSkillSetAndDateAndClient = DemoDao.getDetailsForSkillAndDateAndClient(demoBean);
	}
	
	/********************************************************************************************************************************************/
	
	public static ArrayList<DemoBean> getListForSkillSet() {
		return listForSkillSet;
	}
	public static void setListForSkillSet(ArrayList<DemoBean> listForSkillSet) {
		DemoService.listForSkillSet = listForSkillSet;
	}
	public static ArrayList<DemoBean> getListForSkillSetAndDate() {
		return listForSkillSetAndDate;
	}
	public static void setListForSkillSetAndDate(
			ArrayList<DemoBean> listForSkillSetAndDate) {
		DemoService.listForSkillSetAndDate = listForSkillSetAndDate;
	}

	public static ArrayList<DemoBean> getListForSkillSetAndDateAndClient() {
		return listForSkillSetAndDateAndClient;
	}

	public static void setListForSkillSetAndDateAndClient(
			ArrayList<DemoBean> listForSkillSetAndDateAndClient) {
		DemoService.listForSkillSetAndDateAndClient = listForSkillSetAndDateAndClient;
	}
}
