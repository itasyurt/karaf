package org.itasyurt.karaf.dao.characteristic.specification.atomic;

import java.util.List;

import org.itasyurt.karaf.domain.characteristic.specification.atomic.AtomicCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.BooleanCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SelectionCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.domain.text.Text;
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
public class AtomicCharacteristicSpecificationDaoTest {

	@Autowired
	AtomicCharacteristicSpecificationDao dao;

	@Test
	@Transactional
	public void savedAndFoundSuccessfully() {
		SelectionCharacteristicSpecification scs = createSCS();
		scs = (SelectionCharacteristicSpecification) dao.save(scs);

		AtomicCharacteristicSpecification retrieved = dao.find(scs.getId());
		Assert.notNull(retrieved);

	}

	@Test
	@Transactional
	public void savedAndListedSuccessfully() {
		List<AtomicCharacteristicSpecification> l0 = dao.getAll();
		AtomicCharacteristicSpecification bcs = createBCS("bcs");
		bcs = dao.save(bcs);

		AtomicCharacteristicSpecification scs = createSCS();
		scs = dao.save(scs);

		List<AtomicCharacteristicSpecification> l1 = dao.getAll();
		Assert.isTrue(2 == (l1.size() - l0.size()));

	}

	private BooleanCharacteristicSpecification createBCS(String code) {
		BooleanCharacteristicSpecification result= new BooleanCharacteristicSpecification();
		result.setCode(code);
		result.setName(code);
		
		return result;
	}

	private SelectionCharacteristicSpecification createSCS() {
		SelectionCharacteristicSpecification scs = new SelectionCharacteristicSpecification();
		scs.setCode("code");
		Text name = new Text();
		name.setText("name");
		scs.setName(name);

		SpecOption so1 = new SpecOption();
		so1.setCode("so1");
		Text so1Name = new Text();
		so1Name.setText("so1Name");
		so1.setName(so1Name);
		scs.getOptions().add(so1);
		return scs;
	}
}
