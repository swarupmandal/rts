package org.appsquad.utility;

import java.util.Comparator;

import org.appsquad.bean.TaskNameBean;

public class ScheduleDateComparator implements Comparator<TaskNameBean> {
	@Override
	public int compare(TaskNameBean o1, TaskNameBean o2) {
		if(o1.getScheduledDateSql().after(o2.getScheduledDateSql())){
			return 1;
		}else{
			return -1;
		}
	}
}
