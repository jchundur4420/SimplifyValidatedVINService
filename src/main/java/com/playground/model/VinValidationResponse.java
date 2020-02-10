/**
 * 
 */
package com.playground.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@JsonIgnoreProperties(ignoreUnknown=true)
public class VinValidationResponse {
	@JsonProperty("Count")
	private String count;
	@JsonProperty("Message")
	private String message;
	@JsonProperty("SearchCriteria")
	private String searchCriteria;
	@JsonProperty("Results")
	private List<Results> results;

}
