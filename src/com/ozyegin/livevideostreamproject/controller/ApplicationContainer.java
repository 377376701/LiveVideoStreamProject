package com.ozyegin.livevideostreamproject.controller;

import com.ozyegin.livevideostreamproject.model.Channel;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

import java.util.ArrayList;

@ManagedBean
@ApplicationScoped
public class ApplicationContainer {

	private ArrayList<Channel> channelList = new ArrayList<Channel>();

	private String newChannelName;

	public ArrayList<Channel> getChannelList() {
		return channelList;
	}
	
	public void removeChannel(String name) {
		for(Channel c :channelList)
		{
			if(c.getChannelName().equals(name))
			{
				channelList.remove(c);
				return;
			}
		}
	
	}


	public void setChannelList(ArrayList<Channel> channelList) {
		this.channelList = channelList;
	}

	public void createChannel(ActionEvent actionEvent) {

		RequestContext context = RequestContext.getCurrentInstance();
		boolean success = false;

		if (uniqueCheck(newChannelName)) {
			Channel c2 = new Channel();
			c2.setChannelName(newChannelName);
			channelList.add(c2);
			success = true;
			
			FacesContext currentContext = FacesContext.getCurrentInstance();
			ChannelView bean = (ChannelView) currentContext.getApplication().evaluateExpressionGet(currentContext, "#{channelView}", ChannelView.class);
			bean.setSelectedChannel(newChannelName);
			
		} else {
			success = false;
			addMessage(newChannelName + " isimli kanal mevcut!!");
		}

		newChannelName = null;
		context.addCallbackParam("success", success);
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
