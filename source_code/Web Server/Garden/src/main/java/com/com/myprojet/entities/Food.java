package com.myprojet.entities;

// Generated Jun 10, 2016 10:34:30 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Food generated by hbm2java
 */
@Entity
@Table(name = "food", catalog = "travelguild")
public class Food implements java.io.Serializable {

	private Integer foodId;
	private String foodName;
	private String foodDescription;
	private Integer provinceId;
	private String foodImgLink;
	private Integer foodVote;

	public Food() {
	}

	public Food(String foodName, String foodDescription, Integer provinceId,
			String foodImgLink, Integer foodVote) {
		this.foodName = foodName;
		this.foodDescription = foodDescription;
		this.provinceId = provinceId;
		this.foodImgLink = foodImgLink;
		this.foodVote = foodVote;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "foodId", unique = true, nullable = false)
	public Integer getFoodId() {
		return this.foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	@Column(name = "foodName")
	public String getFoodName() {
		return this.foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	@Column(name = "foodDescription")
	public String getFoodDescription() {
		return this.foodDescription;
	}

	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}

	@Column(name = "provinceId")
	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "foodImgLink")
	public String getFoodImgLink() {
		return this.foodImgLink;
	}

	public void setFoodImgLink(String foodImgLink) {
		this.foodImgLink = foodImgLink;
	}

	@Column(name = "foodVote")
	public Integer getFoodVote() {
		return this.foodVote;
	}

	public void setFoodVote(Integer foodVote) {
		this.foodVote = foodVote;
	}

}