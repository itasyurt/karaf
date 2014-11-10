package org.itasyurt.karaf.core.characteristic.specification.atomic;

import org.itasyurt.karaf.core.text.Text;

public abstract class CharacteristicSpecification {

	private String code;
	private Text name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Text getName() {
		return name;
	}

	public void setName(Text name) {
		this.name = name;
	}

}
