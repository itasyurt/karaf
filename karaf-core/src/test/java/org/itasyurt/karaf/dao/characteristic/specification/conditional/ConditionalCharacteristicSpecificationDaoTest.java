package org.itasyurt.karaf.dao.characteristic.specification.conditional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.itasyurt.karaf.dao.characteristic.specification.atomic.AtomicCharacteristicSpecificationDao;
import org.itasyurt.karaf.dao.characteristic.specification.atomic.SelectionCharacteristicSpecificationDao;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.AtomicCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.BooleanCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SelectionCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.domain.characteristic.specification.combined.CombinedCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.ConditionalCharacteristicSpecification;
import org.itasyurt.karaf.util.DataCreationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
@TransactionConfiguration(defaultRollback = true)
public class ConditionalCharacteristicSpecificationDaoTest {

	@Autowired
	ConditionalCharacteristicSpecificationDao dao;

	@Autowired
	SelectionCharacteristicSpecificationDao scsDao;

	@Autowired
	AtomicCharacteristicSpecificationDao acsDao;

	@Test
	@Transactional
	public void savedAndFoundSuccessfully() {
		SelectionCharacteristicSpecification illerCharacteristic = DataCreationUtils.createSCS("iller", "ankara", "istanbul", "izmir");

		illerCharacteristic = scsDao.save(illerCharacteristic);

		AtomicCharacteristicSpecification boyoz = new BooleanCharacteristicSpecification();
		boyoz.setCode("boyoz");
		boyoz.setName("boyoz");
		acsDao.save(boyoz);

		AtomicCharacteristicSpecification gevrek = new BooleanCharacteristicSpecification();
		gevrek.setCode("gevrek");
		gevrek.setName("gevrek");
		acsDao.save(gevrek);

		AtomicCharacteristicSpecification doner = new BooleanCharacteristicSpecification();
		doner.setCode("doner");
		doner.setName("doner");
		acsDao.save(doner);

		AtomicCharacteristicSpecification islakHamburger = new BooleanCharacteristicSpecification();
		islakHamburger.setCode("islakHamburger");
		islakHamburger.setName("islakHamburger");
		acsDao.save(islakHamburger);

		AtomicCharacteristicSpecification dilliKasarli = new BooleanCharacteristicSpecification();
		dilliKasarli.setCode("dilliKasarli");
		acsDao.save(dilliKasarli);

		Map<SpecOption, List<AtomicCharacteristicSpecification>> relatedCharacteristicsMap = new HashMap<SpecOption, List<AtomicCharacteristicSpecification>>();
		relatedCharacteristicsMap.put(illerCharacteristic.getOptions().get(0), Arrays.asList(doner));
		relatedCharacteristicsMap.put(illerCharacteristic.getOptions().get(1), Arrays.asList(doner, islakHamburger, dilliKasarli));
		relatedCharacteristicsMap.put(illerCharacteristic.getOptions().get(2), Arrays.asList(gevrek, boyoz));

		ConditionalCharacteristicSpecification ccs = DataCreationUtils.createConditionalCS("il_yemek", illerCharacteristic, relatedCharacteristicsMap);

		ccs = dao.save(ccs);

		ConditionalCharacteristicSpecification retrieved = dao.find(ccs.getId());

		Assert.notNull(retrieved);
	}

	@Test
	@Transactional
	public void savedAndListedSuccessfully() {

		List<ConditionalCharacteristicSpecification> l1 = dao.getAll();

		SelectionCharacteristicSpecification illerCharacteristic = DataCreationUtils.createSCS("iller", "ankara", "istanbul", "izmir");
		illerCharacteristic = scsDao.save(illerCharacteristic);

		AtomicCharacteristicSpecification boyoz = new BooleanCharacteristicSpecification();
		boyoz.setCode("boyoz");
		boyoz.setName("boyoz");
		acsDao.save(boyoz);

		AtomicCharacteristicSpecification gevrek = new BooleanCharacteristicSpecification();
		gevrek.setCode("gevrek");
		gevrek.setName("gevrek");
		acsDao.save(gevrek);

		AtomicCharacteristicSpecification doner = new BooleanCharacteristicSpecification();
		doner.setCode("doner");
		doner.setName("doner");
		acsDao.save(doner);

		AtomicCharacteristicSpecification islakHamburger = new BooleanCharacteristicSpecification();
		islakHamburger.setCode("islakHamburger");
		islakHamburger.setName("islakHamburger");
		acsDao.save(islakHamburger);

		AtomicCharacteristicSpecification dilliKasarli = new BooleanCharacteristicSpecification();
		dilliKasarli.setCode("dilliKasarli");
		acsDao.save(dilliKasarli);

		Map<SpecOption, List<AtomicCharacteristicSpecification>> relatedCharacteristicsMap = new HashMap<SpecOption, List<AtomicCharacteristicSpecification>>();
		relatedCharacteristicsMap.put(illerCharacteristic.getOptions().get(0), Arrays.asList(doner));
		relatedCharacteristicsMap.put(illerCharacteristic.getOptions().get(1), Arrays.asList(doner, islakHamburger, dilliKasarli));
		relatedCharacteristicsMap.put(illerCharacteristic.getOptions().get(2), Arrays.asList(gevrek, boyoz));

		ConditionalCharacteristicSpecification ccs = DataCreationUtils.createConditionalCS("il_yemek", illerCharacteristic, relatedCharacteristicsMap);
		ConditionalCharacteristicSpecification ccs2 = DataCreationUtils.createConditionalCS("il_yemek2", illerCharacteristic, relatedCharacteristicsMap);

		ccs = dao.save(ccs);
		ccs = dao.save(ccs2);

		List<ConditionalCharacteristicSpecification> l2 = dao.getAll();

		Assert.isTrue(2 == (l2.size() - l1.size()));

	}

}
