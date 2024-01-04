package com.simpleform0.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users_details")
public class UserssModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	@Column(nullable=false,unique=true)
	Integer personalid;
	String username;
	String password;
	String access;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPersonalid() {
		return personalid;
	}
	public void setPersonalid(Integer personalid) {
		this.personalid = personalid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	@Override
	public int hashCode() {
		return Objects.hash(access, id, password, personalid, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserssModel other = (UserssModel) obj;
		return Objects.equals(access, other.access) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password) && Objects.equals(personalid, other.personalid)
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "UserssModel [id=" + id + ", personalid=" + personalid + ", username=" + username + ", password="
				+ password + ", access=" + access + "]";
	}
	
}
