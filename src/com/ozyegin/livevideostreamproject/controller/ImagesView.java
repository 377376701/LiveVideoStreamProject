package com.ozyegin.livevideostreamproject.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class ImagesView {

	private List<String> images;

	@PostConstruct
	public void init() {
		images = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) {
			images.add("nature" + i + ".png");
		}
	}

	public List<String> getImages() {
		return images;
	}
}
