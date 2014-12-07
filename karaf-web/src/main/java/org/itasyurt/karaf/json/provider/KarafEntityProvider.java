package org.itasyurt.karaf.json.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.itasyurt.jsonize.serializer.JsonizeSerializer;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Provider
@Produces("application/json")
public class KarafEntityProvider extends JacksonJsonProvider {

	JsonizeSerializer serializer = new JsonizeSerializer();

	@Override
	public void writeTo(Object value, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException {
		Object result = null;
		if (Collection.class.isAssignableFrom(type)) {
			if (value != null) {
				Collection valueCollection = (Collection) value;
				ArrayList resultCollection = new ArrayList();
				for (Object item : valueCollection) {
					resultCollection.add(serializer.convertToDetailedJson(item));
				}
				result = resultCollection;
			}

		} else {
			result = serializer.convertToDetailedJson(value);
		}
		super.writeTo(result, type, genericType, annotations, mediaType, httpHeaders, entityStream);
	}

}
