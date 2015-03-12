package com.mygudou.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mygudou.app.model.Admin;

public class AdminRowMapper implements RowMapper<Admin>{

	public Admin mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		admin.setId(rs.getInt("id"));
		admin.setName(rs.getString("name"));
		admin.setPass(rs.getString("password"));
		admin.setFriendID(rs.getString("friendid"));
		return admin;
	}

}
