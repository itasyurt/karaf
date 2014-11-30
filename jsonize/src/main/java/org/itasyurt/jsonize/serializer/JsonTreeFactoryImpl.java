package org.itasyurt.jsonize.serializer;

import java.util.HashMap;
import java.util.Map;

import org.itasyurt.jsonize.anottationprocessor.JsonAnnotationProcessor;
import org.itasyurt.jsonize.anottationprocessor.JsonTree;

public class JsonTreeFactoryImpl implements JsonTreeFactory {

	Map<Class, JsonTree> cache = new HashMap<Class, JsonTree>();
	JsonAnnotationProcessor processor = new JsonAnnotationProcessor();

	public JsonTree getJsonTree(Class clazz) {

		JsonTree result = cache.get(clazz);
		if (result == null) {
			result = processor.getDetailedJsonTree(clazz);
			cache.put(clazz, result);

		}

		return result;
	}
}
