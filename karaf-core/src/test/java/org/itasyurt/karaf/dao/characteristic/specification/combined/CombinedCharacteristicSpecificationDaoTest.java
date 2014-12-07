package org.itasyurt.karaf.dao.characteristic.specification.combined;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.itasyurt.karaf.dao.characteristic.specification.atomic.SelectionCharacteristicSpecificationDao;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SelectionCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.combined.CombinedCharacteristicSpecification;
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
public class CombinedCharacteristicSpecificationDaoTest {

	@Autowired
	CombinedCharacteristicSpecificationDao dao;

	@Autowired
	SelectionCharacteristicSpecificationDao scsDao;

	@Test
	@Transactional
	public void savedAndFoundSuccessfully() {
		SelectionCharacteristicSpecification illerCharacteristic = DataCreationUtils.createSCS("iller", "ankara", "istanbul", "izmir");

		illerCharacteristic = scsDao.save(illerCharacteristic);

		SelectionCharacteristicSpecification ilcelerCharacteristic = DataCreationUtils.createSCS("ilceler", "cankaya", "besiktas", "konak", "buca");

		ilcelerCharacteristic = scsDao.save(ilcelerCharacteristic);

		Map<Integer, List<Integer>> relatedOptionsMap = new HashMap<Integer, List<Integer>>();
		relatedOptionsMap.put(0, Arrays.asList(0));
		relatedOptionsMap.put(1, Arrays.asList(1));
		relatedOptionsMap.put(2, Arrays.asList(2, 3));

		CombinedCharacteristicSpecification ccs = DataCreationUtils.createCombinedCS("il_ilce", illerCharacteristic, ilcelerCharacteristic, relatedOptionsMap);

		ccs=dao.save(ccs);

		CombinedCharacteristicSpecification retrieved = dao.find(ccs.getId());

		Assert.notNull(retrieved);
	}

	@Test
	@Transactional
	public void savedAndListedSuccessfully() {

		List<CombinedCharacteristicSpecification> l1 = dao.getAll();
		SelectionCharacteristicSpecification illerCharacteristic = DataCreationUtils.createSCS("iller", "ankara", "istanbul", "izmir");

		illerCharacteristic = scsDao.save(illerCharacteristic);

		SelectionCharacteristicSpecification ilcelerCharacteristic = DataCreationUtils.createSCS("ilceler", "cankaya", "besiktas", "konak", "buca");

		ilcelerCharacteristic = scsDao.save(ilcelerCharacteristic);

		Map<Integer, List<Integer>> relatedOptionsMap = new HashMap<Integer, List<Integer>>();
		relatedOptionsMap.put(0, Arrays.asList(0));
		relatedOptionsMap.put(1, Arrays.asList(1));
		relatedOptionsMap.put(2, Arrays.asList(2, 3));

		CombinedCharacteristicSpecification ccs = DataCreationUtils.createCombinedCS("il_ilce", illerCharacteristic, ilcelerCharacteristic, relatedOptionsMap);
		CombinedCharacteristicSpecification ccs2 = DataCreationUtils.createCombinedCS("il_ilce2", illerCharacteristic, ilcelerCharacteristic, relatedOptionsMap);

		dao.save(ccs);
		dao.save(ccs2);

		List<CombinedCharacteristicSpecification> l2 = dao.getAll();

		Assert.isTrue(2 == (l2.size() - l1.size()));

	}

}
