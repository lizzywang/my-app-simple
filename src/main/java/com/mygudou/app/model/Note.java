package com.mygudou.app.model;

import java.sql.Timestamp;

public class Note {
	private int from;
	private int to;
	private String message;
	private Timestamp timeStamp;
	private String commitName;
	private String toName;
	private int checked;
	
	
	public int getChecked() {
        return checked;
    }
    public void setChecked(int checked) {
        this.checked = checked;
    }
    public String getCommitName() {
		return commitName;
	}
	public void setCommitName(String commitName) {
		this.commitName = commitName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
}
