package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.ResourceAllocationBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.dao.ResourceAllocationDao;

public class ResourceAllocationService {
	public static boolean isUpdateResource = false;
	public static boolean isUpdate = false;
	public static boolean isInsertMapper = false;
	public static boolean isInsertTracking = false;
	
	public static boolean isUpdateResourceTable(ArrayList<ResourceMasterBean> resourceList){
		return isUpdateResource = ResourceAllocationDao.updateResourceTable(resourceList);
	}

	public static boolean isUpdateNumberInResourceTable(Integer clientId,Integer reqId,String type,Integer number){
		return isUpdate = ResourceAllocationDao.updateResourceTableNumber(clientId,reqId,type,number);
	}
	
	public static boolean isInsertMapperTable(ArrayList<ResourceMasterBean> resourceList,ResourceAllocationBean bean){
		return isInsertMapper = ResourceAllocationDao.insertIntoMapper(resourceList, bean);
	}
	
	public static boolean isInsertStatusTrackingTable(ArrayList<ResourceMasterBean> resourceList,ResourceAllocationBean bean){
		return isInsertTracking = ResourceAllocationDao.insertIntoStatus(resourceList, bean);
	}
	
	/****************************************************************************************************************************************/
	
	public static boolean isUpdateResource() {
		return isUpdateResource;
	}

	public static void setUpdateResource(boolean isUpdateResource) {
		ResourceAllocationService.isUpdateResource = isUpdateResource;
	}

	public static boolean isInsertMapper() {
		return isInsertMapper;
	}

	public static void setInsertMapper(boolean isInsertMapper) {
		ResourceAllocationService.isInsertMapper = isInsertMapper;
	}

	public static boolean isInsertTracking() {
		return isInsertTracking;
	}

	public static void setInsertTracking(boolean isInsertTracking) {
		ResourceAllocationService.isInsertTracking = isInsertTracking;
	}


	public static boolean isUpdate() {
		return isUpdate;
	}


	public static void setUpdate(boolean isUpdate) {
		ResourceAllocationService.isUpdate = isUpdate;
	}
}
