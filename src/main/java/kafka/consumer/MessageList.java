package kafka.consumer;

import java.util.ArrayList;
import java.util.List;

public class MessageList {

	private static List<Message> messageList = new ArrayList<>();
	private static MessageList messages = new MessageList();

	public static MessageList getInstance() {
		return messages;
	}

	public void add(Message m) {
		messageList.add(m);
	}

	
	
}
