package co.com.ceiba.infrastructure.repository.query.person;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Repository;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import co.com.ceiba.domain.port.person.Receiver;

@Repository
public class ReceiverRabbitMQ implements Receiver{

	private final static String QUEUE_NAME_MOSTOF18 = "PeopleMostOf18";
	private final static String QUEUE_NAME_LESSOF18 = "PeopleLessOf18";
	String actualMessage;

	@Override
	public String readPeopleMostOf18() throws IOException, TimeoutException  {
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        actualMessage = "";
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
        return actualMessage;
	}

	@Override
	public String readPeopleLessOf18() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		actualMessage = "";
        factory.setHost("localhost");
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
        return actualMessage;
	}

}
