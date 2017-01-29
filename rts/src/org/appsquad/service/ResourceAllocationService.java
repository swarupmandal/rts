package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.ResourceAllocationBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.dao.ResourceAllocationDao;

public class ResourceAllocationService {
	
	public static boolean isUpdateResourceTable(ArrayList<ResourceMasterBean> resourceList){
		boolean isUpdateResource = false;
		isUpdateResource = ResourceAllocationDao.updateResourceTable(resourceList);
		return isUpdateResource;
	}

	public static boolean isUpdateNumberInResourceTable(Integer clientId,Integer reqId,String type,Integer number){
		boolean isUpdate = false;
		isUpdate = ResourceAllocationDao.updateResourceTableNumber(clientId,reqId,type,number);
		return isUpdate;
	}
	
	public static boolean isInsertMapperTable(ArrayList<ResourceMasterBean> resourceList,ResourceAllocationBean bean){
		boolean isInsertMapper = false;
		isInsertMapper = ResourceAllocationDao.insertIntoMapper(resourceList, bean);
		return isInsertMapper;
	}
	
	public static boolean isInsertStatusTrackingTable(ArrayList<ResourceMasterBean> resourceList,ResourceAllocationBean bean){
		boolean isInsertTracking = false;
		isInsertTracking = ResourceAllocationDao.insertIntoStatus(resourceList, bean);
		return isInsertTracking;
	}
	
	public static void getAllData(ResourceAllocationBean resourceAllocationBean){
		ResourceAllocationDao.getAllFieldData(resourceAllocationBean);
	}
	
}
