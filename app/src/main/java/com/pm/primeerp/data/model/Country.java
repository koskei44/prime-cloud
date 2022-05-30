package com.pm.primeerp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.pm.primeerp.data.pojo.AuditModel;

import java.io.Serializable;


/**
 * @author kirwa
 *
 */
@Entity
public class Country  extends AuditModel {
	@PrimaryKey(autoGenerate = true)
	@NonNull
	private int country_id;
	private String name;
	private  String  code;
	private  String  nationality;


	public Country(int country_id, String name) {
		this.country_id = country_id;
		this.name = name;
	}

	@Ignore
	public Country() {
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}
