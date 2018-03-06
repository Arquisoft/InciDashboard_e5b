package uo.asw.apacheKafka.consumer;

import javax.annotation.ManagedBean;

import org.springframework.kafka.annotation.KafkaListener;

@ManagedBean
public class MessageListener {
	//private static final Logger logger = Logger.getLogger(MessageListener.class);

	@KafkaListener(topics = "exampleTopic")
	public void listen(String data) {
		//logger.info("New message received: \"" + data + "\"");
	}
}