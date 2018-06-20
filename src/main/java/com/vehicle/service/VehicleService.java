package com.vehicle.service;

import java.util.List;

import com.vehicle.vo.VehicleVO;

public interface VehicleService {

	public void addVehicle(VehicleVO vehicle);

	public void deleteVehicle(Long id);

	public void updateVehicle(VehicleVO vehicle);

	public List<VehicleVO> getVehiclesByName(String name);
}
