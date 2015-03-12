package com.mygudou.app.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mygudou.app.dao.MessageDao;
import com.mygudou.app.daoimpl.MessageDaoImpl;

@Service("MessageService")
@Transactional
public class MessageService {
	
	@Resource(name = "MessageDao")
    private MessageDaoImpl MessageDao;
	
	public int addMessage(String message,int from,int to){
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		int affectRows = MessageDao.addMessage(message, from, to,timestamp);
		return affectRows;
	}
}
