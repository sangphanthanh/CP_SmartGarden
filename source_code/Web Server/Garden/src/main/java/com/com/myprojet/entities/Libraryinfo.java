package com.myprojet.entities;

// Generated Jun 10, 2016 10:34:30 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Libraryinfo generated by hbm2java
 */
@Entity
@Table(name = "libraryinfo", catalog = "travelguild")
public class Libraryinfo implements java.io.Serializable {

	private Integer libId;
	private String libPlanName;
	private String libTypePlan;
	private Float libHighPh;
	private Float libLowPh;
	private Float libHighLight;
	private Float libLowLight;
	private Float libHighTemperature;
	private Float libLowTemperature;
	private Float libHighSoilMoisture;
	private Float libLowSoilMoisture;

	public Libraryinfo() {
	}

	public Libraryinfo(String libPlanName, String libTypePlan) {
		this.libPlanName = libPlanName;
		this.libTypePlan = libTypePlan;
	}

	public Libraryinfo(String libPlanName, String libTypePlan, Float libHighPh,
			Float libLowPh, Float libHighLight, Float libLowLight,
			Float libHighTemperature, Float libLowTemperature,
			Float libHighSoilMoisture, Float libLowSoilMoisture) {
		this.libPlanName = libPlanName;
		this.libTypePlan = libTypePlan;
		this.libHighPh = libHighPh;
		this.libLowPh = libLowPh;
		this.libHighLight = libHighLight;
		this.libLowLight = libLowLight;
		this.libHighTemperature = libHighTemperature;
		this.libLowTemperature = libLowTemperature;
		this.libHighSoilMoisture = libHighSoilMoisture;
		this.libLowSoilMoisture = libLowSoilMoisture;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "libID", unique = true, nullable = false)
	public Integer getLibId() {
		return this.libId;
	}

	public void setLibId(Integer libId) {
		this.libId = libId;
	}

	@Column(name = "libPlanName", nullable = false)
	public String getLibPlanName() {
		return this.libPlanName;
	}

	public void setLibPlanName(String libPlanName) {
		this.libPlanName = libPlanName;
	}

	@Column(name = "libTypePlan", nullable = false)
	public String getLibTypePlan() {
		return this.libTypePlan;
	}

	public void setLibTypePlan(String libTypePlan) {
		this.libTypePlan = libTypePlan;
	}

	@Column(name = "libHighPH", precision = 12, scale = 0)
	public Float getLibHighPh() {
		return this.libHighPh;
	}

	public void setLibHighPh(Float libHighPh) {
		this.libHighPh = libHighPh;
	}

	@Column(name = "libLowPH", precision = 12, scale = 0)
	public Float getLibLowPh() {
		return this.libLowPh;
	}

	public void setLibLowPh(Float libLowPh) {
		this.libLowPh = libLowPh;
	}

	@Column(name = "libHighLight", precision = 12, scale = 0)
	public Float getLibHighLight() {
		return this.libHighLight;
	}

	public void setLibHighLight(Float libHighLight) {
		this.libHighLight = libHighLight;
	}

	@Column(name = "libLowLight", precision = 12, scale = 0)
	public Float getLibLowLight() {
		return this.libLowLight;
	}

	public void setLibLowLight(Float libLowLight) {
		this.libLowLight = libLowLight;
	}

	@Column(name = "libHighTemperature", precision = 12, scale = 0)
	public Float getLibHighTemperature() {
		return this.libHighTemperature;
	}

	public void setLibHighTemperature(Float libHighTemperature) {
		this.libHighTemperature = libHighTemperature;
	}

	@Column(name = "libLowTemperature", precision = 12, scale = 0)
	public Float getLibLowTemperature() {
		return this.libLowTemperature;
	}

	public void setLibLowTemperature(Float libLowTemperature) {
		this.libLowTemperature = libLowTemperature;
	}

	@Column(name = "libHighSoilMoisture", precision = 12, scale = 0)
	public Float getLibHighSoilMoisture() {
		return this.libHighSoilMoisture;
	}

	public void setLibHighSoilMoisture(Float libHighSoilMoisture) {
		this.libHighSoilMoisture = libHighSoilMoisture;
	}

	@Column(name = "libLowSoilMoisture", precision = 12, scale = 0)
	public Float getLibLowSoilMoisture() {
		return this.libLowSoilMoisture;
	}

	public void setLibLowSoilMoisture(Float libLowSoilMoisture) {
		this.libLowSoilMoisture = libLowSoilMoisture;
	}

}
