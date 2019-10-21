package co.com.ceiba.domain.port.person;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface Publisher {
	public void sendPeopleMostOf18() throws IOException, TimeoutException;
	public void sendPeopleLessOf18() throws IOException, TimeoutException;
}
