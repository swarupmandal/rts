package org.appsquad.service;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.dao.TaskNameDao;

public class TaskNameService {
     public static boolean flagInsert = false;
     public static boolean saveTaskDetails(TaskNameBean taskNameBean){
    	 return flagInsert = TaskNameDao.saveTaskDetailsDao(taskNameBean);
     }
}
