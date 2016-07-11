package com.garden.entity;

// Generated Jun 10, 2016 12:51:31 AM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Sensorinfo generated by hbm2java
 */
@Entity
@Table(name = "sensorinfo", catalog = "travelguild")
public class Sensorinfo implements java.io.Serializable {

	private Integer id;
	private Integer zoneId;
	private Float ph;
	private Float light;
	private Float humidity;
	private Float temperature;
	private Float humidityofsoil;
	private Date sincetime;

	public Sensorinfo() {
	}

	public Sensorinfo(Integer zoneId, Float ph, Float light, Float humidity,
			Float temperature, Float humidityofsoil, Date sincetime) {
		this.zoneId = zoneId;
		this.ph = ph;
		this.light = light;
		this.humidity = humidity;
		this.temperature = temperature;
		this.humidityofsoil = humidityofsoil;
		this.sincetime = sincetime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "zoneID")
	public Integer getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	@Column(name = "ph", precision = 12, scale = 0)
	public Float getPh() {
		return this.ph;
	}

	public void setPh(Float ph) {
		this.ph = ph;
	}

	@Column(name = "light", precision = 12, scale = 0)
	public Float getLight() {
		return this.light;
	}

	public void setLight(Float light) {
		this.light = light;
	}

	@Column(name = "humidity", precision = 12, scale = 0)
	public Float getHumidity() {
		return this.humidity;
	}

	public void setHumidity(Float humidity) {
		this.humidity = humidity;
	}

	@Column(name = "temperature", precision = 12, scale = 0)
	public Float getTemperature() {
		return this.temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	@Column(name = "humidityofsoil", precision = 12, scale = 0)
	public Float getHumidityofsoil() {
		return this.humidityofsoil;
	}

	public void setHumidityofsoil(Float humidityofsoil) {
		this.humidityofsoil = humidityofsoil;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "sincetime", length = 10)
	public Date getSincetime() {
		return this.sincetime;
	}

	public void setSincetime(Date sincetime) {
		this.sincetime = sincetime;
	}

}