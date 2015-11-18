package com.ozyegin.livevideostreamproject.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ChannelView {

	private String selectedChannel;

	public String getSelectedChannel() {
		return selectedChannel;
	}

	public void setSelectedChannel(String selectedChannel) {
		this.selectedChannel = selectedChannel;
	}

}
