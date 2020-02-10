/**
 * 
 */
package com.playground.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.playground.model.Response;
import com.playground.model.Results;
import com.playground.model.VinValidationResponse;
import com.playground.service.VINRequestService;

/**
 * @author jayas
 *
 */
@Component
//@Service
public class VINRequestServiceImpl implements VINRequestService{
	
	//ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private DecodeVINExtendedService decodedService;
	
	@Override
	public Response isValid(String vin) {
		VinValidationResponse vinResp = decodedService.getDecodedVIN(vin);
		if(Objects.nonNull(vinResp) && vinResp.getResults()!=null) {
			List<Results> results = vinResp.getResults();
			Response resp =Response.builder().build();
			//Boolean status = false;
			
			results.stream().forEach(result ->{
				if(result!=null) {
					if(Objects.nonNull(result.getVariable()) && Objects.nonNull(result.getValue())) {
						if(result.getVariable().equalsIgnoreCase("make")) {
							resp.setMake(result.getValue());
						} 
						
						if(result.getVariable().equalsIgnoreCase("model")) {
							resp.setModel(result.getValue());
						}
						
						if(result.getVariable().equalsIgnoreCase("Model Year")) {
							resp.setYear(result.getValue());
						}
						
						if(result.getVariable().equalsIgnoreCase("Plant Country")) {
							resp.setPlantCountry(result.getValue());
						}
						
						if(result.getVariable().equalsIgnoreCase("Plant Company Name")) {
							resp.setPlantCompanyName(result.getValue());
						}
						
						if(result.getVariable().equalsIgnoreCase("plant state")) {
							resp.setPlantState(result.getValue());
						}
						
						if(result.getVariable().equalsIgnoreCase("Manufacturer Name")) {
							resp.setManufacturerName(result.getValue());
						}
						
						if(result.getVariable().equalsIgnoreCase("Plant City")) {
							resp.setPlantCity(result.getValue());
						}
						
						if(result.getVariable().equalsIgnoreCase("Vehicle Type")) {
							resp.setVehicleType(result.getValue());
						}									
						
					} 
				}
				
			});
		
			return resp;
		}
		return null;
	}

}
