package uo.asw.apacheKafka.producer;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@ManagedBean
public class KafkaProducer {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void send(String topic, String data) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data); 
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			
			@Override
			public void onSuccess(SendResult<String, String> result) {
				//COMPLETE
			}
			
			@Override
			public void onFailure(Throwable ex) {
				//COMPLETE
			}
			
		});	
	}
	
}
