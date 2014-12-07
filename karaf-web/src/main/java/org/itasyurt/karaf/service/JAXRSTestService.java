package org.itasyurt.karaf.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

@Service("testService")
@Path("/test")
@Produces("application/json")
public class JAXRSTestService {

	@GET
	public String echo() {
		return "echo";
	}
}
