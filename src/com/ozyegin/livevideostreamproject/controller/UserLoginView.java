package com.ozyegin.livevideostreamproject.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

@ManagedBean
public class UserLoginView {

	String[] userNames;
	String[] passwords;

	private String username;

	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void readUsersFromFile() {
		ReadPropertyFile readPropertyFile = new ReadPropertyFile();
		userNames = readPropertyFile.ReadProperty("USER","c://LVS//resources//config.properties").split(";");
		passwords = readPropertyFile.ReadProperty("PASSWORD","c://LVS//resources//config.properties").split(";");
	}

	public boolean userExist(String user, String password) {
		for (int i = 0; i < userNames.length; i++)
			if (user.equals(userNames[i]) && password.equals(passwords[i]))
				return true;
		return false;
	}

	public void login(ActionEvent event) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean loggedIn = false;
		readUsersFromFile();

		if (username != null && userExist(username,password) && password != null) {
			loggedIn = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome",
					username);
		} else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Loggin Error", "Invalid credentials");
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("loggedIn", loggedIn);
	}
}
