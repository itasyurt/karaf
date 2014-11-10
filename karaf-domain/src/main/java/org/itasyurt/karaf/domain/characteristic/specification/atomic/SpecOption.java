package org.itasyurt.karaf.domain.characteristic.specification.atomic;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.itasyurt.karaf.domain.entity.VersionedEntity;
import org.itasyurt.karaf.domain.text.Text;

@Entity
public class SpecOption extends VersionedEntity {

	private String code;

	@OneToOne(cascade = CascadeType.ALL)
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
