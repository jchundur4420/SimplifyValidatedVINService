/**
 * 
 */
/**
 * @author jayas
 *
 */
package com.playground.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playground.model.Request;
import com.playground.model.Response;
import com.playground.model.ResponseData;
import com.playground.service.VINRequestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/vin/validate/" , consumes = MediaType.APPLICATION_JSON_VALUE )
@Api(value = "VIN api")
@CrossOrigin(value = "*")
@ApiResponses(value = { @ApiResponse(code = 201, message = "All fields are validated"),@ApiResponse(code = 400, message = "Not a Valid VIN, preverification failed"), @ApiResponse(code = 404, message = "Not a Valid VIN"),  @ApiResponse(code = 500, message = "Server not available") })
public class VINRequestController {

	@Autowired
	VINRequestService service;

	ObjectMapper mapper = new ObjectMapper();

	/**
	 * @param vin
	 * @return
	 */

	@PostMapping
	public @ResponseBody ResponseData postValid(@RequestBody Request vin) {
		return isValid(vin);
	}

	@GetMapping
	public ResponseData checkValid(@RequestBody Request vin) {
		return isValid(vin);
	}

	public boolean validateConditions(String x) {
		if (x == null || x.isEmpty() || x.length() == 0)
			return false;
		else
			return true;
	}

	public ResponseData preVerification(String vin) {
		ResponseData badResp = null;
		if (vin == null || vin.length()!=17) {
			badResp = ResponseData.builder().status("400").statusMessage("Not a Valid VIN, Preverification failed").response(null).build();
		}
		return badResp;

	}
	
	public ResponseData isValid(Request vin) {
		ResponseData rd = preVerification(vin.getVin());
		if (rd != null) {
			return rd;
		} else {
			rd = new ResponseData();
			Response resp;
			try {
				
				resp = service.isValid(vin.getVin());
				if(Objects.nonNull(resp)) {
					if (validateConditions(resp.getMake()) && validateConditions(resp.getManufacturerName())
							&& validateConditions(resp.getModel()) && validateConditions(resp.getPlantCity())
							&& validateConditions(resp.getPlantCompanyName()) && validateConditions(resp.getPlantCountry())
							&& validateConditions(resp.getPlantState()) && validateConditions(resp.getVehicleType())
							&& validateConditions(resp.getYear())) {
						rd = ResponseData.builder().status("201").response(resp).statusMessage("All fields are validated")
								.build();
						return rd;
	
					} else {
						rd = ResponseData.builder().status("404").response(resp).statusMessage("Not a Valid VIN").build();
						return rd;
					}
				} else {
					rd = ResponseData.builder().status("500").response(null).statusMessage("Server not available").build();
				}
			} catch (Exception e) {
				rd = ResponseData.builder().status("500").response(null).statusMessage("Server not available").build();
			}
		}
		return rd;
	}

}