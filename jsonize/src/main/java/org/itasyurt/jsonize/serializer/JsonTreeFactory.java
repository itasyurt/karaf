package org.itasyurt.jsonize.serializer;

import org.itasyurt.jsonize.anottationprocessor.JsonTree;

public interface JsonTreeFactory {

	JsonTree getJsonTree(Class clazz);
}
