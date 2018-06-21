package com.vehicle;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vehicle.dao.VehicleDao;
import com.vehicle.service.VehicleService;
import com.vehicle.service.VehicleServiceImpl;

@Configuration
public class TestConfiguration {


	@Bean
	public VehicleService vehicleService() {
		return new VehicleServiceImpl();
	}

	@Bean
	public VehicleDao vehicleDao() {
		return Mockito.mock(VehicleDao.class);
	}
}
