package org.appsquad.utility;

import java.sql.Connection;
import java.util.ArrayList;
import org.appsquad.bean.CurrentOpportunitiesReportGenerationBean;
import org.appsquad.database.DbConnection;

public class MonthShowingForReport {
	   
   public static ArrayList<CurrentOpportunitiesReportGenerationBean> calculation(CurrentOpportunitiesReportGenerationBean generationBean){
	   int count = 0;
	   int counter = 0;
	   ArrayList<CurrentOpportunitiesReportGenerationBean> finalList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
	   try {
		  Connection connection = DbConnection.createConnection();
		  sql_connection:{
			  try {
				//1st block
			   	count = MonthShowingUtility.countTotal(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil(),generationBean.getCurrentOpportunitiesBean().getTentureToUtil(),connection);
			   	System.out.println("COUNT :"+count);
			   	if(count>0){
				   		CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
						String name = MonthShowingUtility.monthName(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil(), connection,generationBean.getRtsTrackingDetailsId());
						System.out.println(name);
						if(counter==0){
							  bean.getCurrentOpportunitiesReportBean().setMonth(name);
							  if(bean.getCurrentOpportunitiesReportBean().getMonth().startsWith("january")){
								  
								  bean.getCurrentOpportunitiesBean().setTentureFromUtil(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil());
								  bean.getCurrentOpportunitiesBean().setTentureToUtil(generationBean.getCurrentOpportunitiesBean().getTentureToUtil());
								  bean.getClientInformationBean().setFullName(generationBean.getClientInformationBean().getFullName());
								  bean.getResourceMasterBean().setFullName(generationBean.getResourceMasterBean().getFullName());
								  bean.getCurrentOpportunitiesReportBean().setBillNoString(generationBean.getCurrentOpportunitiesReportBean().getBillNoString());
								  bean.getCurrentOpportunitiesReportBean().setBillAmountString(generationBean.getCurrentOpportunitiesReportBean().getBillAmountString());
								  bean.getCurrentOpportunitiesReportBean().setMonth(name);
								  bean.setRtsTrackingDetailsId(generationBean.getRtsTrackingDetailsId());
								  
								  finalList.add(bean);
								  int number = (count-1);
								  
								  JanuaryMonthShowingUtility.januaryMonthShowing(number, generationBean, finalList);
							  }else if(bean.getCurrentOpportunitiesReportBean().getMonth().startsWith("february")){
								  
								   bean.getCurrentOpportunitiesBean().setTentureFromUtil(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil());
								  bean.getCurrentOpportunitiesBean().setTentureToUtil(generationBean.getCurrentOpportunitiesBean().getTentureToUtil());
								  bean.getClientInformationBean().setFullName(generationBean.getClientInformationBean().getFullName());
								  bean.getResourceMasterBean().setFullName(generationBean.getResourceMasterBean().getFullName());
								  bean.getCurrentOpportunitiesReportBean().setBillNoString(generationBean.getCurrentOpportunitiesReportBean().getBillNoString());
								  bean.getCurrentOpportunitiesReportBean().setBillAmountString(generationBean.getCurrentOpportunitiesReportBean().getBillAmountString());
								  bean.getCurrentOpportunitiesReportBean().setMonth(name);
								  bean.setRtsTrackingDetailsId(generationBean.getRtsTrackingDetailsId());
								  
								  finalList.add(bean);
								  int number = (count-1);
								  
								  FebruaryMonthShowingUtility.febMonthShowing(number, generationBean, finalList);
							  }else if(bean.getCurrentOpportunitiesReportBean().getMonth().startsWith("march")){
								  
								   bean.getCurrentOpportunitiesBean().setTentureFromUtil(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil());
								  bean.getCurrentOpportunitiesBean().setTentureToUtil(generationBean.getCurrentOpportunitiesBean().getTentureToUtil());
								  bean.getClientInformationBean().setFullName(generationBean.getClientInformationBean().getFullName());
								  bean.getResourceMasterBean().setFullName(generationBean.getResourceMasterBean().getFullName());
								  bean.getCurrentOpportunitiesReportBean().setBillNoString(generationBean.getCurrentOpportunitiesReportBean().getBillNoString());
								  bean.getCurrentOpportunitiesReportBean().setBillAmountString(generationBean.getCurrentOpportunitiesReportBean().getBillAmountString());
								  bean.getCurrentOpportunitiesReportBean().setMonth(name);
								  bean.setRtsTrackingDetailsId(generationBean.getRtsTrackingDetailsId());
								  
								  finalList.add(bean);
								  int number = (count-1);
								  
								  MarchMonthShowingUtility.marchMonthShowing(number, generationBean, finalList);
							  }else if(bean.getCurrentOpportunitiesReportBean().getMonth().startsWith("april")){
								  
								   bean.getCurrentOpportunitiesBean().setTentureFromUtil(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil());
								  bean.getCurrentOpportunitiesBean().setTentureToUtil(generationBean.getCurrentOpportunitiesBean().getTentureToUtil());
								  bean.getClientInformationBean().setFullName(generationBean.getClientInformationBean().getFullName());
								  bean.getResourceMasterBean().setFullName(generationBean.getResourceMasterBean().getFullName());
								  bean.getCurrentOpportunitiesReportBean().setBillNoString(generationBean.getCurrentOpportunitiesReportBean().getBillNoString());
								  bean.getCurrentOpportunitiesReportBean().setBillAmountString(generationBean.getCurrentOpportunitiesReportBean().getBillAmountString());
								  bean.getCurrentOpportunitiesReportBean().setMonth(name);
								  bean.setRtsTrackingDetailsId(generationBean.getRtsTrackingDetailsId());
								  
								  finalList.add(bean);
								  int number = (count-1);
								  
								  AprilMonthShowingUtility.aprilMonthShowing(number, generationBean, finalList);
							  }else if(bean.getCurrentOpportunitiesReportBean().getMonth().startsWith("may")){
								  
								   bean.getCurrentOpportunitiesBean().setTentureFromUtil(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil());
								  bean.getCurrentOpportunitiesBean().setTentureToUtil(generationBean.getCurrentOpportunitiesBean().getTentureToUtil());
								  bean.getClientInformationBean().setFullName(generationBean.getClientInformationBean().getFullName());
								  bean.getResourceMasterBean().setFullName(generationBean.getResourceMasterBean().getFullName());
								  bean.getCurrentOpportunitiesReportBean().setBillNoString(generationBean.getCurrentOpportunitiesReportBean().getBillNoString());
								  bean.getCurrentOpportunitiesReportBean().setBillAmountString(generationBean.getCurrentOpportunitiesReportBean().getBillAmountString());
								  bean.getCurrentOpportunitiesReportBean().setMonth(name);
								  bean.setRtsTrackingDetailsId(generationBean.getRtsTrackingDetailsId());
								  
								  finalList.add(bean);
								  int number = (count-1);
								  
								  MayMonthShowingUtility.mayMonthShowing(number, generationBean, finalList);
							  }else if(bean.getCurrentOpportunitiesReportBean().getMonth().startsWith("june")){
								  
								   bean.getCurrentOpportunitiesBean().setTentureFromUtil(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil());
								  bean.getCurrentOpportunitiesBean().setTentureToUtil(generationBean.getCurrentOpportunitiesBean().getTentureToUtil());
								  bean.getClientInformationBean().setFullName(generationBean.getClientInformationBean().getFullName());
								  bean.getResourceMasterBean().setFullName(generationBean.getResourceMasterBean().getFullName());
								  bean.getCurrentOpportunitiesReportBean().setBillNoString(generationBean.getCurrentOpportunitiesReportBean().getBillNoString());
								  bean.getCurrentOpportunitiesReportBean().setBillAmountString(generationBean.getCurrentOpportunitiesReportBean().getBillAmountString());
								  bean.getCurrentOpportunitiesReportBean().setMonth(name);
								  bean.setRtsTrackingDetailsId(generationBean.getRtsTrackingDetailsId());
								  
								  finalList.add(bean);
								  int number = (count-1);
								  
								  JuneMonthShowingUtility.juneMonth(number, generationBean, finalList);
							  }else if(bean.getCurrentOpportunitiesReportBean().getMonth().startsWith("july")){
								  
								   bean.getCurrentOpportunitiesBean().setTentureFromUtil(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil());
								  bean.getCurrentOpportunitiesBean().setTentureToUtil(generationBean.getCurrentOpportunitiesBean().getTentureToUtil());
								  bean.getClientInformationBean().setFullName(generationBean.getClientInformationBean().getFullName());
								  bean.getResourceMasterBean().setFullName(generationBean.getResourceMasterBean().getFullName());
								  bean.getCurrentOpportunitiesReportBean().setBillNoString(generationBean.getCurrentOpportunitiesReportBean().getBillNoString());
								  bean.getCurrentOpportunitiesReportBean().setBillAmountString(generationBean.getCurrentOpportunitiesReportBean().getBillAmountString());
								  bean.getCurrentOpportunitiesReportBean().setMonth(name);
								  bean.setRtsTrackingDetailsId(generationBean.getRtsTrackingDetailsId());
								  
								  finalList.add(bean);
								  int number = (count-1);
								  
								  JulyMonthShowingUtility.julyMonth(number, generationBean, finalList);
							  }else if(bean.getCurrentOpportunitiesReportBean().getMonth().startsWith("august")){
								  
								   bean.getCurrentOpportunitiesBean().setTentureFromUtil(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil());
								  bean.getCurrentOpportunitiesBean().setTentureToUtil(generationBean.getCurrentOpportunitiesBean().getTentureToUtil());
								  bean.getClientInformationBean().setFullName(generationBean.getClientInformationBean().getFullName());
								  bean.getResourceMasterBean().setFullName(generationBean.getResourceMasterBean().getFullName());
								  bean.getCurrentOpportunitiesReportBean().setBillNoString(generationBean.getCurrentOpportunitiesReportBean().getBillNoString());
								  bean.getCurrentOpportunitiesReportBean().setBillAmountString(generationBean.getCurrentOpportunitiesReportBean().getBillAmountString());
								  bean.getCurrentOpportunitiesReportBean().setMonth(name);
								  bean.setRtsTrackingDetailsId(generationBean.getRtsTrackingDetailsId());
								  
								  finalList.add(bean);
								  int number = (count-1);
								  
								  AugustMonthShowingUtility.augustMonth(number, generationBean, finalList);
								  
							  }else if(bean.getCurrentOpportunitiesReportBean().getMonth().startsWith("september")){
								  
								   bean.getCurrentOpportunitiesBean().setTentureFromUtil(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil());
								  bean.getCurrentOpportunitiesBean().setTentureToUtil(generationBean.getCurrentOpportunitiesBean().getTentureToUtil());
								  bean.getClientInformationBean().setFullName(generationBean.getClientInformationBean().getFullName());
								  bean.getResourceMasterBean().setFullName(generationBean.getResourceMasterBean().getFullName());
								  bean.getCurrentOpportunitiesReportBean().setBillNoString(generationBean.getCurrentOpportunitiesReportBean().getBillNoString());
								  bean.getCurrentOpportunitiesReportBean().setBillAmountString(generationBean.getCurrentOpportunitiesReportBean().getBillAmountString());
								  bean.getCurrentOpportunitiesReportBean().setMonth(name);
								  bean.setRtsTrackingDetailsId(generationBean.getRtsTrackingDetailsId());
								  
								  finalList.add(bean);
								  int number = (count-1);
								  
								  SeptemberMonthShowingUtility.sepMonth(number, generationBean, finalList);
								  
							  }else if(bean.getCurrentOpportunitiesReportBean().getMonth().startsWith("october")){
								  
								   bean.getCurrentOpportunitiesBean().setTentureFromUtil(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil());
								  bean.getCurrentOpportunitiesBean().setTentureToUtil(generationBean.getCurrentOpportunitiesBean().getTentureToUtil());
								  bean.getClientInformationBean().setFullName(generationBean.getClientInformationBean().getFullName());
								  bean.getResourceMasterBean().setFullName(generationBean.getResourceMasterBean().getFullName());
								  bean.getCurrentOpportunitiesReportBean().setBillNoString(generationBean.getCurrentOpportunitiesReportBean().getBillNoString());
								  bean.getCurrentOpportunitiesReportBean().setBillAmountString(generationBean.getCurrentOpportunitiesReportBean().getBillAmountString());
								  bean.getCurrentOpportunitiesReportBean().setMonth(name);
								  bean.setRtsTrackingDetailsId(generationBean.getRtsTrackingDetailsId());
								  
								  finalList.add(bean);
								  int number = (count-1);
								  
								  OctoberMonthShowingUtility.octoMonth(number, generationBean, finalList);
								  
							  }else if(bean.getCurrentOpportunitiesReportBean().getMonth().startsWith("november")){
								  
								   bean.getCurrentOpportunitiesBean().setTentureFromUtil(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil());
								  bean.getCurrentOpportunitiesBean().setTentureToUtil(generationBean.getCurrentOpportunitiesBean().getTentureToUtil());
								  bean.getClientInformationBean().setFullName(generationBean.getClientInformationBean().getFullName());
								  bean.getResourceMasterBean().setFullName(generationBean.getResourceMasterBean().getFullName());
								  bean.getCurrentOpportunitiesReportBean().setBillNoString(generationBean.getCurrentOpportunitiesReportBean().getBillNoString());
								  bean.getCurrentOpportunitiesReportBean().setBillAmountString(generationBean.getCurrentOpportunitiesReportBean().getBillAmountString());
								  bean.getCurrentOpportunitiesReportBean().setMonth(name);
								  bean.setRtsTrackingDetailsId(generationBean.getRtsTrackingDetailsId());
								  
								  finalList.add(bean);
								  int number = (count-1);
								  
								  NovemberMonthShowingUtility.novMonth(number, generationBean, finalList);
								  
							  }else if(bean.getCurrentOpportunitiesReportBean().getMonth().startsWith("december")){
								  
								   bean.getCurrentOpportunitiesBean().setTentureFromUtil(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil());
								  bean.getCurrentOpportunitiesBean().setTentureToUtil(generationBean.getCurrentOpportunitiesBean().getTentureToUtil());
								  bean.getClientInformationBean().setFullName(generationBean.getClientInformationBean().getFullName());
								  bean.getResourceMasterBean().setFullName(generationBean.getResourceMasterBean().getFullName());
								  bean.getCurrentOpportunitiesReportBean().setBillNoString(generationBean.getCurrentOpportunitiesReportBean().getBillNoString());
								  bean.getCurrentOpportunitiesReportBean().setBillAmountString(generationBean.getCurrentOpportunitiesReportBean().getBillAmountString());
								  bean.getCurrentOpportunitiesReportBean().setMonth(name);
								  bean.setRtsTrackingDetailsId(generationBean.getRtsTrackingDetailsId());
								  
								  finalList.add(bean);
								  int number = (count-1);
								  
								  DecemberMonthShowingUtility.decMonth(number, generationBean, finalList);								  
							  }
						}
			   	}else if(count==0){
			   		String name = MonthShowingUtility.monthName(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil(), connection,generationBean.getRtsTrackingDetailsId());
					CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
					bean.getCurrentOpportunitiesReportBean().setMonth(name);
					 bean.getCurrentOpportunitiesBean().setTentureFromUtil(generationBean.getCurrentOpportunitiesBean().getTentureFromUtil());
					bean.getCurrentOpportunitiesBean().setTentureToUtil(generationBean.getCurrentOpportunitiesBean().getTentureToUtil());
					bean.getClientInformationBean().setFullName(generationBean.getClientInformationBean().getFullName());
					bean.getResourceMasterBean().setFullName(generationBean.getResourceMasterBean().getFullName());
				    bean.getCurrentOpportunitiesReportBean().setBillNoString(generationBean.getCurrentOpportunitiesReportBean().getBillNoString());
					bean.getCurrentOpportunitiesReportBean().setBillAmountString(generationBean.getCurrentOpportunitiesReportBean().getBillAmountString());
					bean.setRtsTrackingDetailsId(generationBean.getRtsTrackingDetailsId());
					
					finalList.add(bean);
			   	}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(connection!=null){
					connection.close();
				}
			}
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
	return finalList;
   }
}
