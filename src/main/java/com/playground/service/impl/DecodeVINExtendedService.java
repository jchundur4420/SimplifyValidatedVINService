/**
 * 
 */
package com.playground.service.impl;

/*import java.util.ArrayList;
import java.util.List;*/

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
/*import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;*/
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playground.model.VinValidationResponse;

/**
 * @author jayas
 *
 */
@Service
public class DecodeVINExtendedService {
	private static final org.slf4j.Logger log= LoggerFactory.getLogger(DecodeVINExtendedService.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	ObjectMapper mapper = new ObjectMapper();
	
	private String url = "https://vpic.nhtsa.dot.gov/api/vehicles/DecodeVinExtended/";

	public VinValidationResponse getDecodedVIN(String vin) {
	//	log.info("vin" +vin);
		VinValidationResponse vResp = restTemplate.getForObject(url+vin+"?format=json", VinValidationResponse.class);
	//	log.info("Message" +vResp.getMessage());
	//	log.info("Count" +vResp.getCount());
		return vResp;
	}
	
	@Bean
	private RestTemplate restTemplate() {
		return new RestTemplate();
	}
		

}
