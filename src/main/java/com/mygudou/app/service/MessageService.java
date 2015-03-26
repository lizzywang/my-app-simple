package com.mygudou.app.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;





import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
		
		return jsonStr;
		
	}
	
	public String getUncheckedMessage(String id){
	    List<Note> list = MessageDao.getuncheckedMessage(id);
	    HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
	    for(Note note:list){
	        int from_id = note.getFrom();
	        if(hm.containsKey(from_id)){
	            int count= hm.get(from_id);
	            count++;
	            hm.put(from_id, count);
	        }
	        else{
	        hm.put(from_id, 1);}
	        
	    }
	  
	    return buildString(hm);
            
        
	    
	}
	
	public String getConversation(int from_id,int to_id){
	    List<Note> list = MessageDao.getConversation(from_id, to_id);
	    String jsonstr = JSON.toJSONString(list);
	    return jsonstr;
	}
	
	public String getConversation2(int from_id,int to_id){
            List<Note> list = MessageDao.getConversation(from_id, to_id);
            String jsonstr = JSON.toJSONString(list);
            return jsonstr;
        }
        
	public String buildString(HashMap<Integer,Integer> hm){
	   
	    Set<Map.Entry<Integer, Integer>> set = hm.entrySet();
	    JSONArray jsonArray = new JSONArray(); 
            for (Iterator<Map.Entry<Integer, Integer>> it = set.iterator(); it.hasNext();) {
               Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>)it.next();
               
               JSONObject bjson = new JSONObject();
               
               bjson.put("id", entry.getKey());
               bjson.put("count", entry.getValue());
               jsonArray.add(bjson);
              
               }
            System.out.println(jsonArray.toString());
           return jsonArray.toString();
            
           }
	
	
}
