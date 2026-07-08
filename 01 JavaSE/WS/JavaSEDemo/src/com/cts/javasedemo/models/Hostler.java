package com.cts.javasedemo.models;

import java.util.Objects;

public class Hostler extends Student {

	private int roomNumber;
	private double hostelFee;

	public Hostler() {
		
	}

	public Hostler(long admno, String fullName, String grade, double fee, int roomNumber, double hostelFee) {
		super(admno, fullName, grade, fee);
		this.roomNumber = roomNumber;
		this.hostelFee = hostelFee;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public double getHostelFee() {
		return hostelFee;
	}

	public void setHostelFee(double hostelFee) {
		this.hostelFee = hostelFee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(hostelFee, roomNumber);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hostler other = (Hostler) obj;
		return Double.doubleToLongBits(hostelFee) == Double.doubleToLongBits(other.hostelFee)
				&& roomNumber == other.roomNumber;
	}

	@Override
	public String toString() {
		return 
				super.toString() + 
				", roomNumber=" + roomNumber + ", hostelFee=" + hostelFee ;
	}

	
}
