package com.mygudou.app.dao;

import java.sql.Timestamp;
import java.util.List;

import com.mygudou.app.model.Message;

public interface ChatDao {
	public int insertMsg(String name,String message,Timestamp timestamp);
	public List<Message> queryMsg(Timestamp timestamp);
}
