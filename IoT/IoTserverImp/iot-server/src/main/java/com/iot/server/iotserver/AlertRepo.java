package com.iot.server.iotserver;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepo extends JpaRepository<Alert, Long> {
    
}
