package com.simpleform0.model;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="admissionapplications")
public class Applications {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer aid;
	String name;
	String parent_name;
	Date dob;
	String gender;
	String course;;
	Long contact_no;
	String address;
	String emailid;
	String sslc_school_name;
	String sslc_mark;
	String hsc_school_name;
	String hsc_mark;
	String diplomo_college_name;
	String diplomo_mark;

	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
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
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getSslc_school_name() {
		return sslc_school_name;
	}
	public void setSslc_school_name(String sslc_school_name) {
		this.sslc_school_name = sslc_school_name;
	}
	public String getSslc_mark() {
		return sslc_mark;
	}
	public void setSslc_mark(String sslc_mark) {
		this.sslc_mark = sslc_mark;
	}
	public String getHsc_school_name() {
		return hsc_school_name;
	}
	public void setHsc_school_name(String hsc_school_name) {
		this.hsc_school_name = hsc_school_name;
	}
	public String getHsc_mark() {
		return hsc_mark;
	}
	public void setHsc_mark(String hsc_mark) {
		this.hsc_mark = hsc_mark;
	}
	public String getDiplomo_college_name() {
		return diplomo_college_name;
	}
	public void setDiplomo_college_name(String diplomo_college_name) {
		this.diplomo_college_name = diplomo_college_name;
	}
	public String getDiplomo_mark() {
		return diplomo_mark;
	}
	public void setDiplomo_mark(String diplomo_mark) {
		this.diplomo_mark = diplomo_mark;
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, aid, contact_no, course, diplomo_college_name, diplomo_mark, dob, emailid, gender,
				hsc_mark, hsc_school_name, name, parent_name, sslc_mark, sslc_school_name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Applications other = (Applications) obj;
		return Objects.equals(address, other.address) && Objects.equals(aid, other.aid)
				&& Objects.equals(contact_no, other.contact_no) && Objects.equals(course, other.course)
				&& Objects.equals(diplomo_college_name, other.diplomo_college_name)
				&& Objects.equals(diplomo_mark, other.diplomo_mark) && Objects.equals(dob, other.dob)
				&& Objects.equals(emailid, other.emailid) && Objects.equals(gender, other.gender)
				&& Objects.equals(hsc_mark, other.hsc_mark) && Objects.equals(hsc_school_name, other.hsc_school_name)
				&& Objects.equals(name, other.name) && Objects.equals(parent_name, other.parent_name)
				&& Objects.equals(sslc_mark, other.sslc_mark)
				&& Objects.equals(sslc_school_name, other.sslc_school_name);
	}
	@Override
	public String toString() {
		return "Applications [aid=" + aid + ", name=" + name + ", parent_name=" + parent_name + ", dob=" + dob
				+ ", gender=" + gender + ", course=" + course + ", contact_no=" + contact_no + ", address=" + address
				+ ", emailid=" + emailid + ", sslc_school_name=" + sslc_school_name + ", sslc_mark=" + sslc_mark
				+ ", hsc_school_name=" + hsc_school_name + ", hsc_mark=" + hsc_mark + ", diplomo_college_name="
				+ diplomo_college_name + ", diplomo_mark=" + diplomo_mark + "]";
	}
	


}
