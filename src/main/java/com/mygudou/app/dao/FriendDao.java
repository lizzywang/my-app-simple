package com.mygudou.app.dao;

import java.util.List;

import com.mygudou.app.model.Admin;

public interface FriendDao {
	public List<Admin> findFriends(String name);
	public void setFriend(int id,String friends_id);
	public Admin getFriends(int id);
	public List<Admin> getFriendsList(String friends);
	public boolean hasFriends(int id);
}
