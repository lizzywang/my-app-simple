package com.mygudou.app.dao;

import java.sql.Timestamp;
import java.util.List;
import com.mygudou.app.model.Note;

public interface MessageDao {
	public int addMessage(String message,int from ,int to,Timestamp timestamp,String commitName,String toName);
	public List<Note> getMessageList(String from);
}
