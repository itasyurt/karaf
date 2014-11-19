package org.itasyurt.jsonize.annotationprocessor;

import java.util.HashMap;
import java.util.Map;

import org.itasyurt.jsonize.anottationprocessor.JsonAnnotationProcessor;
import org.itasyurt.jsonize.anottationprocessor.JsonTree;
import org.itasyurt.jsonize.domain.Match;
import org.junit.Test;

public class JsonAnnotationProcessorTestCase {

	@Test
	public void printJsonAnnotations() {

		JsonAnnotationProcessor processor = new JsonAnnotationProcessor();
		long start = System.currentTimeMillis();
		Map<Class, JsonTree> jsonTreesMap = new HashMap<Class, JsonTree>();

		for (int i = 0; i < 10000; ++i) {
			Class clazz = Match.class;
			if (jsonTreesMap.get(clazz) == null) {
				jsonTreesMap.put(clazz, processor.getDetailedJsonTree(Match.class));
			}

		}

		long end = System.currentTimeMillis();
		System.out.println(end - start);

	}
}