package org.itasyurt.karaf.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.eclipse.persistence.annotations.UuidGenerator;
import org.itasyurt.jsonize.annotations.JsonSummary;

@MappedSuperclass
@UuidGenerator(name="ID_GEN")
public abstract class BaseEntity {

	@Id
	@GeneratedValue(generator="ID_GEN")
	@JsonSummary
	private String id;

	@Version
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	protected abstract Object[] equalsFields();

	protected abstract Object[] hashCodeFields();

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;

		}

		if (!obj.getClass().isAssignableFrom(getClass())) {
			return false;
		}

		EqualsBuilder builder = new EqualsBuilder();
		BaseEntity rhs = (BaseEntity) obj;

		Object[] rhsFields = rhs.equalsFields();
		Object[] lhsFields = this.equalsFields();

		if (lhsFields == null) {
			if (rhsFields != null) {
				return false;
			} else {
				return true;
			}
		}
		if ((rhsFields == null || (rhsFields.length != lhsFields.length))) {
			return false;
		}

		for (int i = 0; i < lhsFields.length; ++i) {
			builder.append(lhsFields[i], rhsFields[i]);
		}

		return builder.isEquals();
	}

	@Override
	public int hashCode() {
		Object[] fields = hashCodeFields();

		HashCodeBuilder builder = new HashCodeBuilder();
		if (fields != null) {
			for (int i = 0; i < fields.length; ++i) {
				builder.append(fields[i]);
			}

		}
		return builder.hashCode();
	}

}
