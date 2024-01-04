package com.simpleform0.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reviews")
public class Reviews {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer rino;
	@Column(nullable=false)
	String name;
	@Column(nullable=false,unique=true)
	String emailid;
	Double star;
	String message;
	public Integer getRino() {
		return rino;
	}
	public void setRino(Integer rino) {
		this.rino = rino;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public Double getStar() {
		return star;
	}
	public void setStar(Double star) {
		this.star = star;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int hashCode() {
		return Objects.hash(emailid, message, name, rino, star);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reviews other = (Reviews) obj;
		return Objects.equals(emailid, other.emailid) && Objects.equals(message, other.message)
				&& Objects.equals(name, other.name) && Objects.equals(rino, other.rino)
				&& Objects.equals(star, other.star);
	}
	@Override
	public String toString() {
		return "Reviews [rino=" + rino + ", name=" + name + ", emailid=" + emailid + ", star=" + star + ", message="
				+ message + "]";
	}
	
}
