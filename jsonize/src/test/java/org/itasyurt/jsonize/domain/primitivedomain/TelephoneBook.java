package org.itasyurt.jsonize.domain.primitivedomain;

import java.util.HashMap;
import java.util.Map;

import org.itasyurt.jsonize.annotations.JsonDetail;

public class TelephoneBook {

	@JsonDetail
	Map<String,String> namesAndNumbers= new HashMap<String, String>();

	public Map<String, String> getNamesAndNumbers() {
		return namesAndNumbers;
	}

	public void setNamesAndNumbers(Map<String, String> namesAndNumbers) {
		this.namesAndNumbers = namesAndNumbers;
	}
	
	
}
