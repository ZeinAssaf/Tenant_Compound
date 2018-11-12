package backend;
public class Apartment {
	
	private int id;
	private double size;
	private int rooms;
	private double rent;
	private boolean balcony;
	private int floor;
	private String address;
	private String postal_code;
	private String city;
	public Apartment(int id, double size, int rooms, double rent, boolean balcony, int floor, String address,
			String postal_code, String city) {
		
		this.id = id;
		this.size = size;
		this.rooms = rooms;
		this.rent = rent;
		this.balcony = balcony;
		this.floor = floor;
		this.address = address;
		this.postal_code = postal_code;
		this.city = city;
	}
	public Apartment(double size, int rooms, double rent, boolean balcony, int floor, String address,
			String postal_code, String city) {
		
		this.size = size;
		this.rooms = rooms;
		this.rent = rent;
		this.balcony = balcony;
		this.floor = floor;
		this.address = address;
		this.postal_code = postal_code;
		this.city = city;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public int getRooms() {
		return rooms;
	}
	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public boolean isBalcony() {
		return balcony;
	}
	public void setBalcony(boolean balcony) {
		this.balcony = balcony;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Apartment [id=" + id + ", size=" + size + ", rooms=" + rooms + ", rent=" + rent + ", balcony=" + balcony
				+ ", floor=" + floor + ", address=" + address + ", postal_code=" + postal_code + ", city=" + city + "]";
	}

}
