package ubbl.query_service.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;

import ubbl.query_service.config.SecurityTestConfig;
import ubbl.query_service.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityTestConfig.class)
@AutoConfigureMockMvc
public class HomeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void get_home_without_user_should_redirect_to_login() throws Exception {
		mockMvc.perform(get("/home"))
		    .andExpect(redirectedUrlPattern("**/login"));
	}
	
	@Test @WithUserDetails(userDetailsServiceBeanName="testUserDetailsService", value="testUser")
	public void get_home_with_user_should_return_view_home_and_model_with_username() throws Exception {
		ModelAndView  mv = mockMvc.perform(get("/home")).andReturn().getModelAndView();
		
		assertThat(mv.getViewName(), equalTo("home"));
		assertThat(((UserDetails) mv.getModel().get("user"))
			.getUsername(), equalTo("testUser")); 
	}
}