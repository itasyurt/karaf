package org.itasyurt.karaf.domain;

import org.itasyurt.karaf.domain.characteristic.specification.atomic.AtomicCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.BooleanCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.TextCharacteristicSpecification;
import org.junit.Assert;
import org.junit.Test;

public class EqualsAndHashCodeTest {

	@Test
	public void equalsWorks() {
		AtomicCharacteristicSpecification acs1 = new BooleanCharacteristicSpecification();
		acs1.setCode("c1");

		AtomicCharacteristicSpecification acs2 = new BooleanCharacteristicSpecification();
		acs2.setCode("c1");
		Assert.assertTrue(acs1.equals(acs2));
		Assert.assertTrue(acs2.equals(acs1));

	}

	@Test
	public void notEqualsWorks() {
		AtomicCharacteristicSpecification acs1 = new BooleanCharacteristicSpecification();
		AtomicCharacteristicSpecification acs2 = new BooleanCharacteristicSpecification();
		acs1.setCode("c1");
		acs2.setCode("c2");
		Assert.assertFalse(acs1.equals(acs2));
		Assert.assertFalse(acs2.equals(acs1));

		AtomicCharacteristicSpecification acs3 = new TextCharacteristicSpecification();
		acs3.setCode("c1");
		Assert.assertFalse(acs3.equals(acs1));
		Assert.assertFalse(acs1.equals(acs3));

	}

	@Test
	public void hashCodeWorks() {

		AtomicCharacteristicSpecification acs1 = new BooleanCharacteristicSpecification();
		AtomicCharacteristicSpecification acs2 = new BooleanCharacteristicSpecification();
		AtomicCharacteristicSpecification acs3 = new BooleanCharacteristicSpecification();
		acs1.setCode("c1");
		acs2.setCode("c2");
		acs3.setCode("c1");
		Assert.assertTrue(acs1.hashCode() == acs3.hashCode());
		Assert.assertFalse(acs1.hashCode() == acs2.hashCode());

	}

}
