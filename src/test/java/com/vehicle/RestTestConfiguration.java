package com.vehicle;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vehicle.controllers.VehicleRestController;
import com.vehicle.dao.VehicleDao;
import com.vehicle.service.VehicleService;

@Configuration
public class RestTestConfiguration {

	@Bean
	public VehicleRestController controller() {
		return new VehicleRestController();
	}
	@Bean
	public VehicleService vehicleService() {
		return Mockito.mock(VehicleService.class);
	}
	
	@Bean
	public VehicleDao vehicleDao() {
		return Mockito.mock(VehicleDao.class);
	}
}
