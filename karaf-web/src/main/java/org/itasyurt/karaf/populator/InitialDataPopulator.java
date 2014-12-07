package org.itasyurt.karaf.populator;

import java.util.ArrayList;
import java.util.List;

import org.itasyurt.karaf.dao.characteristic.specification.atomic.AtomicCharacteristicSpecificationDao;
import org.itasyurt.karaf.dao.characteristic.specification.atomic.SelectionCharacteristicSpecificationDao;
import org.itasyurt.karaf.dao.characteristic.specification.combined.CombinedCharacteristicSpecificationDao;
import org.itasyurt.karaf.dao.characteristic.specification.conditional.ConditionalCharacteristicSpecificationDao;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.AtomicCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.NumericCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SelectionCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.SpecOption;
import org.itasyurt.karaf.domain.characteristic.specification.combined.CombinedCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.combined.SpecOptionCombinedRelation;
import org.itasyurt.karaf.domain.characteristic.specification.combined.SpecOptionRelatedOptions;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.ConditionalCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.SpecOptionConditionalCharacteristic;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.SpecOptionConditionalRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialDataPopulator {

	@Autowired
	AtomicCharacteristicSpecificationDao acsDao;
	
	@Autowired
	SelectionCharacteristicSpecificationDao scsDao;
	
	@Autowired
	ConditionalCharacteristicSpecificationDao conditionalSpecDao;
	
	@Autowired
	CombinedCharacteristicSpecificationDao combinedSpecDao;
	
	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("populationContext.xml");

		InitialDataPopulator populator = ctx.getBean(InitialDataPopulator.class);
		populator.populate();

	}

	@Transactional
	public void populate() {

		populateAtomicCharacteristicSpecifications();
		populateConditionalCharacteristicSpecifications();
		populateCombinedCharacteristicSpecifications();

	}

	private void populateCombinedCharacteristicSpecifications() {
		CombinedCharacteristicSpecification ccs= new CombinedCharacteristicSpecification();
		ccs.setName("Duy Tipi-Renk");
		ccs.setCode("AMPULTIPI_RENK");
		SelectionCharacteristicSpecification primary = scsDao.findByCode("DUYTIPI");
		SelectionCharacteristicSpecification secondary = scsDao.findByCode("AMPULRENK");
		ccs.setPrimary(primary);
		ccs.setSecondary(secondary);
		SpecOption option=primary.getOptions().get(0);
		
		SpecOptionCombinedRelation value= new SpecOptionCombinedRelation();
		value.setPrimary(option);
		List<SpecOptionRelatedOptions>relatedOptions  = new ArrayList<SpecOptionRelatedOptions>();
		SpecOptionRelatedOptions relatedOption= new SpecOptionRelatedOptions();
		relatedOption.setOption(secondary.getOptions().get(0));
		relatedOptions.add(relatedOption);
		relatedOption= new SpecOptionRelatedOptions();
		relatedOption.setOption(secondary.getOptions().get(1));
		relatedOptions.add(relatedOption);
		relatedOption= new SpecOptionRelatedOptions();
		relatedOption.setOption(secondary.getOptions().get(2));
		relatedOptions.add(relatedOption);
		value.setRelatedOptions(relatedOptions);
		
		ccs.getRelatedOptions().put(option, value);
		
		option=primary.getOptions().get(1);
		value= new SpecOptionCombinedRelation();
		value.setPrimary(option);
		relatedOptions  = new ArrayList<SpecOptionRelatedOptions>();
		relatedOption= new SpecOptionRelatedOptions();
		relatedOption.setOption(secondary.getOptions().get(0));
		relatedOptions.add(relatedOption);
		relatedOption= new SpecOptionRelatedOptions();
		relatedOption.setOption(secondary.getOptions().get(1));
		relatedOptions.add(relatedOption);
		relatedOption= new SpecOptionRelatedOptions();
		relatedOption.setOption(secondary.getOptions().get(2));
		relatedOptions.add(relatedOption);
		value.setRelatedOptions(relatedOptions);
		
		ccs.getRelatedOptions().put(option, value);
		
		option=primary.getOptions().get(2);
		value= new SpecOptionCombinedRelation();
		value.setPrimary(option);
		relatedOptions  = new ArrayList<SpecOptionRelatedOptions>();
		relatedOption= new SpecOptionRelatedOptions();
		relatedOption.setOption(secondary.getOptions().get(0));
		relatedOptions.add(relatedOption);
		relatedOptions.add(relatedOption);
		value.setRelatedOptions(relatedOptions);
		ccs.getRelatedOptions().put(option, value);
		combinedSpecDao.save(ccs);
		
	}

	private void populateConditionalCharacteristicSpecifications() {
		ConditionalCharacteristicSpecification ccs= new ConditionalCharacteristicSpecification();
		ccs.setName("Ampul Tipi-Kosullu");
		ccs.setCode("AMPULTIPI_C1");
		SelectionCharacteristicSpecification primary = scsDao.findByCode("AMPULTIPI");
		ccs.setPrimary(primary);
		SpecOptionConditionalRelation relation= new SpecOptionConditionalRelation();
		SpecOption option = primary.getOptions().get(0);
		relation.setPrimary(option);
		SpecOptionConditionalCharacteristic conditionalCharacteristicRelation= new SpecOptionConditionalCharacteristic();
		conditionalCharacteristicRelation.setCharacteristic(acsDao.findByCode("KELVIN"));
		relation.getConditionalCharacteristics().add(conditionalCharacteristicRelation);
		ccs.getConditionalCharacteristics().put(option, relation);
		conditionalSpecDao.save(ccs);
		
	}

	private void populateAtomicCharacteristicSpecifications() {
		
		AtomicCharacteristicSpecification kelvin= new NumericCharacteristicSpecification();
		kelvin.setCode("KELVIN");
		kelvin.setName("Kelvin");
		acsDao.save(kelvin);
		
		AtomicCharacteristicSpecification lumen= new NumericCharacteristicSpecification();
		lumen.setCode("LM");
		lumen.setName("Lumen");
		acsDao.save(lumen);
		
		SelectionCharacteristicSpecification scs= new SelectionCharacteristicSpecification();
		scs.setCode("DUYTIPI");
		scs.setName("Duy Tipi");
		SpecOption option= new SpecOption();
		option.setCode("E27");
		option.setName("E27");
		scs.getOptions().add(option);
		option= new SpecOption();
		option.setCode("E14");
		option.setName("E14");
		scs.getOptions().add(option);
		option= new SpecOption();
		option.setCode("G9");
		option.setName("G9");
		scs.getOptions().add(option);
		acsDao.save(scs);
		
		scs= new SelectionCharacteristicSpecification();
		scs.setCode("AMPULTIPI");
		scs.setName("Ampul Tipi");
		option= new SpecOption();
		option.setCode("LED");
		option.setName("Led");
		scs.getOptions().add(option);
		option= new SpecOption();
		option.setCode("HAL");
		option.setName("Halojen");
		scs.getOptions().add(option);
		option= new SpecOption();
		option.setCode("ECO");
		option.setName("Ekonomik");
		scs.getOptions().add(option);
		acsDao.save(scs);
		
		scs= new SelectionCharacteristicSpecification();
		scs.setCode("AMPULRENK");
		scs.setName("Ampul Rengi");
		option= new SpecOption();
		option.setCode("WW");
		option.setName("Warm White");
		scs.getOptions().add(option);
		option= new SpecOption();
		option.setCode("W");
		option.setName("White");
		scs.getOptions().add(option);
		option= new SpecOption();
		option.setCode("B");
		option.setName("Blue");
		scs.getOptions().add(option);
		acsDao.save(scs);
		
	}

}
