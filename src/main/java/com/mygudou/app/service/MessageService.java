package com.mygudou.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.mygudou.app.dao.MessageDao;
import com.mygudou.app.daoimpl.MessageDaoImpl;
import com.mygudou.app.model.Note;

@Service("MessageService")
@Transactional
public class MessageService {
	
	@Resource(name = "MessageDao")
    private MessageDaoImpl MessageDao;
	
	public int addMessage(String message,int from,int to,String commitName,String toName){
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		int affectRows = MessageDao.addMessage(message, from, to,timestamp,commitName,toName);
		return affectRows;
	}
	
	public String getMessageList(String from){
		List<Note> notes = MessageDao.getMessageList(from);
		String jsonStr = JSON.toJSONString(notes);
		System.out.println(jsonStr);
		return jsonStr;
		
	}
}
