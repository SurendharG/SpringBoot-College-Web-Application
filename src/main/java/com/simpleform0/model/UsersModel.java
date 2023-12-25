package com.simpleform0.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users_table")
public class UsersModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	String login;
	String email;
	String password;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, login, email,password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersModel other = (UsersModel) obj;
		return  Objects.equals(id, other.id) && Objects.equals(login, other.login)
				&& Objects.equals(email, other.email) &&Objects.equals(password, other.password);
	}
	@Override
	public String toString() {
		return "UsersModel [id=" + id + ", login=" + login + ", email=" + email + "]";
//		return "UsersModel [id=" + id + ", login=" + login + ", email=" + email + ",password=" + password + "]";
//		return "UsersModel [ login=" + login + ", email=" + email + ",password=" + password + "]";

	}
	
}
