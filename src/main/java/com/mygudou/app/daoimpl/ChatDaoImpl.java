package com.mygudou.app.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.mygudou.app.dao.ChatDao;
import com.mygudou.app.model.Message;
import com.mygudou.app.rowmapper.MessageRowMapper;

@Repository("ChatDao")
public class ChatDaoImpl implements ChatDao{
	@Resource(name = "myjdbcTemplate")
	private JdbcTemplate jdbcTemplate; 

	@Override
	public int insertMsg(final String name, final String content,final Timestamp timestamp) {
		int affectRows = jdbcTemplate.update(new PreparedStatementCreator(){

			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String sql = "insert into message(name,content,timestamp) values(?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, content);
				ps.setTimestamp(3, timestamp);
				return ps;
			}});
		return affectRows;
		
	}

	@Override
	public List<Message> queryMsg(Timestamp timestamp) {
		String sql = "select * from message where timestamp >=timestamp('"+timestamp+"')";
		List<Message> messages = jdbcTemplate.query(sql, new MessageRowMapper());
		for(int i = 0;i<messages.size();i++){
			System.out.println(messages.get(i).getName()+messages.get(i).getContent());
		}
		return messages;
	}

}
