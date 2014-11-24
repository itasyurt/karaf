package org.itasyurt.jsonize;

import java.util.Date;
import java.util.Map;

import org.itasyurt.jsonize.anottationprocessor.JsonAnnotationProcessor;
import org.itasyurt.jsonize.domain.football.Match;
import org.itasyurt.jsonize.domain.university.Student;
import org.itasyurt.jsonize.domain.university.Thesis;
import org.itasyurt.jsonize.domain.university.University;
import org.itasyurt.jsonize.serializer.JsonizeDeserializer;
import org.itasyurt.jsonize.serializer.JsonizeSerializer;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerializeAndDeserializeToJson {

	@Test
	public void convertToPlainJson() {
		Student joe = createStudent("1", "joe");
		Student william = createStudent("2", "william");
		Student jack = createStudent("3", "jack");
		Student avarel = createStudent("4", "avarel");
		Thesis tJoe = createThesis("TJoe", joe);
		Thesis tWilliam = createThesis("TWilliam", william);
		Thesis tJack = createThesis("TJack", jack);
		Thesis tAvarel = createThesis("TAvarel", avarel);

		University university = new University();
		university.setName("DaltonUniversity");
		university.getStudentMap().put("1", joe);
		university.getStudentMap().put("2", william);
		university.getStudentMap().put("3", jack);
		university.getStudentMap().put("4", avarel);

		university.getThesesMap().put(joe, tJoe);
		university.getThesesMap().put(william, tWilliam);
		university.getThesesMap().put(jack, tJack);
		university.getThesesMap().put(avarel, tAvarel);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonizeSerializer serializer = new JsonizeSerializer();
		Map<String, Object> uniJson = serializer.convertToDetailedJson(university);
		String jsonString = gson.toJson(uniJson);
		Map fromJson = gson.fromJson(jsonString, Map.class);
		System.out.println(fromJson);
		JsonizeDeserializer deserializer= new JsonizeDeserializer();
		University deserializedUniversity=deserializer.convertFromJson(University.class, fromJson);
		System.out.println(deserializedUniversity.getName());

	}

	private Student createStudent(String studentNumber, String name) {

		Student result = new Student();
		result.setStudentNumber(studentNumber);
		result.setName(name);
		return result;
	}

	private Thesis createThesis(String title, Student student) {
		Thesis result = new Thesis();
		result.setStudent(student);
		result.setTitle(title);
		return result;
	}
}
