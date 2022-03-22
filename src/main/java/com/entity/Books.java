package com.entity;



import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Books {
	
	@Id
	private  String id;
	private  String name;
	private  String author;
	
	@GeneratedValue
	private LocalDate date =  LocalDate.now();
	
	public LocalDate getDate() {
		return date;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public  String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Books(String id, String name,String author) {
		super();
		this.id = id;
		this.name = name;
	
		this.author = author;
	}
	
}
