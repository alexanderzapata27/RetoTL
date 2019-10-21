package co.com.ceiba.infrastructure.repository.command.person;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import co.com.ceiba.domain.dto.person.PersonDTO;
import co.com.ceiba.domain.exception.TLException;
import co.com.ceiba.domain.port.person.PersonDAO;
import co.com.ceiba.domain.port.person.Publisher;

@Repository
public class PublisherRabbitMQ implements Publisher{
	private static final String QUEUE_NAME_MOSTOF18 = "PeopleMostOf18";
	private static final String QUEUE_NAME_LESSOF18 = "PeopleLessOf18";
	PersonDAO personDao;
	Gson gson;
	
	@Autowired
	public PublisherRabbitMQ(PersonDAO personDao) {
		this.personDao = personDao;
		gson = new Gson();
	}
	

	@Override
	public void sendPeopleMostOf18() throws TLException {
		ConnectionFactory factory = new ConnectionFactory();
		
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME_MOSTOF18, false, false, false, null);
            List<PersonDTO> lista = personDao.getAll().stream().filter(person -> person.getAge()>=18).collect(Collectors.toList());
            String message = gson.toJson(lista);
            channel.basicPublish("", QUEUE_NAME_MOSTOF18, null, message.getBytes("UTF-8"));
            System.out.println("Sent '" + message + "'");
        } catch (IOException e) {
			throw new TLException("No se obtuvieron datos de entrada correctos");
		} catch (TimeoutException e) {
			throw new TLException("La comunicaci�n con la cola de informacion no pudo ser establecida",e);
		}
	}

	@Override
	public void sendPeopleLessOf18() throws TLException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME_LESSOF18, false, false, false, null);
            List<PersonDTO> lista = personDao.getAll().stream().filter(person -> person.getAge()<18).collect(Collectors.toList());
            String message = gson.toJson(lista);
            channel.basicPublish("", QUEUE_NAME_LESSOF18, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Sent '" + message + "'");
		} catch (IOException e) {
			throw new TLException("No se obtuvieron datos de entrada correctos",e);
		} catch (TimeoutException e) {
			throw new TLException("La comunicaci�n con la cola de informacion no pudo ser establecida",e);
		}
	}
	

}
