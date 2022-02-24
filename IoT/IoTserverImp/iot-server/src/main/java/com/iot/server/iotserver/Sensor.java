package com.iot.server.iotserver;
/**
 * @author Ali Alzubaidi
 *
 * 
 * 
 */

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.v3.oas.annotations.media.Schema;


@Entity
@Table(name = "sensors")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Sensor implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6609840729642210969L;


    @Schema(description = "Unique identifier of the sensor.", 
    example = "1", required = true)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    

    @Schema(description = "Sensor name", 
    example = "Fire detector", required = true)
    @NotBlank
    @Size(max = 100)
    private String name;

    @Schema(description = "Location of the sensor", 
    example = " 11 name Street PostCode", required = true)
    @NotBlank
    @Size(max = 100)
    private String location;


    @Schema(description = "Sensor creation data")
    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on")
    private Date timeStamp;

    public long getId() {
        return id;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private List <Alert> alerts;


    protected Sensor() {
        this.alerts = new ArrayList<Alert>();
        this.timeStamp = new Date();
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public @NotBlank Date getTimeStamp() {
        return timeStamp;
    }


    public List<Alert> getAlerts() {
        return this.alerts;
    }

    public Alert registerAlert() {
        Alert alert = new Alert();
        alert.alertStatus.put(Status.Initial.toString(), new Timestamp(System.currentTimeMillis()).toString());
        alert.setSensor(this);
        return alert;
    }

    public Alert updateAlert(int id, String status) {
        Alert alert = this.alerts.get(id);
        alert.getAlertStatus().put(status, new Timestamp(System.currentTimeMillis()).toString());
        return alert;
    }


    
}
