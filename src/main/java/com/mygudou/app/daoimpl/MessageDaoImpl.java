package com.mygudou.app.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.mygudou.app.dao.MessageDao;

@Repository("MessageDao")
public class MessageDaoImpl implements MessageDao{

	@Resource(name = "myjdbcTemplate")
	private JdbcTemplate jdbcTemplate; 
	@Override
	public int addMessage(final String message, final int from, final int to,final Timestamp timestamp) {
		int affectRows = jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				String sql = "insert into note values(?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, from);
				ps.setInt(2, to);
				ps.setString(3, message);
				ps.setTimestamp(4, timestamp);
				return ps;
			}
			
		});
		return affectRows;
		
	}

}
