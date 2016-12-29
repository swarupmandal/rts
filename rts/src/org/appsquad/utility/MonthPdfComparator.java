package org.appsquad.utility;

import java.util.Comparator;

import org.appsquad.bean.TaskNameBean;

public class MonthPdfComparator implements Comparator<TaskNameBean> {
	@Override
	public int compare(TaskNameBean o1, TaskNameBean o2) {
		if(o1.getMonth()>o2.getMonth()){
			return 1;
		}else{
			return -1;
		}
	}
}

