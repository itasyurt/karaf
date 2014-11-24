package org.itasyurt.jsonize.adapter;

public class CharacterAdapter extends AbstractTypeAdapter<Character> {

	@Override
	public Character convertToObject(String s) {

		if (s.length() == 1) {
			return s.charAt(0);
		} else if (s.length() > 0) {
			throw new IllegalArgumentException("Not a Character!");
		}
		return null;
	}

}
