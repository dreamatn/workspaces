package com.dreamatn.springboot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dreamatn.springboot.controller.HelloWorldController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MockServletContext.class)
@WebAppConfiguration
public class HelloWorldControllerTests {
	private MockMvc mvc;
	@Before
	public void setUp() throws Exception{
		mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
	}
	@Test
	public void getHello() throws Exception{
		
	}
}
