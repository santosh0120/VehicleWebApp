package com.vehicle.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle.dao.VehicleDao;
import com.vehicle.domain.Vehicle;
import com.vehicle.vo.VehicleVO;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao dao;
	
	@Override
	public void addVehicle(VehicleVO vo) {
		Vehicle vehicle = transform(vo);
		if(vo == null) 
			throw new RuntimeException("Vehicle could not be transformed");
		dao.save(vehicle);
	}

	@Override
	public void deleteVehicle(Long id) {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(id);
		dao.delete(vehicle);
	}

	@Override
	public void updateVehicle(VehicleVO vehicle) {
		Optional<Vehicle> savedVehicle = dao.findById(vehicle.getId());
		if(savedVehicle.isPresent()) {
			Vehicle existingVehicle = savedVehicle.get();
			existingVehicle.setName(vehicle.getName());
			existingVehicle.setType(vehicle.getType());
			dao.save(existingVehicle);
		}
	}

	@Override
	public List<VehicleVO> getVehiclesByName(String name) {
		final List<VehicleVO> foundVehicles = new ArrayList<>();
		dao.findByName(name).forEach(savedVehicle -> {
			foundVehicles.add(transform(savedVehicle));
		});
		
		return foundVehicles;
	}

	private Vehicle transform(VehicleVO vo) {
		Vehicle vehicle = null;
		if(vo != null) {
			vehicle = new Vehicle();
			vehicle.setName(vo.getName());
			vehicle.setType(vo.getType());
		}
		
		return vehicle;
	}
	
	private VehicleVO transform(Vehicle vehicle) {
		VehicleVO vo = null;
		if(vehicle != null) {
			vo = new VehicleVO();
			vo.setId(vehicle.getId());
			vo.setName(vehicle.getName());
			vo.setType(vehicle.getType());
		}
		
		return vo;
	}
}
