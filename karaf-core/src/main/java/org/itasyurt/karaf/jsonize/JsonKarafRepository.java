package org.itasyurt.karaf.jsonize;

import java.util.Map;

import org.itasyurt.jsonize.repository.ObjectRepository;
import org.itasyurt.karaf.dao.common.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonKarafRepository implements ObjectRepository {

	private static final String ID = "id";
	
	@Autowired
	CommonDao dao;

	@SuppressWarnings("rawtypes")
	@Override
	public <T> T find(Class<T> clazz, Object key) {
		Map objMap = (Map) key;
		return dao.find(clazz, (String) (objMap.get(ID)));
	}

}
