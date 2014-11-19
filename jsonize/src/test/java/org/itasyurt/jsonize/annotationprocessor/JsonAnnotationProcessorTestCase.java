package org.itasyurt.jsonize.annotationprocessor;

import org.itasyurt.jsonize.anottationprocessor.JsonAnnotationProcessor;
import org.itasyurt.jsonize.domain.Match;
import org.junit.Test;

public class JsonAnnotationProcessorTestCase {

	@Test
	public void printJsonAnnotations() {

		JsonAnnotationProcessor processor= new JsonAnnotationProcessor();
		
		processor.printDetailJsonTree(Match.class);
	}
}