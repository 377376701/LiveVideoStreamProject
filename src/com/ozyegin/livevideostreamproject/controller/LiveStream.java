package com.ozyegin.livevideostreamproject.controller;

import com.ozyegin.livevideostreamproject.config.ServletAwareConfig;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/livevideo", configurator=ServletAwareConfig.class)
public class LiveStream {

	private static final Set<Session> sessions = Collections
			.synchronizedSet(new HashSet<Session>());
	
	 private EndpointConfig config;
	

	@OnOpen
	public void whenOpening(Session session, EndpointConfig config) throws IOException, EncodeException {
		session.setMaxBinaryMessageBufferSize(1024 * 512);
		sessions.add(session);
		this.config = config;
	}

	@OnMessage
	public void processVideo(byte[] imageData, Session session) {
		System.out.println("INsite process Video differentiate session");
		try {
			// Wrap a byte array into a buffer
			ByteBuffer buf = ByteBuffer.wrap(imageData);
			// imageBuffers.add(buf);

			for (Session session2 : sessions) {
				if(session.getQueryString().equals(session2.getQueryString())){
					session2.getBasicRemote().sendBinary(buf);	
					System.out.println("INsite process Video differentiate session1:"+session.getQueryString()+ "session2:"+session2.getQueryString());
				}
			}

		} catch (Throwable ioe) {
			System.out.println("Error sending message " + ioe.getMessage());
		}
	}

	@OnClose
	public void whenClosing(Session session) {
		System.out.println("Goodbye !");
		sessions.remove(session);
		HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
		ChannelView bean = (ChannelView) httpSession.getAttribute("channelView");
		String removeChannelName = bean.getSelectedChannel();
		
		ApplicationContainer bean2 = (ApplicationContainer) httpSession.getServletContext().getAttribute("applicationContainer");
		
		bean2.removeChannel(removeChannelName);
		
		
		
	}

}
