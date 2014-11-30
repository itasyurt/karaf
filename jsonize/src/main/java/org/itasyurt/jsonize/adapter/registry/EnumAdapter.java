package org.itasyurt.jsonize.adapter.registry;

import org.itasyurt.jsonize.adapter.AbstractTypeAdapter;

public class EnumAdapter extends AbstractTypeAdapter<Enum> {

	private Class<Enum> clazz;
	public EnumAdapter(Class<Enum> clazz) {
		this.clazz= clazz;
	}
	@Override
	public Enum convertToObject(String s) {
		
		return Enum.valueOf(clazz, s);
	}
	
	
	
	

}
