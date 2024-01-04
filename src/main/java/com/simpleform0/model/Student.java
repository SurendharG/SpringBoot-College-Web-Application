package com.simpleform0.model;


import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student_details")
public class Student {

	@Id
	@Column(nullable=false,unique=true)
	Long regno;
	String name;
	Date dob;
	String gender;
	String course;
	@Column(precision = 10, scale = 2)
	BigDecimal total_fees;
	@Column(precision = 10, scale = 2)
	BigDecimal paid_fees;
	@Column(precision = 10, scale = 2)
	BigDecimal pending_fees;
	Long contact_no;
	String address;
	@Column(nullable=false)
	String password;
	public Long getRegno() {
		return regno;
	}
	public void setRegno(Long regno) {
		this.regno = regno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public BigDecimal getTotal_fees() {
		return total_fees;
	}
	public void setTotal_fees(BigDecimal total_fees) {
		this.total_fees = total_fees;
	}
	public BigDecimal getPaid_fees() {
		return paid_fees;
	}
	public void setPaid_fees(BigDecimal paid_fees) {
		this.paid_fees = paid_fees;
	}
	public BigDecimal getPending_fees() {
		return pending_fees;
	}
	public void setPending_fees(BigDecimal pending_fees) {
		this.pending_fees = pending_fees;
	}
	public Long getContact_no() {
		return contact_no;
	}
	public void setContact_no(Long contact_no) {
		this.contact_no = contact_no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, contact_no, course, dob, gender, name, paid_fees, password, pending_fees, regno,
				total_fees);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(address, other.address) && Objects.equals(contact_no, other.contact_no)
				&& Objects.equals(course, other.course) && Objects.equals(dob, other.dob)
				&& Objects.equals(gender, other.gender) && Objects.equals(name, other.name)
				&& Objects.equals(paid_fees, other.paid_fees) && Objects.equals(password, other.password)
				&& Objects.equals(pending_fees, other.pending_fees) && Objects.equals(regno, other.regno)
				&& Objects.equals(total_fees, other.total_fees);
	}
	@Override
	public String toString() {
		return "Student [regno=" + regno + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", course="
				+ course + ", total_fees=" + total_fees + ", paid_fees=" + paid_fees + ", pending_fees=" + pending_fees
				+ ", contact_no=" + contact_no + ", address=" + address + ", password=" + password + "]";
	}
}
