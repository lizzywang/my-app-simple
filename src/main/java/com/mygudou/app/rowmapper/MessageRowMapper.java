package com.mygudou.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mygudou.app.model.Message;

public class MessageRowMapper implements RowMapper<Message>{

	@Override
	public Message mapRow(ResultSet rs, int arg1) throws SQLException {
		Message message = new Message();
		message.setName(rs.getString("name"));
		message.setContent(rs.getString("content"));
		message.setTimestamp(rs.getTimestamp("timestamp"));
		return message;
	}

}
