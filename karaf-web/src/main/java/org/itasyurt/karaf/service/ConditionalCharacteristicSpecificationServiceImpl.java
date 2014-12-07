package org.itasyurt.karaf.service;

import java.util.List;

import javax.ws.rs.Path;

import org.itasyurt.karaf.dao.characteristic.specification.conditional.ConditionalCharacteristicSpecificationDao;
import org.itasyurt.karaf.domain.characteristic.specification.conditional.ConditionalCharacteristicSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("conditionalCharSpecService")
@Path("/cs/conditional")
public class ConditionalCharacteristicSpecificationServiceImpl implements CharacteristicSpecificationService<ConditionalCharacteristicSpecification> {

	@Autowired
	ConditionalCharacteristicSpecificationDao dao;

	@Override
	public void create(ConditionalCharacteristicSpecification acs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ConditionalCharacteristicSpecification acs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String code) {
		// TODO Auto-generated method stub

	}

	@Override
	public ConditionalCharacteristicSpecification find(String code) {

		return dao.findByCode(code);
	}

	@Override
	public List<? extends ConditionalCharacteristicSpecification> getAll() {

		return dao.getAll();
	}

}
