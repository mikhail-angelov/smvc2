package org.sample.ma.model;

import java.io.Serializable;
import java.util.Date;

public class Meeting implements Serializable{

	private static final long serialVersionUID = -1562555751765231582L;
	private Date date;
	private String userId;
	private Date meetingStart;
	private float duration;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getMeetingStart() {
		return meetingStart;
	}
	public void setMeetingStart(Date meetingStart) {
		this.meetingStart = meetingStart;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	
}
