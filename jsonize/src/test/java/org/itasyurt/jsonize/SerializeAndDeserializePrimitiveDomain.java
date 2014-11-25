package org.itasyurt.jsonize;

import java.util.Map;

import org.itasyurt.jsonize.domain.primitivedomain.PersonNames;
import org.itasyurt.jsonize.domain.primitivedomain.TelephoneBook;
import org.itasyurt.jsonize.serializer.JsonizeDeserializer;
import org.itasyurt.jsonize.serializer.JsonizeSerializer;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerializeAndDeserializePrimitiveDomain {

	@Test
	public void serializeAndDeserializePrimitiveList() {
		PersonNames names = new PersonNames();
		names.getNames().add("john");
		names.getNames().add("mary");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		JsonizeSerializer serializer = new JsonizeSerializer();

		Map<String, Object> jsonMap = serializer.convertToDetailedJson(names);
		String jsonString = gson.toJson(jsonMap);
		Map fromJson = gson.fromJson(jsonString, Map.class);
		JsonizeDeserializer deserializer = new JsonizeDeserializer();

		PersonNames deserializedNames = deserializer.convertFromJson(PersonNames.class, fromJson);
		Assert.assertTrue(deserializedNames.getNames().size() == 2);

	}

	@Test
	public void serializeAndDeserializePrimitiveMap() {
		TelephoneBook book = new TelephoneBook();
		book.getNamesAndNumbers().put("john", "1");
		book.getNamesAndNumbers().put("mary", "2");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonizeSerializer serializer = new JsonizeSerializer();

		Map<String, Object> jsonMap = serializer.convertToDetailedJson(book);
		String jsonString = gson.toJson(jsonMap);
		Map fromJson = gson.fromJson(jsonString, Map.class);
		JsonizeDeserializer deserializer = new JsonizeDeserializer();

		TelephoneBook deserialized = deserializer.convertFromJson(TelephoneBook.class, fromJson);
		Assert.assertTrue(deserialized.getNamesAndNumbers().values().size() == 2);

	}
}
