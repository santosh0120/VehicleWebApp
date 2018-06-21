package com.vehicle.controllers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.vehicle.RestTestConfiguration;
import com.vehicle.service.VehicleService;
import com.vehicle.vo.VehicleVO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {RestTestConfiguration.class})
public class VehicleRestControllerTest {

	@Autowired
	VehicleRestController controller;
	
	@Autowired
	VehicleService service;
	
	VehicleVO vo;
	
	@Before
	public void setUp() throws Exception {
		vo = new VehicleVO();
		vo.setName("Honda City");
		vo.setType("car");
		
		Mockito.when(service.getVehiclesByName(vo.getName()))
			.thenReturn(Arrays.asList(new VehicleVO[] { vo }));
		
	}

	@Test
	public void testAddVehicle() {
		ResponseEntity<String> response = controller.addVehicle(vo);
		assertEquals(HttpStatus.OK, response.getStatusCode().OK);
	}

	@Test
	public void testUpdateVehicle() {
		ResponseEntity<String> response = controller.updateVehicle(vo);
		assertEquals(HttpStatus.OK, response.getStatusCode().OK);
	}

	@Test
	public void testDeleteVehicle() {
		ResponseEntity<String> response = controller.deleteVehicle(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode().OK);
	}

	@Test
	public void testGetVehiclesByName() {
		ResponseEntity<List<VehicleVO>> response = controller.getVehiclesByName(vo.getName());
		assertEquals(HttpStatus.OK, response.getStatusCode().OK);
		List<VehicleVO> foundVehicles = response.getBody();
		foundVehicles.forEach(foundVehicle -> {
			assertEquals(vo.getName(), foundVehicle.getName());
		});
		
	}

}
