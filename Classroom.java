
public class Classroom {
	private String roomNumber;
	private String className;
	private int capacity;
	
	public Classroom(String roomNumber,String className,int capacity) {
		this.roomNumber = roomNumber;
		this.className = className;
		this.capacity = capacity;
	}
	
	public String getRoomNumber() {
		return roomNumber;
	}
	
	public String getClassName() {
		return className;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public String toString() {
		return "Class's name: " + className + "  number: " + roomNumber + "  Capacity: " + capacity;
	}
	

}
