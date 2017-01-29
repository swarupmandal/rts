package org.appsquad.service;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.dao.TaskNameDao;

public class TaskNameService {

     public static boolean saveTaskDetails(TaskNameBean taskNameBean){
    	 boolean flagInsert = false;
    	 flagInsert = TaskNameDao.saveTaskDetailsDao(taskNameBean);
    	 return flagInsert;
     }
     
}
