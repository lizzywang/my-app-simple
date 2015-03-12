

/** * @(#)AdminDaoimpl.java, 2015年2月11日. * * Copyright 2015 Yodao, Inc. All rights reserved. * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. */
 package com.mygudou.app.daoimpl; /** * * @author xinwang * *  */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import com.mygudou.app.dao.AdminDao;
import com.mygudou.app.model.Admin;
import com.mygudou.app.rowmapper.AdminRowMapper;

@Repository("AdminDao")
public class AdminDaoimpl implements AdminDao{
@Resource(name = "myjdbcTemplate")
private JdbcTemplate jdbcTemplate; 

    public Admin checkLogin(String name, String pass) {
        String sql = "select * from admin where name= '"+name+"' and password= '"+pass+"'";  
        List<Admin> entity = jdbcTemplate.query(sql, new AdminRowMapper());
        if(entity.size()>0){
        return entity.get(0);}
        else 
        	return null;
        }


    public Admin checkLogin(String name) {
        String sql = "select * from admin where name='"+name+"'";  
        List<Admin> entity = jdbcTemplate.query(sql, new AdminRowMapper());
        if(entity.size()>0){
        return entity.get(0);}
        else 
        	return null;
        
        }


	public int insert( final String name, final String pass) {

		int affectRows = jdbcTemplate.update(new PreparedStatementCreator(){

			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String sql = "insert into Admin(name,password) values(?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, pass);
				return ps;
			}});
		return affectRows;
	}


	@Override
	public Admin getById(int id) {
		String sql = "select * from admin where id ="+id;
		List<Admin> list = jdbcTemplate.query(sql, new AdminRowMapper());
		return list.get(0);
	}
        
        
        
    }



