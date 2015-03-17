package com.mygudou.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mygudou.app.daoimpl.AdminDaoimpl;
import com.mygudou.app.daoimpl.MessageDaoImpl;
import com.mygudou.app.model.Note;
import com.mygudou.app.service.MessageService;

@Controller
@RequestMapping(value = "/message")
public class MessageController {
	
    
    @Resource(name = "MessageService")
    private MessageService MessageService;
    @Resource(name = "MessageDao")
    private MessageDaoImpl MessageDao;
    
	@ResponseBody
	@RequestMapping(value = "/add",method =RequestMethod.POST)
	public void addMessage(@RequestBody String json,HttpServletRequest request,HttpServletResponse response){
		System.out.println(json);
		JSONObject jsonString = JSON.parseObject(json);
		System.out.println("1");
		int from_id = jsonString.getIntValue("from");
		int to_id = jsonString.getIntValue("to");
		String message = jsonString.getString("message");
		String commitName = jsonString.getString("commitName");
		String toName = jsonString.getString("toName");
		int affectRows = MessageService.addMessage(message, from_id, to_id,commitName,toName);
		JSONObject bJson = new JSONObject();
		bJson.put("check", affectRows);
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
	
	@RequestMapping(value="/getMessageList")
	public void getMessageList(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		String notes = MessageService.getMessageList(id);
		PrintWriter out = null;
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            out = response.getWriter();
            out.write(notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	@RequestMapping(value="/test")
	public String getBeautifullines(){
	    return "/test";
	}
}
