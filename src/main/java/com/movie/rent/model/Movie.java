package com.movie.rent.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection="movie")
public class Movie {

	@Id
	private String id;
	private String name;
	private Date releaseDate;
	private String actor;
	private String actress;
	private String genres;
	private double price;
	
	public Movie(){
		
	}
	
	public Movie(String name, Date releaseDate, String actor, String actress, String genres, double price) {
		super();
		this.name = name;
		this.releaseDate = releaseDate;
		this.actor = actor;
		this.actress = actress;
		this.genres = genres;
		this.price = price;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getActress() {
		return actress;
	}
	public void setActress(String actress) {
		this.actress = actress;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Movie){
			return this.id.equals(((Movie) obj).getId());
		}
		return false;
	}
}
