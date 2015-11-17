package com.ozyegin.livevideostreamproject.controller;

import com.ozyegin.livevideostreamproject.model.Channel;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import java.util.ArrayList;

@ManagedBean
@ApplicationScoped
public class ApplicationContainer {

	private ArrayList<Channel> channelList = new ArrayList<Channel>();

	public ArrayList<Channel> getChannelList() {
		return channelList;
	}

	public void setChannelList(ArrayList<Channel> channelList) {
		this.channelList = channelList;
	}

}
