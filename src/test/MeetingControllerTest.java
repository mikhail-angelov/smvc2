package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.ma.model.BookingService;
import org.sample.ma.model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@RunWith(SpringJUnit4ClassRunner.class) //run as junit process
@WebAppConfiguration
@ContextConfiguration(classes=TestConfig.class) //setup test configuration
public class MeetingControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private BookingService service;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testGetMeetingsList() throws Exception {
		//add meeting,
		service.bookMeeting("ma", "3.3.2013 10:30", "2");
		
		//then check it
		MvcResult result =  (MvcResult)mockMvc.perform(get("/api/meetings"))
								.andExpect(status().isOk())
								.andDo(MockMvcResultHandlers.print())
								//.andExpect(content(containsString("ma"))) //it's not complied
								.andReturn();
		assertEquals(true, result.getResponse().getContentAsString().contains("ma"));
		 
	}

	@Test
	public void testAddMeeting() throws Exception {
		
		
//		mockMvc.perform(post("/api/add").contentType(MediaType.APPLICATION_JSON)
//						.content(String.format("{\"userId\":%s,\"meetingStart\":\"%s\",\"duration\":\"%d\"}", "ma","5.5.2013 16:00",1).getBytes()));
		mockMvc.perform(get("/api/add")
				.param("userId", "user1").param("meetingStart", "5.5.2013 16:00").param("duration","1"))
			.andDo(MockMvcResultHandlers.print())
	        .andExpect(status().isOk())
			.andReturn();
		
		List<Meeting> list = service.getMeetingsList();
		assertEquals("user1", list.get(list.size()-1).getUserId()); //one should be added
	}

}
