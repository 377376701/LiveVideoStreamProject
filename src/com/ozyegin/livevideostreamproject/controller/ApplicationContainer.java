package com.ozyegin.livevideostreamproject.controller;

import com.ozyegin.livevideostreamproject.model.Channel;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import java.util.ArrayList;

@ManagedBean
@ApplicationScoped
public class ApplicationContainer {

	private ArrayList<Channel> channelList = new ArrayList<Channel>();

	private String newChannelName;

	public ArrayList<Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(ArrayList<Channel> channelList) {
		this.channelList = channelList;
	}

	public void createChannel(ActionEvent actionEvent) {

		if (uniqueCheck(newChannelName)) {
			Channel c2 = new Channel();
			c2.setChannelName(newChannelName);
			channelList.add(c2);
		} else {
			addMessage(newChannelName + "isimli kanal mevcut!!");
		}

		newChannelName = null;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	@PostConstruct
	public void init() {

	}

	public Boolean uniqueCheck(String name) {

		for (Channel c1 : channelList) {
			if (name.equals(c1.getChannelName())) {
				return false;
			}
		}

		return true;

	}

	public String getNewChannelName() {
		return newChannelName;
	}

	public void setNewChannelName(String newChannelName) {
		this.newChannelName = newChannelName;
	}

}
