package co.com.ceiba.infrastructure.restcontroller;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class RestPersonas_getPersonInformationByIdentification_Test {

	@Rule
	public WireMockRule personRule = new WireMockRule(8089);
	
	RestTemplate client;
	
    @Before
    public void init() {
        client = new RestTemplate();
    }
	
	public void getPersonInformationTest() {
		personRule.stubFor(get(urlEqualTo("http://localhost:8080/reto-team-lead/persons/98989898"))
	            .willReturn(aResponse()
	                .withStatus(200)
	                .withHeader("Content-Type", "application/json")
	                .withBody("[{\"id\":0,\"identification\":98989898,\"name\":\"PETER\",\"lastname\":\"PARKER\",\"dateOfBirth\":\"1994-08-05\",\"age\":25}]")));
	    String answer = client.getForObject("http://localhost:8080/reto-team-lead/persons/98989898", String.class);
	    Gson gson = new Gson();
	    JsonElement json = gson.toJsonTree(answer);
	    Assert.assertTrue(json.isJsonPrimitive());
	}

}
