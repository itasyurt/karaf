package org.itasyurt.karaf.service;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.itasyurt.karaf.dao.characteristic.specification.atomic.AtomicCharacteristicSpecificationDao;
import org.itasyurt.karaf.dao.characteristic.specification.atomic.SelectionCharacteristicSpecificationDao;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.AtomicCharacteristicSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("acsService")
@Path("/cs/atomic")
@Produces("application/json")
public class AtomicCharacteristicSpecificationServiceImpl implements CharacteristicSpecificationService<AtomicCharacteristicSpecification> {

	@Autowired
	AtomicCharacteristicSpecificationDao acsDao;

	@Autowired
	SelectionCharacteristicSpecificationDao scsDao;

	@Override
	public void create(AtomicCharacteristicSpecification acs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AtomicCharacteristicSpecification acs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String code) {
		// TODO Auto-generated method stub

	}


	@Override
	public AtomicCharacteristicSpecification find(String code) {

		AtomicCharacteristicSpecification result = scsDao.findByCode(code);
		if (result == null) {
			result = acsDao.findByCode(code);
		}
		return result;
	}

	
	public List<AtomicCharacteristicSpecification> getAll() {

		return acsDao.getAll();
	}

}
