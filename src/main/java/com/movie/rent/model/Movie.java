package com.movie.rent.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="movie")
public class Movie {

	@Id
	private String id;
	private String name;
	private Date releasedate;
	private String actor;
	private String actress;
	private String genres;
	private double price;
	
	public Movie(){
		
	}
	
	public Movie(String name, Date releasedate, String actor, String actress, String genres, double price) {
		super();
		this.name = name;
		this.releasedate = releasedate;
		this.actor = actor;
		this.actress = actress;
		this.genres = genres;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getReleaseDate() {
		return releasedate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releasedate = releaseDate;
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
	/*@Override
	public String toString() {
		return "Movie [name=" + name + ", releaseDate=" + releasedate + ", actor=" + actor + ", actress=" + actress
				+ ", genres=" + genres + "]";
	}*/
	
}
