/**
 * 
 */
package com.playground.VINRequestControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.playground.controller.VINRequestController;

/**
 * @author jayas
 *
 */
public class mockControllerTests {
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	VINRequestController vinRequestController;
	
	@Test
	public void mockTestInvalidVINPreVerify() {
		String request = "{\"vin\" : \"\"}";
		try {
			mvc.perform(post("/vin/validate/").content(request).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(jsonPath("status", Matchers.is("400"))).andExpect(jsonPath("statusMessage", Matchers.is("Not a Valid VIN, Preverification failed")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			e.getMessage();
		}
	}
	
	@Test
	public void mockTestValidVIN() {
		String request = "{\"vin\" : \"\12345678901234567\"}";
		try {
			mvc.perform(post("/vin/validate/").content(request).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(jsonPath("status", Matchers.is("201"))).andExpect(jsonPath("statusMessage", Matchers.is("All fields are validated")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	@Test
	public void mockTestInValidVIN() {
		String request = "{\"vin\" : \"\12345678901234567\"}";
		try {
			mvc.perform(post("/vin/validate/").content(request).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andExpect(jsonPath("status", Matchers.is("404"))).andExpect(jsonPath("statusMessage", Matchers.is("Not a Valid VIN")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	

}
