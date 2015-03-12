package com.mygudou.app.dao;

import java.sql.Timestamp;

public interface MessageDao {
	public int addMessage(String message,int from ,int to,Timestamp timestamp);
}
