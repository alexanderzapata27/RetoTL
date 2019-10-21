package co.com.ceiba.domain.port.person;

import co.com.ceiba.domain.exception.TLException;

public interface Receiver {
	public String readPeopleMostOf18() throws TLException;
	public String readPeopleLessOf18() throws TLException;
}
