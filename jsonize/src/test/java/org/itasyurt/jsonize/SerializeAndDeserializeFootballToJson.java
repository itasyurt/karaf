package org.itasyurt.jsonize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.itasyurt.jsonize.domain.football.Association;
import org.itasyurt.jsonize.domain.football.City;
import org.itasyurt.jsonize.domain.football.Club;
import org.itasyurt.jsonize.domain.football.Country;
import org.itasyurt.jsonize.domain.football.FootballObjectRepository;
import org.itasyurt.jsonize.domain.football.GoalInfo;
import org.itasyurt.jsonize.domain.football.GoalKeeper;
import org.itasyurt.jsonize.domain.football.Match;
import org.itasyurt.jsonize.domain.football.MatchScore;
import org.itasyurt.jsonize.domain.football.Person;
import org.itasyurt.jsonize.domain.football.Stadium;
import org.itasyurt.jsonize.domain.football.Tournament;
import org.itasyurt.jsonize.domain.football.TransferList;
import org.itasyurt.jsonize.serializer.JsonizeDeserializer;
import org.itasyurt.jsonize.serializer.JsonizeSerializer;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SerializeAndDeserializeFootballToJson {

	FootballObjectRepository repository = new FootballObjectRepository();

	@Test
	public void convertToJsonAndDeserialize() {

		createDomainObjects();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonizeSerializer serializer = new JsonizeSerializer();
		Map<String, Object> matchJson = serializer.convertToDetailedJson(repository.find(Match.class, "elclassico"));

		Map<String, Object> barcelonaJson = serializer.convertToDetailedJson(repository.find(Club.class, "barcelona"));
		String jsonString = gson.toJson(barcelonaJson);
		Map fromJson = gson.fromJson(jsonString, Map.class);
		System.out.println(fromJson);
		JsonizeDeserializer deserializer = new JsonizeDeserializer();
		deserializer.setRepository(repository);

		Club deserializedBarcelona = deserializer.convertFromJson(Club.class, fromJson);

		Map<String, Object> realMadridJson = serializer.convertToDetailedJson(repository.find(Club.class, "realMadrid"));
		jsonString = gson.toJson(realMadridJson);
		fromJson = gson.fromJson(jsonString, Map.class);
		System.out.println(fromJson);

		Club deserializedRealMadrid = deserializer.convertFromJson(Club.class, fromJson);
		System.out.println(deserializedRealMadrid.getName());

	}

	@Test
	public void serializeAndDeserializeWithSummaryLists() {
		createDomainObjects();
		TransferList transferList = new TransferList();
		transferList.setId("tl1");
		transferList.setName("tl1");
		transferList.getPlayers().add(repository.find(GoalKeeper.class, "casillas"));
		transferList.getPlayers().add(repository.find(Person.class, "messi"));

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonizeSerializer serializer = new JsonizeSerializer();
		Map<String, Object> converted = serializer.convertToDetailedJson(transferList);

		String jsonString = gson.toJson(converted);
		Map fromJson = gson.fromJson(jsonString, Map.class);
		System.out.println(fromJson);
		JsonizeDeserializer deserializer = new JsonizeDeserializer();
		deserializer.setRepository(repository);

		TransferList deserialized = deserializer.convertFromJson(TransferList.class, fromJson);

		System.out.println(deserialized.getName());

	}

	@Test
	public void serializeAndDeserializeWithSummarySets() {
		createDomainObjects();
		Tournament t = new Tournament();
		t.setName("t1");
		t.getClubs().add(repository.find(Club.class, "barcelona"));
		t.getClubs().add(repository.find(Club.class, "realMadrid"));

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonizeSerializer serializer = new JsonizeSerializer();
		Map<String, Object> converted = serializer.convertToDetailedJson(t);

		String jsonString = gson.toJson(converted);
		Map fromJson = gson.fromJson(jsonString, Map.class);
		System.out.println(fromJson);
		JsonizeDeserializer deserializer = new JsonizeDeserializer();
		deserializer.setRepository(repository);

		Tournament deserialized = deserializer.convertFromJson(Tournament.class, fromJson);

		System.out.println(deserialized.getName());

	}

	private void createDomainObjects() {

		repository = new FootballObjectRepository();
		Association uefa = createAssociation("uefa", "uefa", 1954);

		Association conmebol = createAssociation("conmebol", "conmebol", 1916);
		Association caf = createAssociation("caf", "caf", 1957);
		repository.put(uefa);
		repository.put(conmebol);
		repository.put(caf);

		Country argentina = createCountry("argentina", "argentina", conmebol);
		Country brazil = createCountry("brazil", "brazil", conmebol);
		Country spain = createCountry("spain", "spain", uefa);
		Country portugal = createCountry("portugal", "portugal", uefa);
		Country france = createCountry("france", "france", uefa);
		Country italy = createCountry("italy", "italy", uefa);

		repository.put(argentina);
		repository.put(brazil);
		repository.put(spain);
		repository.put(portugal);
		repository.put(france);
		repository.put(italy);

		Country southAfrica = createCountry("southAfrica", "southAfrica", caf);
		repository.put(southAfrica);

		Person messi = createPerson("messi", "messi", argentina);
		Person iniesta = createPerson("iniesta", "iniesta", spain);
		Person neymar = createPerson("neymar", "neymar", brazil);

		Person ronaldo = createPerson("ronaldo", "ronaldo", portugal);
		Person benzema = createPerson("benzema", "benzema", france);
		Person casillas = createGoalKeeper("casillas", "casillas", spain);

		Person luisEnrique = createPerson("luisEnrique", "luisEnrique", spain);
		Person ancelotti = createPerson("ancelotti", "ancelotti", italy);

		repository.put(messi);
		repository.put(iniesta);
		repository.put(neymar);

		repository.put(ronaldo);
		repository.put(benzema);
		repository.put(casillas);

		Club barcelona = createClub("barcelona", "barcelona", spain, luisEnrique, Arrays.asList(messi, iniesta, neymar));
		Club realMadrid = createClub("realMadrid", "realMadrid", spain, ancelotti, Arrays.asList(ronaldo, benzema, casillas));

		repository.put(realMadrid);
		repository.put(barcelona);

		City johannesburg = createCity("name", "name", southAfrica);
		repository.put(johannesburg);
		Stadium soccerCity = createStadium("soccerCity", "soccerCity", johannesburg);
		repository.put(soccerCity);

		MatchScore score = new MatchScore();
		score.getHomeGoalInfo().add(createGoalInfo(messi, 36));
		score.getHomeGoalInfo().add(createGoalInfo(messi, 73));
		score.getAwayGoalInfo().add(createGoalInfo(ronaldo, 51));

		Match match = createMatch("elclassico", new Date(), soccerCity, barcelona, realMadrid, score);
		repository.put(match);

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

	private GoalKeeper createGoalKeeper(String id, String name, Country country) {

		GoalKeeper result = new GoalKeeper();
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
		result.setId(id);
		result.setName(name);
		result.setFoundationYear(foundationYear);
		return result;
	}

}
