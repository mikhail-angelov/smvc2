package org.sample.ma.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class BookingServiceTest {

	@Test
	public void testBookMeeting() {
		BookingService service = new BookingServiceImpl();
		
		//we can test all constrains and border cases here
		boolean res = service.bookMeeting(null,null,null);
		
		assertEquals(false,res);
	}

	@Test
	public void testGetMeetingsList() {
		BookingService service = new BookingServiceImpl();
		List<Meeting> list = service.getMeetingsList();
		assertEquals(0,list.size()); //maybe we should add one item first
	}

}
