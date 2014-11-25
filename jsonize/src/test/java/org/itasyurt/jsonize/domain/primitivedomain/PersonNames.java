package org.itasyurt.jsonize.domain.primitivedomain;

import java.util.ArrayList;
import java.util.List;

import org.itasyurt.jsonize.annotations.JsonDetail;

public class PersonNames {

	@JsonDetail
	List<String> names = new ArrayList<String>();

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

}
