package org.itasyurt.karaf.dao.jsonize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.itasyurt.jsonize.serializer.JsonizeDeserializer;
import org.itasyurt.jsonize.serializer.JsonizeSerializer;
import org.itasyurt.karaf.dao.common.CommonDao;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.AtomicCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.BooleanCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SelectionCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.domain.characteristic.specification.combined.CombinedCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.ConditionalCharacteristicSpecification;
import org.itasyurt.karaf.jsonize.JsonKarafRepository;
import org.itasyurt.karaf.util.DataCreationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
@TransactionConfiguration(defaultRollback = true)
public class KarafJsonizeSerializeDeserializeTest {

	@Autowired
	CommonDao dao;

	@Autowired
	JsonKarafRepository repository;

	JsonizeSerializer serializer = new JsonizeSerializer();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Test
	@Transactional
	public void scsSerializedAndDeserializedSuccessfully() {
		SelectionCharacteristicSpecification illerCharacteristic = DataCreationUtils.createSCS("iller", "ankara", "istanbul", "izmir");

		illerCharacteristic = dao.save(illerCharacteristic);

		String json = gson.toJson(serializer.convertToDetailedJson(illerCharacteristic));

		JsonizeDeserializer deserializer = new JsonizeDeserializer();
		deserializer.setRepository(repository);
		Map jsonMap = gson.fromJson(json, Map.class);

		SelectionCharacteristicSpecification deserialized = deserializer.convertFromJson(SelectionCharacteristicSpecification.class, jsonMap);
		Assert.isTrue(3 == deserialized.getOptions().size());

	}

	@Test
	@Transactional
	public void ccsSerializedAndDeserializedSuccessfully() {
		SelectionCharacteristicSpecification illerCharacteristic = DataCreationUtils.createSCS("iller", "ankara", "istanbul", "izmir");

		illerCharacteristic = dao.save(illerCharacteristic);

		AtomicCharacteristicSpecification boyoz = new BooleanCharacteristicSpecification();
		boyoz.setCode("boyoz");
		boyoz.setName("boyoz");
		dao.save(boyoz);

		AtomicCharacteristicSpecification gevrek = new BooleanCharacteristicSpecification();
		gevrek.setCode("gevrek");
		gevrek.setName("gevrek");
		dao.save(gevrek);

		AtomicCharacteristicSpecification doner = new BooleanCharacteristicSpecification();
		doner.setCode("doner");
		doner.setName("doner");
		dao.save(doner);

		AtomicCharacteristicSpecification islakHamburger = new BooleanCharacteristicSpecification();
		islakHamburger.setCode("islakHamburger");
		islakHamburger.setName("islakHamburger");
		dao.save(islakHamburger);

		AtomicCharacteristicSpecification dilliKasarli = new BooleanCharacteristicSpecification();
		dilliKasarli.setCode("dilliKasarli");
		dao.save(dilliKasarli);

		Map<SpecOption, List<AtomicCharacteristicSpecification>> relatedCharacteristicsMap = new HashMap<SpecOption, List<AtomicCharacteristicSpecification>>();
		relatedCharacteristicsMap.put(illerCharacteristic.getOptions().get(0), Arrays.asList(doner));
		relatedCharacteristicsMap.put(illerCharacteristic.getOptions().get(1), Arrays.asList(doner, islakHamburger, dilliKasarli));
		relatedCharacteristicsMap.put(illerCharacteristic.getOptions().get(2), Arrays.asList(gevrek, boyoz));

		ConditionalCharacteristicSpecification ccs = DataCreationUtils.createConditionalCS("il_yemek", illerCharacteristic, relatedCharacteristicsMap);

		ccs = dao.save(ccs);

		String json = gson.toJson(serializer.convertToDetailedJson(ccs));

		JsonizeDeserializer deserializer = new JsonizeDeserializer();
		deserializer.setRepository(repository);
		Map jsonMap = gson.fromJson(json, Map.class);

		ConditionalCharacteristicSpecification deserialized = deserializer.convertFromJson(ConditionalCharacteristicSpecification.class, jsonMap);
		Assert.isTrue(3 == deserialized.getConditionalCharacteristics().size());
	}

	@Test
	@Transactional
	public void combinedSavedAndFoundSuccessfully() {
		SelectionCharacteristicSpecification illerCharacteristic = DataCreationUtils.createSCS("iller", "ankara", "istanbul", "izmir");

		illerCharacteristic = dao.save(illerCharacteristic);

		SelectionCharacteristicSpecification ilcelerCharacteristic = DataCreationUtils.createSCS("ilceler", "cankaya", "besiktas", "konak", "buca");

		ilcelerCharacteristic = dao.save(ilcelerCharacteristic);

		Map<Integer, List<Integer>> relatedOptionsMap = new HashMap<Integer, List<Integer>>();
		relatedOptionsMap.put(0, Arrays.asList(0));
		relatedOptionsMap.put(1, Arrays.asList(1));
		relatedOptionsMap.put(2, Arrays.asList(2, 3));

		CombinedCharacteristicSpecification ccs = DataCreationUtils.createCombinedCS("il_ilce", illerCharacteristic, ilcelerCharacteristic, relatedOptionsMap);

		ccs = dao.save(ccs);
		String json = gson.toJson(serializer.convertToDetailedJson(ccs));

		JsonizeDeserializer deserializer = new JsonizeDeserializer();
		deserializer.setRepository(repository);
		Map jsonMap = gson.fromJson(json, Map.class);

		CombinedCharacteristicSpecification deserialized = deserializer.convertFromJson(CombinedCharacteristicSpecification.class, jsonMap);
		Assert.isTrue(3 == deserialized.getRelatedOptions().size());

	}

}
