package com.iot.server.iotserver;
/**
 * @author Ali Alzubaidi
 *
 * 
 * 
 */

import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepo extends JpaRepository<Sensor, Long> {
    
}
