package com.mygudou.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.mygudou.app.daoimpl.ChatDaoImpl;
import com.mygudou.app.model.Message;

@Transactional
@Service("ChatService")
public class ChatService {
	@Resource(name="ChatDao")
	private ChatDaoImpl ChatDao;
	
	public static final int delay = 4000;
	public int insertMsg(String name,String content){
		Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        int flag = ChatDao.insertMsg(name, content, timestamp);
        return flag;
	}
	
	public String getChatMsg(){
		Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime()-delay);
        System.out.println(timestamp);
        List<Message> messages = ChatDao.queryMsg(timestamp);
        String jsonStr = JSON.toJSONString(messages);
        System.out.println("jsonString:"+jsonStr);
        return jsonStr;
        
		
        
		
		
	}
}
