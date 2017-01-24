package org.appsquad.bean;

public class RuntimePopulateRoleBasedOnUserIdBean {
   private Integer userSerialId;
   private String userID;
   private Integer roleID;
   private Integer menusID;
   
   
   /*****************************************************************************************************************************************************/
   
   public Integer getUserSerialId() {
	return userSerialId;
   }
   public void setUserSerialId(Integer userSerialId) {
	this.userSerialId = userSerialId;
   }
   public String getUserID() {
	return userID;
   }
   public void setUserID(String userID) {
	this.userID = userID;
   }
   public Integer getRoleID() {
	return roleID;
   }
   public void setRoleID(Integer roleID) {
	this.roleID = roleID;
   }
   public Integer getMenusID() {
	return menusID;
   }
   public void setMenusID(Integer menusID) {
	this.menusID = menusID;
   } 
}
