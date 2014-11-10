package org.itasyurt.karaf.core.text;

import javax.persistence.Entity;

import org.itasyurt.karaf.core.entity.BaseEntity;

@Entity
public class Text  extends BaseEntity{

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
