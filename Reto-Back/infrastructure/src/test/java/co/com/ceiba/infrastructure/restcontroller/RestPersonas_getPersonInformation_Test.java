package co.com.ceiba.infrastructure.restcontroller;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class RestPersonas_getPersonInformation_Test {

	@Rule
	public WireMockRule personRule = new WireMockRule(8089);
	
	RestTemplate client;
	
    @Before
    public void init() {
        client = new RestTemplate();
    }
	
	@Test
	public void getPersonInformationTest() {
		personRule.stubFor(get(urlEqualTo("http://localhost:8080/reto-team-lead/persons"))
	            .willReturn(aResponse()
	                .withStatus(200)
	                .withHeader("Content-Type", "application/json")
	                .withBody("[{\"id\":0,\"identification\":98989898,\"name\":\"PETER\",\"lastname\":\"PARKER\",\"dateOfBirth\":\"1994-08-05\",\"age\":25},{\"id\":0,\"identification\":5353,\"name\":\"ALBERTO\",\"lastname\":\"SALGADO\",\"dateOfBirth\":\"1994-08-05\",\"age\":25},{\"id\":0,\"identification\":5454,\"name\":\"JORGE\",\"lastname\":\"CLONEY\",\"dateOfBirth\":\"2000-01-26\",\"age\":19},{\"id\":0,\"identification\":5555,\"name\":\"JORGE\",\"lastname\":\"CLONEY\",\"dateOfBirth\":\"2002-10-22\",\"age\":16}]")));
	    String answer = client.getForObject("http://localhost:8080/reto-team-lead/persons", String.class);
	    Gson gson = new Gson();
	    JsonElement json = gson.toJsonTree(answer);
	    Assert.assertTrue(json.isJsonPrimitive());
	}

}
