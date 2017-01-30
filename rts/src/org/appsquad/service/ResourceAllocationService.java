package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.ResourceAllocationBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.dao.ResourceAllocationDao;

public class ResourceAllocationService {
	
	public static boolean isUpdateResourceTable(ArrayList<ResourceMasterBean> resourceList){
		return ResourceAllocationDao.updateResourceTable(resourceList);
	}

	public static boolean isUpdateNumberInResourceTable(Integer clientId,Integer reqId,String type,Integer number){
		return ResourceAllocationDao.updateResourceTableNumber(clientId,reqId,type,number);
	}
	
	public static boolean isInsertMapperTable(ArrayList<ResourceMasterBean> resourceList,ResourceAllocationBean bean){
		return ResourceAllocationDao.insertIntoMapper(resourceList, bean);
	}
	
	public static boolean isInsertStatusTrackingTable(ArrayList<ResourceMasterBean> resourceList,ResourceAllocationBean bean){
		return ResourceAllocationDao.insertIntoStatus(resourceList, bean);
	}
	
	public static void getAllData(ResourceAllocationBean resourceAllocationBean){
		ResourceAllocationDao.getAllFieldData(resourceAllocationBean);
	}
	
}
