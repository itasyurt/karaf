package org.itasyurt.karaf.domain.text;

import javax.persistence.Entity;

import org.itasyurt.karaf.domain.entity.BaseEntity;

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
