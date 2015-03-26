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

import com.mygudou.app.dao.MessageDao;
import com.mygudou.app.model.Note;
import com.mygudou.app.rowmapper.NoteRowMapper;

@Repository("MessageDao")
public class MessageDaoImpl implements MessageDao{

	@Resource(name = "myjdbcTemplate")
	private JdbcTemplate jdbcTemplate; 
	@Override
	public int addMessage(final String message, final int from, final int to,final Timestamp timestamp,final String commitName,final String toName) {
		int affectRows = jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				String sql = "insert into note values(?,?,?,?,?,?,0)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, from);
				ps.setInt(2, to);
				ps.setString(3, message);
				ps.setTimestamp(4, timestamp);
				ps.setString(5, commitName);
				ps.setString(6, toName);
				
				return ps;
			}
			
		});
		return affectRows;
		
	}
	@Override
	public List<Note> getMessageList(String from) {
		String sql = "select * from note where to_id="+from+" order by timestamp desc";
		List<Note> notes = jdbcTemplate.query(sql, new NoteRowMapper());
		return notes;
	}
    /* (non-Javadoc) * @see com.mygudou.app.dao.MessageDao#getuncheckedMessage(java.lang.String) */
    @Override
    public List<Note> getuncheckedMessage(String id) {
        String sql = "select * from note where to_id="+id+" and checked = 0 order by timestamp desc";
        List<Note> list = jdbcTemplate.query(sql, new NoteRowMapper());
        
        return list;
        
        // TODO Auto-generated method stub return null;
        
    }
    /* (non-Javadoc) * @see com.mygudou.app.dao.MessageDao#getConversation(java.lang.String, java.lang.String) */
    @Override
    public List<Note> getConversation(String from_id, String to_id) {
        String sql = "select * from note where to_id="+to_id+" and from_id="+from_id+" or to_id = "+from_id+" and from_id = "+to_id +" order by timestamp desc";
        List<Note> list = jdbcTemplate.query(sql, new NoteRowMapper());
        return list;
        
        // TODO Auto-generated method stub return null;
        
    }

}
