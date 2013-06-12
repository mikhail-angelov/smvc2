package test;

import org.easymock.EasyMock;
import org.sample.ma.model.BookingService;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//@ComponentScan("org.sample.ma.controller") //make spring to scan the controller package only
@ComponentScan("org.sample.ma") //scan all beans
@EnableWebMvc
public class TestConfig {
	
	//bean for model stub
//    @Bean public org.sample.ma.model.BookingService myService() {
//        return EasyMock.createMock(org.sample.ma.model.BookingService.class);
//    }

}
