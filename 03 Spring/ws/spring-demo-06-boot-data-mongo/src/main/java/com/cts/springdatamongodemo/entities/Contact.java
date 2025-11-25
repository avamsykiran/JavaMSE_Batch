package com.cts.springdatamongodemo.entities;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collation = "contacts")
public class Contact {

	@Id
	private Long contactId;	
	private String fullName;	
	private String mobileNumber;	
	private String mailId;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dateOfBith;
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(Long contactId, String fullName, String mobileNumber, String meilId, LocalDate dateOfBith) {
		super();
		this.contactId = contactId;
		this.fullName = fullName;
		this.mobileNumber = mobileNumber;
		this.mailId = meilId;
		this.dateOfBith = dateOfBith;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
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

	
	
	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
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
