package org.sample.ma.model;

import java.util.List;

public interface BookingService {
	
	public boolean bookMeeting(String userId, String meetingStart, String duration);
	public List<Meeting> getMeetingsList();

}
