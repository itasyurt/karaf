package org.itasyurt.jsonize.domain.university;

import org.itasyurt.jsonize.annotations.JsonSummary;
import org.itasyurt.jsonize.map.KeyContainer;

public class Thesis implements KeyContainer {

	@JsonSummary
	private String title;

	@JsonSummary
	private Student student;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Object getKey() {

		return student;
	}

}
