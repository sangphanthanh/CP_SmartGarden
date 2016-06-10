package com.myprojet.entities;

// Generated Jun 10, 2016 10:34:30 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Enddevice generated by hbm2java
 */
@Entity
@Table(name = "enddevice", catalog = "travelguild")
public class Enddevice implements java.io.Serializable {

	private Integer id;
	private int zoneId;
	private boolean statuspump;
	private boolean statuscovered;
	private boolean statusmisting;

	public Enddevice() {
	}

	public Enddevice(int zoneId, boolean statuspump, boolean statuscovered,
			boolean statusmisting) {
		this.zoneId = zoneId;
		this.statuspump = statuspump;
		this.statuscovered = statuscovered;
		this.statusmisting = statusmisting;
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

	@Column(name = "zoneID", nullable = false)
	public int getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	@Column(name = "statuspump", nullable = false)
	public boolean isStatuspump() {
		return this.statuspump;
	}

	public void setStatuspump(boolean statuspump) {
		this.statuspump = statuspump;
	}

	@Column(name = "statuscovered", nullable = false)
	public boolean isStatuscovered() {
		return this.statuscovered;
	}

	public void setStatuscovered(boolean statuscovered) {
		this.statuscovered = statuscovered;
	}

	@Column(name = "statusmisting", nullable = false)
	public boolean isStatusmisting() {
		return this.statusmisting;
	}

	public void setStatusmisting(boolean statusmisting) {
		this.statusmisting = statusmisting;
	}

}
