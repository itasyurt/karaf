package org.itasyurt.karaf.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.itasyurt.karaf.domain.characteristic.specification.atomic.AtomicCharacteristicSpecification;
import org.itasyurt.karaf.domain.characteristic.specification.atomic.CharacteristicSpecification;

@Produces("application/json")
public interface CharacteristicSpecificationService<T extends CharacteristicSpecification> {

	public void create(T acs);

	public void update(T acs);

	public void delete(String code);

	@GET
	@Path("find/{code}")
	public T find(@PathParam("code") String code);

	
	@GET
	@Path("list")
	public List<? extends T> getAll();

}
