package com.sas.interview.map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import org.skyscreamer.jsonassert.JSONAssert;
import static org.skyscreamer.jsonassert.JSONCompareMode.LENIENT;


@RunWith(SpringRunner.class)
@WebMvcTest(value=MapController.class, secure=false)
public class MapApplicationTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	public void contextLoads() {
	}

	@Test
	public void create() throws Exception {
		RequestBuilder builder = MockMvcRequestBuilders.get("/map").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		String content = result.getResponse().getContentAsString();
		//String expected = 
		//JSONAssert.assertEquals(expected, content, LENIENT);
	}

	@Test
	public void read() throws Exception {
		RequestBuilder builder = MockMvcRequestBuilders.get("/map/color").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		String content = result.getResponse().getContentAsString();
		//String expected = 
		//JSONAssert.assertEquals(expected, content, LENIENT);
	}

	@Test
	public void readAll() throws Exception {
		RequestBuilder builder = MockMvcRequestBuilders.get("/map").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		String content = result.getResponse().getContentAsString();
		//String expected = 
		//JSONAssert.assertEquals(expected, content, LENIENT);
	}

	@Test
	public void update() throws Exception {
		RequestBuilder builder = MockMvcRequestBuilders.get("/map").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		String content = result.getResponse().getContentAsString();
		//String expected = 
		//JSONAssert.assertEquals(expected, content, LENIENT);
	}

	@Test
	public void delete() throws Exception {
		RequestBuilder builder = MockMvcRequestBuilders.get("/map/color").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		String content = result.getResponse().getContentAsString();
		//String expected = 
		//JSONAssert.assertEquals(expected, content, LENIENT);
	}

}
