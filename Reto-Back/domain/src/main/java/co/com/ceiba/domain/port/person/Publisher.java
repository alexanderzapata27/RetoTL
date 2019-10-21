package co.com.ceiba.domain.port.person;

import co.com.ceiba.domain.exception.TLException;

public interface Publisher {
	public void sendPeopleMostOf18() throws TLException;
	public void sendPeopleLessOf18() throws TLException;
}
