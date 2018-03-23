package kafka.consumer;

public class Message {
	
	String mesage;
	
	private Message() {}
	
	public Message (String mesage) {
		this.mesage = mesage;
	}

	public String getMesage() {
		return mesage;
	}

	public void setMesage(String mesage) {
		this.mesage = mesage;
	}

	
}
