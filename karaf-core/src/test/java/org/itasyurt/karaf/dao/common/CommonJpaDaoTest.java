package org.itasyurt.karaf.dao.common;

import java.util.List;

import org.itasyurt.karaf.domain.characteristic.specification.atomic.BooleanCharacteristicSpecification;
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
public class CommonJpaDaoTest {

	@Autowired
	CommonDao dao;

	@Test
	@Transactional
	public void entityCreatedSuccessfully() {

		BooleanCharacteristicSpecification bcs = new BooleanCharacteristicSpecification();

		bcs.setCode("c1");
		bcs = dao.save(bcs);
		Assert.notNull(bcs.getId());

	}

	@Test
	@Transactional
	public void entitySavedAndListedSuccessfully() {

		BooleanCharacteristicSpecification bcs = new BooleanCharacteristicSpecification();

		List<BooleanCharacteristicSpecification> l0 = dao.getAll(BooleanCharacteristicSpecification.class);
		bcs.setCode("c1");
		bcs = dao.save(bcs);

		BooleanCharacteristicSpecification bcs2 = new BooleanCharacteristicSpecification();
		bcs2.setCode("c2");

		dao.save(bcs2);

		List<BooleanCharacteristicSpecification> l1 = dao.getAll(BooleanCharacteristicSpecification.class);

		Assert.isTrue(2 == (l1.size() - l0.size()));
	}

	@Test
	@Transactional
	public void entitySavedAndFoundSuccessfully() {

		BooleanCharacteristicSpecification bcs = new BooleanCharacteristicSpecification();
		bcs.setCode("c1");
		bcs = dao.save(bcs);

		BooleanCharacteristicSpecification retrieved = dao.find(BooleanCharacteristicSpecification.class, bcs.getId());
		Assert.isTrue(bcs.equals(retrieved));
	}

	@Test
	@Transactional
	public void entitySavedUpdatedAndFoundSuccessfully() {

		BooleanCharacteristicSpecification bcs = new BooleanCharacteristicSpecification();
		bcs.setCode("c1");
		bcs = dao.save(bcs);

		BooleanCharacteristicSpecification retrieved = dao.find(BooleanCharacteristicSpecification.class, bcs.getId());
		Assert.isTrue(retrieved.getCode().equals("c1"));
		bcs.setCode("c2");
		dao.update(bcs);

		retrieved = dao.find(BooleanCharacteristicSpecification.class, bcs.getId());
		Assert.isTrue(retrieved.getCode().equals("c2"));
	}
	
	
	@Test
	@Transactional
	public void entitySavedAndDeletedSuccessfully() {

		BooleanCharacteristicSpecification bcs = new BooleanCharacteristicSpecification();
		bcs.setCode("c1");
		bcs = dao.save(bcs);
		

		BooleanCharacteristicSpecification retrieved = dao.find(BooleanCharacteristicSpecification.class, bcs.getId());
		Assert.notNull(retrieved);

		dao.delete(BooleanCharacteristicSpecification.class, bcs.getId());

		retrieved = dao.find(BooleanCharacteristicSpecification.class, bcs.getId());
		Assert.isNull(retrieved);
	}
}
