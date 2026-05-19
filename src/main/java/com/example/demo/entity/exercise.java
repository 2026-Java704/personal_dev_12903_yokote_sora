package com.example.demo.entity;

import javax.xml.crypto.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercise_records")
public class exercise {
	private Integer id;
	private Integer user_id;
	private double burn_calorie;
	private Integer event_id;
	private Integer time;
	private Data date;
	private Integer progress;
	private String memo;

	public exercise() {

	}

	public exercise(Integer user_id, double burn_calorie, Integer event_id, Integer time, Data date, Integer progress,
			String memo) {
		this.user_id = user_id;
		this.burn_calorie = burn_calorie;
		this.time = time;
		this.date = date;
		this.progress = progress;
		this.memo = memo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public double getBurn_calorie() {
		return burn_calorie;
	}

	public void setBurn_calorie(double burn_calorie) {
		this.burn_calorie = burn_calorie;
	}

	public Integer getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Data getDate() {
		return date;
	}

	public void setDate(Data date) {
		this.date = date;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
