package org.appsquad.viewmodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.apache.log4j.Logger;
import org.appsquad.bean.LoginBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.database.DbConnection;
import org.appsquad.service.LoginService;
import org.appsquad.sql.LoginSql;
import org.appsquad.utility.PasswordEncryption;
import org.appsquad.utility.Pstm;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class LogingViewModel {
	LoginBean loginBean = new LoginBean();
    Connection connection =null; 
	Session session = null;
	private String txtType = "password";
	private String message = "";
	
	final static Logger logger = Logger.getLogger(LogingViewModel.class);
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{
		Selectors.wireComponents(view, this, false);
		session = Sessions.getCurrent();
		loginBean.setUserNameFocus(true);
		loginBean.userNameStyle = "outline: none; border: 1px solid #7bc1f7; "
										+ "box-shadow: 0px 0px 8px #7bc1f7; "
										+ "-moz-box-shadow: 0px 0px 8px #7bc1f7; "
										+ "-webkit-box-shadow: 0px 0px 8px #7bc1f7;";
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeUserName(){
		loginBean.setUserNameFocus(false);
		loginBean.userNameStyle =  "";
		loginBean.setPasswordFocus(true);
		loginBean.passwordStyle = "outline: none; border: 1px solid #7bc1f7; "
										+ "box-shadow: 0px 0px 8px #7bc1f7; "
										+ "-moz-box-shadow: 0px 0px 8px #7bc1f7; "
										+ "-webkit-box-shadow: 0px 0px 8px #7bc1f7;";
	}
	
	
	@Command
	@NotifyChange("*")
	public void onChangePassword(){
		loginBean.passwordStyle = "outline: none; border: 1px solid #7bc1f7; "
										+ "box-shadow: 0px 0px 8px #7bc1f7; "
										+ "-moz-box-shadow: 0px 0px 8px #7bc1f7; "
										+ "-webkit-box-shadow: 0px 0px 8px #7bc1f7;";
		
		onClickLogin();
	}
	
	@Command
	@NotifyChange("*")
	public void onCheckShowPassword(){
		if(txtType.equals("text")){
			txtType = "password";
		}else{
			txtType = "text";
		}
	}
	
	@Command
	@NotifyChange("*")
	public void onClickForgotPassword(){
		if(loginBean.getUserId()!=null){
			UserprofileBean userBean =LoginService.getEmailIdOfUser(loginBean.getUserId());
			if( userBean != null){
				 
				//userBean.setUserid(loginBean.getUserId());
				//userBean.setEmail(emailId);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("userDetails", userBean);
				Window window = (Window) Executions.createComponents("/WEB-INF/view/forgotPassword.zul", null, map);
				window.doModal();
			}else{
				Messagebox.show("Oops! No email id found with the given user id!","Alert Information",Messagebox.OK,Messagebox.EXCLAMATION);
			}
		}else{
			Messagebox.show("UserId is required","Alert Information",Messagebox.OK,Messagebox.EXCLAMATION);
		}
		
	}
	
	@Command
	@NotifyChange("*")
	public void onClickLogin(){
		message = "";
		if(isFieldValidate()){
			connection = DbConnection.createConnection();
			try {
				sql:{    
				     PreparedStatement preparedStatement = null;
				     ResultSet resultSet = null;
				     String encryptedPassword = PasswordEncryption.easeyEncrypt(loginBean.getPassword());
				     try {
						preparedStatement = Pstm.createQuery(connection, LoginSql.loginQuery, 
								Arrays.asList(loginBean.getUserId(),encryptedPassword));
												
						logger.info("Login Function - " + preparedStatement.unwrap(PreparedStatement.class));
						
						resultSet = preparedStatement.executeQuery();
						if(resultSet.next()){
							String userId = resultSet.getString("user_id");
							session.setAttribute("userId", userId);
							Executions.sendRedirect("/home.zul");
							
							Messagebox.show("Hello "+loginBean.getUserId()+" ! Welcome to RTS ","Information",Messagebox.OK,Messagebox.INFORMATION);
						}else {
							 message = "User Name or Password Invalid!";
							// Messagebox.show("Wrong User Id And Password!", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
						}
					} finally{
						if(preparedStatement!=null){
							preparedStatement.close();
						}
					}
			    }
			} catch (Exception e) {
				logger.fatal("--------------------- " + e);
				e.printStackTrace();
			}finally{
				if(connection!=null){
					try {
						connection.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				}
			}	
		}
	}

	public boolean isFieldValidate(){
		if(loginBean.getUserId()!=null){
			if(loginBean.getPassword()!=null){
				return true;
			}else{
				Messagebox.show("Password required!","Password missing",Messagebox.OK,Messagebox.EXCLAMATION);
				return false;
			}
		}else{
			Messagebox.show("User Id required!","User id missing",Messagebox.OK,Messagebox.EXCLAMATION);
			return false;
		}
	}
	
    /*************************************************************************************************************************************************/
	
	public LoginBean getLoginBean() {
		return loginBean;
	}
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}

	public String getTxtType() {
		return txtType;
	}

	public void setTxtType(String txtType) {
		this.txtType = txtType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static Logger getLogger() {
		return logger;
	}
}
