package com.vehicle.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.vehicle.TestConfiguration;
import com.vehicle.dao.VehicleDao;
import com.vehicle.domain.Vehicle;
import com.vehicle.vo.VehicleVO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={TestConfiguration.class})
public class VehicleServiceImplTest {

	@Autowired
	VehicleService service;
	
	@Autowired
	VehicleDao dao;
	Vehicle sampleVehicle = new Vehicle();

	VehicleVO vo = new VehicleVO();
	
	@Before
	public void setUp() throws Exception {
		sampleVehicle.setName("Honda City");
		sampleVehicle.setType("car");

		vo.setName(sampleVehicle.getName());
		vo.setType(sampleVehicle.getType());
		Mockito.when(dao.findByName(sampleVehicle.getName()))
				.thenReturn(Arrays.asList(new Vehicle[] { sampleVehicle }));
	}

	@Test
	public void testAddVehicle() {
		service.addVehicle(vo);// No exception thrown, then its success.
	}
	
	@Test(expected=RuntimeException.class)
	public void testAddVehicleWhenNull() {
		service.addVehicle(null);
	}

	@Test
	public void testGetVehiclesByName() {
		List<VehicleVO> foundVehicles = service.getVehiclesByName(sampleVehicle.getName());
		assertFalse(foundVehicles.isEmpty());
		foundVehicles.forEach(vehicle -> {
			assertEquals(sampleVehicle.getName(), vehicle.getName());
		});
	}
}
