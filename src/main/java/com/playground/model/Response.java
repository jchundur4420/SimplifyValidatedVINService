/**
 * 
 */
package com.playground.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jayas
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
@JsonIgnoreProperties(ignoreUnknown=true)
public class Response {
	
	private String make;
	
	private String model;
	
	private String year;
	private String plantCountry;
	private String plantCity;
	
	private String ManufacturerName;
	private String plantCompanyName;
	private String plantState;

	private String VehicleType;

}
