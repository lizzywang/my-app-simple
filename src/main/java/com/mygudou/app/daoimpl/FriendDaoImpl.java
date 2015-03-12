package com.mygudou.app.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mygudou.app.dao.FriendDao;
import com.mygudou.app.model.Admin;
import com.mygudou.app.rowmapper.AdminRowMapper;


@Repository("FriendDao")
public class FriendDaoImpl implements FriendDao{

	@Resource(name = "myjdbcTemplate")
	private JdbcTemplate jdbcTemplate; 

	@Override
	public List<Admin> findFriends(String name) {
		String sql ="select * from admin where name like '%"+name+"%'";
        List<Admin> entity = jdbcTemplate.query(sql, new AdminRowMapper());
        return entity;
	}

	@Override
	public void setFriend(int id, String friends_id) {
		
		String sql = "update admin set friendid ='"+friends_id+"' where id ="+id ;
		jdbcTemplate.execute(sql);
		
	}

	@Override
	public Admin getFriends(int id) {
		String sql = "select * from admin where id = "+id;
		List<Admin> entity = jdbcTemplate.query(sql, new AdminRowMapper());
		if(entity.size()==0){
			return null;
		}
		return entity.get(0);
		
		
	}

	@Override
	public List<Admin> getFriendsList(String friends) {
		String[] friends_ = friends.split(",");
		String sql = "select * from admin where ";
		StringBuilder sb = new StringBuilder(sql);
		for(String friend:friends_){
			sb.append(" id = "+friend+" or");
		}
		sql = sb.toString().substring(0, sb.toString().lastIndexOf("or"));
		System.out.println(sql);
		List<Admin> list = jdbcTemplate.query(sql, new AdminRowMapper());
		return list;
	}


	
	
	
	

}
