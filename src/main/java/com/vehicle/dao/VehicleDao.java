package com.vehicle.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vehicle.domain.Vehicle;

@Repository
public interface VehicleDao extends CrudRepository<Vehicle, Long> {
	public List<Vehicle> findByName(String name);
}
