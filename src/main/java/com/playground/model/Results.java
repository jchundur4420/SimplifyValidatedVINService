/**
 * 
 */
package com.playground.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Results {
	
	@JsonProperty("Value")
	private String value;
	@JsonProperty("ValueId")
	private String valueId;
	@JsonProperty("Variable")
	private String variable;
	@JsonProperty("VariableId")
	private String variableId;

}
