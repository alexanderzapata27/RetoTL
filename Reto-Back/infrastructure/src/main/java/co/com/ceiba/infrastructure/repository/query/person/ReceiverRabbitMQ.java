package co.com.ceiba.infrastructure.repository.query.person;

import org.springframework.stereotype.Repository;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import co.com.ceiba.domain.exception.TLException;
import co.com.ceiba.domain.port.person.Receiver;

@Repository
public class ReceiverRabbitMQ implements Receiver{

	private static final String QUEUE_NAME_MOSTOF18 = "PeopleMostOf18";
	private static final String QUEUE_NAME_LESSOF18 = "PeopleLessOf18";
	String actualMessage;

	@Override
	public String readPeopleMostOf18() throws TLException  {
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        actualMessage = "";
        try {
        	Connection connection = factory.newConnection();
        	Channel channel = connection.createChannel();
        	
        	channel.queueDeclare(QUEUE_NAME_MOSTOF18, false, false, false, null);
        	synchronized (actualMessage) {
        		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        			String tempMessage = new String(delivery.getBody(), "UTF-8");
        			actualMessage = tempMessage;
        			System.out.println("Received '" + actualMessage + "'");
        		};
        		
        		channel.basicConsume(QUEUE_NAME_MOSTOF18, true, deliverCallback, consumerTag -> { });
        	}
        }catch(Exception e) {
        	throw new TLException("No se logró la comunicación con la cola de rabbitmq leyendo",e);
        }
        return actualMessage;
	}

	@Override
	public String readPeopleLessOf18() throws TLException {
		ConnectionFactory factory = new ConnectionFactory();
		actualMessage = "";
        factory.setHost("localhost");
        try {
        	Connection connection = factory.newConnection();
        	Channel channel = connection.createChannel();
        	
        	channel.queueDeclare(QUEUE_NAME_LESSOF18, false, false, false, null);
        	synchronized (actualMessage) {
        		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        			String message = new String(delivery.getBody(), "UTF-8");
        			System.out.println("People Less Of 18 Received '" + message + "'");
        		};
        		channel.basicConsume(QUEUE_NAME_LESSOF18, true, deliverCallback, consumerTag -> { });
        	}
        }catch(Exception e) {
        	throw new TLException("Error en la comunicación con la cola de rabbitmq en lectura",e);
        }
        
        return actualMessage;
	}

}
