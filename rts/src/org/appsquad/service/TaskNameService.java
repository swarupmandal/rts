package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.TaskNameDao;

public class TaskNameService {

     public static boolean saveTaskDetails(TaskNameBean taskNameBean){
    	 boolean flagInsert = false;
    	 flagInsert = TaskNameDao.saveTaskDetailsDao(taskNameBean);
    	 return flagInsert;
     }
     
     public static ArrayList<UserprofileBean> loadSearchedUserId(String userId,ArrayList<UserprofileBean> userList){
		 ArrayList<UserprofileBean> users = new ArrayList<UserprofileBean>();
		 for(UserprofileBean user : userList){
			 if(user.getUserid().startsWith(userId)){
				 UserprofileBean bean = new UserprofileBean();
				 bean.setUserid(user.getUserid());
				 bean.setId(user.getId());
				 bean.setUsername(user.getUsername());
				 users.add(bean);
			 }
		 }
		return users;
	 }
}
