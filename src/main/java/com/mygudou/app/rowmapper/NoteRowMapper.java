package com.mygudou.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mygudou.app.model.Note;

public class NoteRowMapper implements RowMapper<Note>{

	@Override
	public Note mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Note note = new Note();
		note.setFrom(rs.getInt("from_id"));
		note.setTo(rs.getInt("to_id"));
		note.setMessage(rs.getString("message"));
		note.setTimeStamp(rs.getTimestamp("timestamp"));
		note.setCommitName(rs.getString("commitname"));
		note.setToName(rs.getString("toname"));
		return note;
	}

}
