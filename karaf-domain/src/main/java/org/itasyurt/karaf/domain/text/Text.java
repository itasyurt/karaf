package org.itasyurt.karaf.domain.text;

import javax.persistence.Entity;

import org.itasyurt.jsonize.annotations.JsonSummary;
import org.itasyurt.karaf.domain.entity.BaseEntity;

@Entity
public class Text extends BaseEntity {

	@JsonSummary
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	protected Object[] equalsFields() {

		return new Object[] { text };
	}

	@Override
	protected Object[] hashCodeFields() {
		return new Object[] { text };
	}

	@Override
	public String toString() {

		return "" + getText();
	}

}
