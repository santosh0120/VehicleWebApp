package com.vehicle.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.vehicle.domain.Vehicle;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleDaoTest {
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	VehicleDao dao;
	
	Vehicle sampleVehicle = new Vehicle();
	
	
	@Before
	public void setUp() throws Exception {
		sampleVehicle.setName("Honda City");
		sampleVehicle.setType("car");
		
		entityManager.persist(sampleVehicle);
		entityManager.flush();
	}

	@Test
	public void testFindByName() {
		List<Vehicle> foundVehicles = dao.findByName(sampleVehicle.getName());// Searching using the sample vehicle
		assertFalse(foundVehicles.isEmpty());
		foundVehicles.forEach((vehicle) -> {
			assertEquals(sampleVehicle.getName(), vehicle.getName());
		});
	}
	
	@Test
	public void testFindByNameNotPresent() {
		List<Vehicle> foundVehicles = dao.findByName("abc");
		assertTrue(foundVehicles.isEmpty());
	}

}
