package com.mygudou.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mygudou.app.daoimpl.FriendDaoImpl;
import com.mygudou.app.model.Admin;

@Service("FriendService")
@Transactional
public class FriendService {
	
	@Resource(name = "FriendDao")
    private FriendDaoImpl FriendDao;
	
	public void SetFriends(int id,int friend_id){
		Admin admin = FriendDao.getFriends(id);
		String friend_ids = admin.getFriendID();
		StringBuilder sb = null;
		if(friend_ids == null){
			sb = new StringBuilder();
			sb.append(friend_id);
		}else{
			sb = new StringBuilder(friend_ids);
			sb.append(",").append(friend_id);
		}
		FriendDao.setFriend(id, sb.toString());
		}
	public List<Admin> getFriendsList(int id){
		Admin admin = FriendDao.getFriends(id);
		String friend_ids = admin.getFriendID();
		List<Admin> list = FriendDao.getFriendsList(friend_ids);
		return list;
		
	}
}
