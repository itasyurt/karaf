package org.itasyurt.jsonize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.itasyurt.jsonize.anottationprocessor.JsonAnnotationProcessor;
import org.itasyurt.jsonize.domain.Association;
import org.itasyurt.jsonize.domain.City;
import org.itasyurt.jsonize.domain.Club;
import org.itasyurt.jsonize.domain.Country;
import org.itasyurt.jsonize.domain.GoalInfo;
import org.itasyurt.jsonize.domain.Match;
import org.itasyurt.jsonize.domain.MatchScore;
import org.itasyurt.jsonize.domain.Person;
import org.itasyurt.jsonize.domain.Stadium;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConvertToPlainJson {

	@Test
	public void convertToPlainJson() {

		Association uefa = createAssociation("id", "uefa", 1954);
		Association conmebol = createAssociation("id", "conmebol", 1916);
		Association caf = createAssociation("id", "conmebol", 1957);

		Country argentina = createCountry("id", "argentina", conmebol);
		Country brazil = createCountry("id", "brazil", conmebol);
		Country spain = createCountry("id", "spain", uefa);
		Country portugal = createCountry("id", "portugal", uefa);
		Country france = createCountry("id", "france", uefa);
		Country italy = createCountry("id", "italy", uefa);

		Country southAfrica = createCountry("id", "southAfrica", caf);

		Person messi = createPerson("id", "messi", argentina);
		Person iniesta = createPerson("id", "iniesta", spain);
		Person neymar = createPerson("id", "neymar", brazil);

		Person ronaldo = createPerson("id", "iniesta", portugal);
		Person benzema = createPerson("id", "benzema", france);
		Person casillas = createPerson("id", "casillas", spain);

		Person luisEnrique = createPerson("id", "luisEnrique", spain);
		Person ancelotti = createPerson("id", "ancelotti", italy);

		Club barcelona = createClub("id", "barcelona", spain, luisEnrique, Arrays.asList(messi, iniesta, neymar));
		Club realMadrid = createClub("id", "realMadrid", spain, ancelotti, Arrays.asList(ronaldo, benzema, casillas));

		City johannesburg = createCity("id", "name", southAfrica);
		Stadium soccerCity = createStadium("id", "soccerCity", johannesburg);

		MatchScore score = new MatchScore();
		score.getHomeGoalInfo().add(createGoalInfo(messi, 36));
		score.getHomeGoalInfo().add(createGoalInfo(messi, 73));
		score.getAwayGoalInfo().add(createGoalInfo(ronaldo, 51));

		Match match = createMatch("id", new Date(), soccerCity, barcelona, realMadrid, score);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonAnnotationProcessor processor = new JsonAnnotationProcessor();
		Map<String, Object> matchJson = processor.convertToDetailedJson(match);

		Map<String, Object> convertedJson = processor.convertToDetailedJson(italy);
		System.out.println(gson.toJson(convertedJson));
		// System.out.println(gson.toJson(match));

	}

	private GoalInfo createGoalInfo(Person player, int minute) {
		GoalInfo result = new GoalInfo();
		result.setPlayer(player);
		result.setMinute(minute);
		return result;

	}

	private City createCity(String id, String name, Country country) {
		City result = new City();
		result.setId(id);
		result.setName(name);
		result.setCountry(country);
		return result;
	}

	private Stadium createStadium(String id, String name, City city) {
		Stadium result = new Stadium();
		result.setId(id);
		result.setName(name);
		result.setCity(city);
		return result;
	}

	private Match createMatch(String id, Date matchDate, Stadium stadium, Club homeTeam, Club awayTeam, MatchScore score) {
		Match match = new Match();
		match.setId(id);
		match.setMatchDate(matchDate);
		match.setStadium(stadium);
		match.setHomeTeam(homeTeam);
		match.setAwayTeam(awayTeam);
		match.setScore(score);

		return match;
	}

	private Person createPerson(String id, String name, Country country) {

		Person result = new Person();
		result.setId(id);
		result.setName(name);
		result.setCountry(country);
		return result;
	}

	private Country createCountry(String id, String name, Association association) {

		Country result = new Country();
		result.setId(id);
		result.setName(name);
		result.setAssociation(association);
		return result;
	}

	private Club createClub(String id, String name, Country country, Person manager, List<Person> players) {

		Club result = new Club();
		result.setId(id);
		result.setName(name);
		result.setCountry(country);
		result.setManager(manager);
		result.setPlayers(new ArrayList<Person>(players));
		return result;
	}

	private Association createAssociation(String id, String name, Integer foundationYear) {
		Association result = new Association();
		result.setId("id");
		result.setName("name");
		result.setFoundationYear(foundationYear);
		return result;
	}

}
