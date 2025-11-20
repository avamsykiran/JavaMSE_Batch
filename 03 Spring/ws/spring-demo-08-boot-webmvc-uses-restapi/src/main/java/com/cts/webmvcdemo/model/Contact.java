package com.cts.webmvcdemo.model;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Contact {

	private Integer contactId;
	
	@NotNull(message = "Full Name is a mandatory field")
	@NotBlank(message = "Full Name is a mandatory field")
	@Size(min = 5,max=25,message = "Full Name must be between 5 and 25 characters in length")
	private String fullName;
	
	@NotNull(message = "Mobile Number is a mandatory field")
	@NotBlank(message = "Mobile Number is a mandatory field")
	@Pattern(regexp = "[1-9][0-9]{9}",message = "Mobile Number must be a ten digit number")
	private String mobileNumber;
	
	@NotNull(message = "Mail Id is a mandatory field")
	@NotBlank(message = "Mail Id is a mandatory field")
	@Email(message = "Please provide a valid mail id")
	private String mailId;
	
	@NotNull(message = "Date of Birth is a mandatory field")
	@PastOrPresent(message = "Date Of Birth can not be a future date")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dateOfBith;
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(Integer contactId, String fullName, String mobileNumber, String meilId, LocalDate dateOfBith) {
		super();
		this.contactId = contactId;
		this.fullName = fullName;
		this.mobileNumber = mobileNumber;
		this.mailId = meilId;
		this.dateOfBith = dateOfBith;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getMeilId() {
		return mailId;
	}

	public void setMeilId(String meilId) {
		this.mailId = meilId;
	}

	public LocalDate getDateOfBith() {
		return dateOfBith;
	}

	public void setDateOfBith(LocalDate dateOfBith) {
		this.dateOfBith = dateOfBith;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", fullName=" + fullName + ", mobileNumber=" + mobileNumber
				+ ", meilId=" + mailId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(contactId, fullName, mailId, mobileNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return contactId == other.contactId && Objects.equals(fullName, other.fullName)
				&& Objects.equals(mailId, other.mailId) && Objects.equals(mobileNumber, other.mobileNumber);
	}
	
	
}
