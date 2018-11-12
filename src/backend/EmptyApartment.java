package backend;

import java.io.Serializable;

public class EmptyApartment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String address;
	private String postal_code;
	private String city;
	private Double size;
	private int rooms;
	private Double rent;
	
	public EmptyApartment(int id, String address, String postal_code, String city, Double size, int rooms,
			Double rent) {
		super();
		this.id = id;
		this.address = address;
		this.postal_code = postal_code;
		this.city = city;
		this.size = size;
		this.rooms = rooms;
		this.rent = rent;
	}
	public EmptyApartment(String address, String postal_code, String city, Double size, int rooms, Double rent) {
		super();
		this.address = address;
		this.postal_code = postal_code;
		this.city = city;
		this.size = size;
		this.rooms = rooms;
		this.rent = rent;
	}
	
	
	public int getId() {
		return id;
	}
	public String getAddress() {
		return address;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public String getCity() {
		return city;
	}
	public Double getSize() {
		return size;
	}
	public int getRooms() {
		return rooms;
	}
	public Double getRent() {
		return rent;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	public void setRent(Double rent) {
		this.rent = rent;
	}
	


}
