package org.itasyurt.jsonize.domain.university;

import java.util.HashMap;
import java.util.Map;

import org.itasyurt.jsonize.annotations.JsonDetail;
import org.itasyurt.jsonize.annotations.JsonSummary;

public class University {

	@JsonSummary
	private String name;
	@JsonDetail
	private Map<String, Student> studentMap= new HashMap<String, Student>();
	@JsonDetail
	private Map<Student, Thesis> thesesMap= new HashMap<Student, Thesis>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Student> getStudentMap() {
		return studentMap;
	}
	public void setStudentMap(Map<String, Student> studentMap) {
		this.studentMap = studentMap;
	}
	public Map<Student, Thesis> getThesesMap() {
		return thesesMap;
	}
	public void setThesesMap(Map<Student, Thesis> thesesMap) {
		this.thesesMap = thesesMap;
	}
	
	
	
	
}
