/**
 * 
 */
package com.playground.model;

import io.swagger.annotations.ApiModel;

/**
 * @author jayas
 *
 */

@ApiModel
public class Request {
	
	private String vin;

	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}

}
