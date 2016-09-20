package org.appsquad.viewmodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.appsquad.bean.LoginBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.LoginSql;
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
import org.zkoss.zul.Messagebox;

public class LogingViewModel {
	
	LoginBean loginBean = new LoginBean();
	
	private Connection connection = null;
	
	Session session = null;
	
	@AfterCompose
	public void initSetUp(@ContextParam(ContextType.VIEW) Component view) throws Exception{

		Selectors.wireComponents(view, this, false);
		
		session = Sessions.getCurrent();
		
		loginBean.setUserNameFocus(true);
		
		loginBean.userNameStyle = "outline: none; border: 1px solid #7bc1f7; "
										+ "box-shadow: 0px 0px 8px #7bc1f7; "
										+ "-moz-box-shadow: 0px 0px 8px #7bc1f7; "
										+ "-webkit-box-shadow: 0px 0px 8px #7bc1f7;";
		
		System.out.println("Login Page loading with session::"+session);
		System.out.println("INIT LOAD");
		
	}
	
	@Command
	@NotifyChange("*")
	public void onChangeUserName(){
		
		//loginMasterBean.userNameFocus = false;
		//loginMasterBean.passwordFocus = true;
		
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
	public void onClickLogin(){
		if(isFieldValidate()){
			connection = DbConnection.createConnection();
			try {
				sql:{    
				     PreparedStatement preparedStatement = null;
				     ResultSet resultSet = null;
				     try {
						preparedStatement = Pstm.createQuery(connection, LoginSql.loginQuery, 
								Arrays.asList(loginBean.getUserId(),loginBean.getPassword()));
						resultSet = preparedStatement.executeQuery();
						if(resultSet.next()){
							String userId = resultSet.getString("user_id");
							session.setAttribute("userId", userId);
							Executions.sendRedirect("/home.zul");
						}else {
							Messagebox.show("Illigal entry");
						}
					} finally{
						if(preparedStatement!=null){
							preparedStatement.close();
						}
					}
			    }
			} catch (Exception e) {
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
				Messagebox.show("Enter Password");
				return false;
			}
		}else{
			Messagebox.show("Enter User Id");
			return false;
		}
	}
	

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

}
