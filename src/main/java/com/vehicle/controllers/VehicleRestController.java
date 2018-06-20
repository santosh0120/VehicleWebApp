package com.vehicle.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.service.VehicleService;
import com.vehicle.vo.VehicleVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/vehicle") 
public class VehicleRestController { 
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleRestController.class);
	
	@Autowired
	private VehicleService service;

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> addVehicle(VehicleVO vehicle) {
		LOGGER.info("Received request for addVehicle {}", vehicle);
		service.addVehicle(vehicle);
		LOGGER.info("Successfully added vehicle");
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(path = "/update", method = RequestMethod.PUT)
	public ResponseEntity<String> updateVehicle(VehicleVO vehicle) {
		LOGGER.info("Received request for updateVehicle {}", vehicle);
		service.updateVehicle(vehicle);
		LOGGER.info("Successfully updated vehicle");
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteVehicle(Long id) {
		LOGGER.info("Received request for deleteVehicle of id {}", id);
		service.deleteVehicle(id);
		LOGGER.info("Successfully deleted vehicle with id {}", id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(path = "/getVehiclesByName", method = RequestMethod.GET)
	public ResponseEntity<List<VehicleVO>> getVehiclesByName(String name) {
		LOGGER.info("Received request for getVehiclesByName for {}", name);
		List<VehicleVO> foundVehicles = service.getVehiclesByName(name);
		LOGGER.info("Found vehicles for {} {}", name, foundVehicles);
		return new ResponseEntity<List<VehicleVO>>(foundVehicles, HttpStatus.OK);
	}
}
