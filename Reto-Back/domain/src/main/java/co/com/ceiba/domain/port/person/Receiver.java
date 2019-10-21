package co.com.ceiba.domain.port.person;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface Receiver {
	public String readPeopleMostOf18() throws IOException, TimeoutException;
	public String readPeopleLessOf18() throws IOException, TimeoutException;
}
