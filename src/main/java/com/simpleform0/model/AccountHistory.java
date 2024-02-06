package com.simpleform0.model;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="accountmaintenance")
public class AccountHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer tno;
	@Column(nullable=true)
	String username;
	@Column(nullable=true)
	Long regno;
	@Column(nullable=true)
	Date date;
	@Column(nullable=true)
	Time time;
	@Column(nullable=true)
	String name;	
	@Column(nullable=true)
	String course;
	@Column(precision = 10, scale = 2,nullable=false)
	BigDecimal amount_paid;
	public Integer getTno() {
		return tno;
	}
	public void setTno(Integer tno) {
		this.tno = tno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getRegno() {
		return regno;
	}
	public void setRegno(Long regno) {
		this.regno = regno;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public BigDecimal getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(BigDecimal amount_paid) {
		this.amount_paid = amount_paid;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(amount_paid, course, date, name, regno, time, tno, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountHistory other = (AccountHistory) obj;
		return Objects.equals(amount_paid, other.amount_paid) && Objects.equals(course, other.course)
				&& Objects.equals(date, other.date) && Objects.equals(name, other.name)
				&& Objects.equals(regno, other.regno) && Objects.equals(time, other.time)
				&& Objects.equals(tno, other.tno) && Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "AccountHistory [tno=" + tno + ", username=" + username + ", regno=" + regno + ", date=" + date
				+ ", time=" + time + ", name=" + name + ", course=" + course + ", amount_paid=" + amount_paid + "]";
	}
	

}



