package uo.asw.apacheKafka.consumer;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import uo.asw.inciDashboard.filter.ReceiveIncidence;

@ManagedBean
public class MessageListener {

	@Autowired
	private ReceiveIncidence receiveIncidence;
	
	@KafkaListener(topics = "incidences") //TODO - topics????
	public void listen(String data) {
		System.out.println(data);// TODO - QUITAR CUANDO ACABE DE PROBARSE
		receiveIncidence.receiveIncidence(data);
	}
}