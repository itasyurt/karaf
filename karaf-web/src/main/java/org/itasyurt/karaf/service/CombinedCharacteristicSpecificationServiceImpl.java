package org.itasyurt.karaf.service;

import java.util.List;

import javax.ws.rs.Path;

import org.itasyurt.karaf.dao.characteristic.specification.combined.CombinedCharacteristicSpecificationDao;
import org.itasyurt.karaf.domain.characteristic.specification.combined.CombinedCharacteristicSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("combinedCharSpecService")
@Path("/cs/combined")
public class CombinedCharacteristicSpecificationServiceImpl implements CharacteristicSpecificationService<CombinedCharacteristicSpecification> {

	@Autowired
	CombinedCharacteristicSpecificationDao dao;
	
	@Override
	public void create(CombinedCharacteristicSpecification acs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CombinedCharacteristicSpecification acs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CombinedCharacteristicSpecification find(String code) {
	
		return dao.findByCode(code);
	}

	@Override
	public List<? extends CombinedCharacteristicSpecification> getAll() {
		
		return dao.getAll();
	}

}
