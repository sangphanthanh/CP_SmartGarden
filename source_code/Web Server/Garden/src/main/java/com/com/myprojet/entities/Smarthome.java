package com.myprojet.entities;

// Generated Jun 10, 2016 10:34:30 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Smarthome generated by hbm2java
 */
@Entity
@Table(name = "smarthome", catalog = "travelguild")
public class Smarthome implements java.io.Serializable {

	private Integer id;
	private String pin;
	private Integer status;
	private String note;
	private String room;

	public Smarthome() {
	}

	public Smarthome(String pin, Integer status, String note, String room) {
		this.pin = pin;
		this.status = status;
		this.note = note;
		this.room = room;
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

	@Column(name = "pin")
	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "room")
	public String getRoom() {
		return this.room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

}