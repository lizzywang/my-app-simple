package com.mygudou.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mygudou.app.service.ChatService;



@Controller
@RequestMapping(value="/simple")
public class ChatController {
	
	 @Resource(name = "ChatService")
	    private ChatService ChatService;
	
	@RequestMapping(value="/chat")
	public void sendChatMessage(HttpServletRequest request,HttpServletResponse response){
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		int flag = ChatService.insertMsg(name, message);
		JSONObject bJson = new JSONObject();

        bJson.put("check", flag);

        String bString = bJson.toJSONString();
        PrintWriter out = null;

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            out = response.getWriter();
            out.write(bString);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	@RequestMapping(value="/getchat")
	public void getChatMessage(HttpServletResponse response){
		
		String message = ChatService.getChatMsg();
		
		PrintWriter out = null;
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            out = response.getWriter();
            out.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
}
