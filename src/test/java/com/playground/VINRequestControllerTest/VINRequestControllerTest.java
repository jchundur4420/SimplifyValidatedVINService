package com.playground.VINRequestControllerTest;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.playground.model.Request;
import com.playground.model.ResponseData;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VINRequestControllerTest {

	@LocalServerPort
	int randomServerPort;

	//private static HttpEntity<Request> request;

	private static URI uri;

	private static ObjectMapper mapper = new ObjectMapper();;
	
	//private HttpHeaders headers = new HttpHeaders();
	
	private Request req = new Request();
	private RestTemplate restTemplate = new RestTemplate();

	@Before
	public void setup() {
		final String baseUrl = "http://localhost:" + 8082 + "/vin/validate/";
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			e.getMessage();
		}
	}

	@Test
	public void postGetResponse() {
		req.setVin("1FAHP3K27CL106954");
		ResponseEntity<String> result = restTemplate.postForEntity(uri, req, String.class);
		// Verify request succeed
		Assertions.assertEquals(200, result.getStatusCodeValue());
		try {
			ResponseData data = mapper.readValue(result.getBody(), ResponseData.class);
			if(data!=null && data.getResponse()!=null) {
				Assertions.assertEquals("201", data.getStatus());
				Assertions.assertNotNull(data.getResponse());
				Assertions.assertNotNull(data.getResponse().getPlantState());}
			else {
				Assertions.assertEquals("500", data.getStatus());
				Assertions.assertEquals("Server not available", data.getStatusMessage());
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			;
		}

	}

	/*
	 * @Test public void postServerError() { ResponseEntity<String> result =
	 * restTemplate.postForEntity(uri, req, String.class); // Verify requestsucceed
	 * Assertions.assertEquals(200, result.getStatusCodeValue()); try { ResponseData
	 * data = mapper.readValue(result.getBody(), ResponseData.class); //
	 * Assertions.assertEquals("500", data.getStatus()); } catch
	 * (JsonProcessingException e) { e.getMessage(); } }
	 */

	@Test
	public void postInvalidVINLessDigits() {
		//Request request = new Request();
		req.setVin("1FAHP354");
		ResponseEntity<String> result = restTemplate.postForEntity(uri, req, String.class);
		// Verify request succeed
		Assertions.assertEquals(200, result.getStatusCodeValue());
		try {
			ResponseData data = mapper.readValue(result.getBody(), ResponseData.class);
			Assertions.assertEquals("400", data.getStatus());
			Assertions.assertEquals("Not a Valid VIN, Preverification failed", data.getStatusMessage());
		} catch (JsonProcessingException e) {
			e.getMessage();
		}

	}

	@Test
	public void postInvalidVIN() {
		req.setVin("12345678901234567");
		ResponseEntity<String> result = restTemplate.postForEntity(uri, req, String.class);
		// Verify request succeed
		Assertions.assertEquals(200, result.getStatusCodeValue());
		try {
			ResponseData data = mapper.readValue(result.getBody(), ResponseData.class);
			if(data!=null && data.getResponse()!=null) {
				Assertions.assertEquals("404", data.getStatus());
				Assertions.assertEquals("Not a Valid VIN", data.getStatusMessage());
			} else {
				Assertions.assertEquals("500", data.getStatus());
				Assertions.assertEquals("Server not available", data.getStatusMessage());
			}
		} catch (JsonProcessingException e) {
			e.getMessage();
		}

	}

	@Test
	public void postInvalidVINNullCheck() {
		req.setVin("null");
		ResponseEntity<String> result = restTemplate.postForEntity(uri, req, String.class);
		// Verify request succeed
		Assertions.assertEquals(200, result.getStatusCodeValue());
		try {
			ResponseData data = mapper.readValue(result.getBody(), ResponseData.class);
			Assertions.assertEquals("400", data.getStatus());
			Assertions.assertEquals("Not a Valid VIN, Preverification failed", data.getStatusMessage());
		} catch (JsonProcessingException e) {
			e.getMessage();
		}
	}

	@Test
	public void postInvalidVINEmptyStringCheck() {
		req.setVin("");
		ResponseEntity<String> result = restTemplate.postForEntity(uri, req, String.class);
		// Verify request succeed
		Assertions.assertEquals(200, result.getStatusCodeValue());
		try {
			ResponseData data = mapper.readValue(result.getBody(), ResponseData.class);
			Assertions.assertEquals("400", data.getStatus());
			Assertions.assertEquals("Not a Valid VIN, Preverification failed", data.getStatusMessage());
		} catch (JsonProcessingException e) {
			e.getMessage();
		}

	}
}
