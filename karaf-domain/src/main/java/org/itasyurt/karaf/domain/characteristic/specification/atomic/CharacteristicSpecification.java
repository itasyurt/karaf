package org.itasyurt.karaf.domain.characteristic.specification.atomic;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.itasyurt.jsonize.annotations.JsonSummary;
import org.itasyurt.karaf.domain.NamedEntity;
import org.itasyurt.karaf.domain.entity.VersionedEntity;
import org.itasyurt.karaf.domain.text.Text;

@MappedSuperclass
public abstract class CharacteristicSpecification extends VersionedEntity implements NamedEntity {

	@JsonSummary
	private String code;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonSummary
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

	@Override
	protected Object[] equalsFields() {
		// TODO Auto-generated method stub
		return new Object[] { code };
	}

	@Override
	protected Object[] hashCodeFields() {

		return new Object[] { code };
	}

	@Override
	public void setName(String name) {
		this.name = new Text();
		this.name.setText(name);

	}

}
