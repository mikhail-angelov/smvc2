package org.sample.ma.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
	
	private List<Meeting> model;
	{
		model = new LinkedList<Meeting>();//maybe, we should sort it 
	}
	
	public BookingServiceImpl(){
		System.out.println("BookingServiceImpl constr");
	}

	@Override
	public List<Meeting> getMeetingsList() {
		System.out.println("get meetings mdl");
		return model;
	}

	@Override
	public boolean bookMeeting(String userId, String meetingStart, String duration){
		System.out.println("book meeting for " + userId);
		
		
		//do type conversion and validation
		int iduration = convertNumber(duration);
		if(iduration == 0) return false;
		Date start = convertDate(meetingStart);
		if(start == null) return false;
		
		System.out.println(start);
		
		//check for constrains
		if(!checkForWorkingHours(start, iduration)) return false;
		if(!checkForIntersection(start, iduration)) return false;

		//set to new Meeting object
		Meeting meeting = new Meeting();
		meeting.setDate(new Date());
		meeting.setDuration(iduration);
		meeting.setMeetingStart(start);
		meeting.setUserId(userId);
		
		model.add(meeting);
		return true;
	}


	private int convertNumber(String str) {
		int result = 0;
		try {
			result = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			//do nothing
		}
		return result;
	}
	
	private Date convertDate(String str){
		Date date = null;
		try {
			date = new SimpleDateFormat("dd.mm.yyyy hh:mm", Locale.ENGLISH).parse(str);
		} catch (ParseException e) {
			//do nothing
		}
		return date;
	}
	
	private boolean checkForWorkingHours(Date date, int duration) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		if((hourOfDay > 9) && (hourOfDay+duration) < 18 ) {
			return true;
		} else {
			return false;
		}		
	}
	
	//if meeting has intersection with any previous meetings, function returns false
	private boolean checkForIntersection(Date date, int duration) {
		final long hoursInMillis = 60L * 60L * 1000L;
		Iterator<Meeting> itr = model.listIterator();
		while(itr.hasNext()){
			Meeting meeting = itr.next();
			Date end = new Date(date.getTime() + (duration * hoursInMillis));
			Date meetingEnd =  new Date(meeting.getMeetingStart().getTime() + ((long)meeting.getDuration() * hoursInMillis));
			if((date.after(meeting.getMeetingStart()) && date.before(meetingEnd)) ||
			   (end.after(meeting.getMeetingStart()) && end.before(meetingEnd))){
				return false;
			}
		}
		return true;
	}
}
