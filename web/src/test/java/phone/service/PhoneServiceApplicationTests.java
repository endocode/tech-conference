package phone.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import phone.service.model.test.NormalizedPhone;
import phone.service.model.test.NormalizedUser;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PhoneServiceApplication.class)
@WebIntegrationTest(randomPort = true)
public class PhoneServiceApplicationTests extends IntegrationTestBase {

	@Value("${local.server.port}")
	int port;

	final String phoneNormalizationTemplate = "http://localhost:{port}/api/phones/{phoneNumber}";
	final String userPhoneTemplate = "http://localhost:{port}/api/users/{userId}/phone";
	final String userTemplate = "http://localhost:{port}/api/users/{userId}";

	@Test
	public void getNormalizedPhoneReturnsOK() {
		final RestTemplate restTemplate = new RestTemplateBuilder()
				.addAcceptType(MediaType.APPLICATION_JSON)
				.withErrorsSuppressed()
				.setConnectTimeout(1000)
				.setReadTimeout(2000)
				.build();
		final ResponseEntity<NormalizedPhone> responseEntity = restTemplate.getForEntity(
				phoneNormalizationTemplate,
				NormalizedPhone.class,
				new UrlParamsBuilder()
						.addParam("port", Integer.toString(port))
						.addParam("phoneNumber", "801-234-9000")
						.build());
		assertThat("The phone number request should return OK/200.", responseEntity.getStatusCode(), is(HttpStatus.OK));
		assertThat("The phone number should be normalized.", responseEntity.getBody().getPhoneNumber(), is("8012349000"));
	}

	@Test
	public void getUserPhone() {
		final RestTemplate restTemplate = new RestTemplateBuilder()
				.addAcceptType(MediaType.APPLICATION_JSON)
				.withErrorsSuppressed()
				.setConnectTimeout(1000)
				.setReadTimeout(2000)
				.build();
		final ResponseEntity<NormalizedPhone> responseEntity = restTemplate.getForEntity(
				userPhoneTemplate,
				NormalizedPhone.class,
				new UrlParamsBuilder()
						.addParam("port", Integer.toString(port))
						.addParam("userId", "1")
						.build());
		assertThat("The phone number request should return OK/200.", responseEntity.getStatusCode(), is(HttpStatus.OK));
		final NormalizedPhone normalizedPhone = responseEntity.getBody();
		assertThat("The phone number should be normalized.", normalizedPhone.getOriginalNumber(), is("801.234.5678"));
		assertThat("The phone number should be normalized.", normalizedPhone.getPhoneNumber(), is("8012345678"));
	}

	@Test
	public void getUser() {
		final RestTemplate restTemplate = new RestTemplateBuilder()
				.addAcceptType(MediaType.APPLICATION_JSON)
				.withErrorsSuppressed()
				.setConnectTimeout(1000)
				.setReadTimeout(2000)
				.build();
		final ResponseEntity<NormalizedUser> responseEntity = restTemplate.getForEntity(
				userTemplate,
				NormalizedUser.class,
				new UrlParamsBuilder()
						.addParam("port", Integer.toString(port))
						.addParam("userId", "2")
						.build());
		assertThat("The phone number request should return OK/200.", responseEntity.getStatusCode(), is(HttpStatus.OK));
		final NormalizedUser user = responseEntity.getBody();
		assertThat("The user should be John.", user.getFirstName(), is("John"));
		assertThat("The phone number should be normalized and formatted.", user.getFormattedPhoneNumber(), is("+1 234-645-4567"));
	}

}
