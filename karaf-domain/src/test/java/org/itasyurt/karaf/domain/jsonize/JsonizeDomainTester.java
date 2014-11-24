package org.itasyurt.karaf.domain.jsonize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.itasyurt.jsonize.serializer.JsonizeSerializer;
import org.itasyurt.karaf.domain.DataCreationUtils;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.AtomicCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.BooleanCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SelectionCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.domain.characteristic.specification.combined.CombinedCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.ConditionalCharacteristicSpecification;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonizeDomainTester {

	@Test
	public void selectionCharacteristicJsonizedSuccessfully() {
		SelectionCharacteristicSpecification illerCharacteristic = DataCreationUtils.createSCS("iller", "ankara", "istanbul", "izmir");
		JsonizeSerializer serializer = new JsonizeSerializer();
		Map<String, Object> convertedJson = serializer.convertToDetailedJson(illerCharacteristic);

		System.out.println(convertedJson);

	}

	@Test
	public void combinedCharacteristicJsonizedSuccessfully() {
		SelectionCharacteristicSpecification illerCharacteristic = DataCreationUtils.createSCS("iller", "ankara", "istanbul", "izmir");

		SelectionCharacteristicSpecification ilcelerCharacteristic = DataCreationUtils.createSCS("ilceler", "cankaya", "besiktas", "konak", "buca");

		Map<Integer, List<Integer>> relatedOptionsMap = new HashMap<Integer, List<Integer>>();
		relatedOptionsMap.put(0, Arrays.asList(0));
		relatedOptionsMap.put(1, Arrays.asList(1));
		relatedOptionsMap.put(2, Arrays.asList(2, 3));

		CombinedCharacteristicSpecification ccs = DataCreationUtils.createCombinedCS("il_ilce", illerCharacteristic, ilcelerCharacteristic, relatedOptionsMap);

		JsonizeSerializer serializer = new JsonizeSerializer();
		Map<String, Object> convertedJson = serializer.convertToDetailedJson(ccs);

		System.out.println(convertedJson);

	}
	
	@Test
	public void conditionalCharacteristicJsonizedSuccessfully() {
		SelectionCharacteristicSpecification illerCharacteristic = DataCreationUtils.createSCS("iller", "ankara", "istanbul", "izmir");

		AtomicCharacteristicSpecification boyoz = new BooleanCharacteristicSpecification();
		boyoz.setCode("boyoz");
		boyoz.setName("boyoz");
		

		AtomicCharacteristicSpecification gevrek = new BooleanCharacteristicSpecification();
		gevrek.setCode("gevrek");
		gevrek.setName("gevrek");
		

		AtomicCharacteristicSpecification doner = new BooleanCharacteristicSpecification();
		doner.setCode("doner");
		doner.setName("doner");
	
		AtomicCharacteristicSpecification islakHamburger = new BooleanCharacteristicSpecification();
		islakHamburger.setCode("islakHamburger");
		islakHamburger.setName("islakHamburger");
		
		AtomicCharacteristicSpecification dilliKasarli = new BooleanCharacteristicSpecification();
		dilliKasarli.setCode("dilliKasarli");
	
		Map<SpecOption, List<AtomicCharacteristicSpecification>> relatedCharacteristicsMap = new HashMap<SpecOption, List<AtomicCharacteristicSpecification>>();
		relatedCharacteristicsMap.put(illerCharacteristic.getOptions().get(0), Arrays.asList(doner));
		relatedCharacteristicsMap.put(illerCharacteristic.getOptions().get(1), Arrays.asList(doner, islakHamburger, dilliKasarli));
		relatedCharacteristicsMap.put(illerCharacteristic.getOptions().get(2), Arrays.asList(gevrek, boyoz));

		ConditionalCharacteristicSpecification ccs = DataCreationUtils.createConditionalCS("il_yemek", illerCharacteristic, relatedCharacteristicsMap);

		JsonizeSerializer serializer = new JsonizeSerializer();
		Map<String, Object> convertedJson = serializer.convertToDetailedJson(ccs);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(convertedJson));

	}

}
