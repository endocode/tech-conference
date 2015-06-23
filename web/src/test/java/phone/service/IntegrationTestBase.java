package phone.service;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class IntegrationTestBase {
    protected static class RestTemplateBuilder {
        private boolean suppressErrors = false;
        private MediaType contentType;
        private List<MediaType> acceptsTypes = new ArrayList<>();
        private int connectTimeout;
        private int readTimeout;

        public RestTemplateBuilder withErrorsSuppressed() {
            suppressErrors = true;
            return this;
        }

        public RestTemplateBuilder addAcceptType(MediaType mediaType) {
            this.acceptsTypes.add(mediaType);
            return this;
        }

        public RestTemplateBuilder setContentType(MediaType mediaType) {
            this.contentType = mediaType;
            return this;
        }

        public RestTemplateBuilder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public RestTemplateBuilder setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public RestTemplate build() {
            RestTemplate restTemplate = new RestTemplate();
            if (suppressErrors) {
                restTemplate.setErrorHandler(new DefaultErrorHandler());
            }

            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(connectTimeout);
            requestFactory.setReadTimeout(readTimeout);
            restTemplate.setRequestFactory(requestFactory);

            HeadersHttpRequestInterceptor requestInterceptor = new HeadersHttpRequestInterceptor(acceptsTypes, contentType);
            restTemplate.setInterceptors(new ArrayList<ClientHttpRequestInterceptor>() {{ add(requestInterceptor); }});

            return restTemplate;
        }
    }

    protected static class UrlParamsBuilder {
        private final Map<String, String> map = new HashMap<>();

        public UrlParamsBuilder addParam(String name, String value) {
            map.put(name, value);
            return this;
        }

        public Map<String, String> build() {
            return map;
        }
    }

    private static class HeadersHttpRequestInterceptor implements ClientHttpRequestInterceptor {

        final private List<MediaType> acceptsTypes;
        final private MediaType contentType;

        private HeadersHttpRequestInterceptor(List<MediaType> acceptsTypes, MediaType contentType) {
            this.acceptsTypes = acceptsTypes;
            this.contentType = contentType;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
            HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper(httpRequest);
            if (acceptsTypes != null && acceptsTypes.size() > 0) {
                httpRequestWrapper.getHeaders().setAccept(acceptsTypes);
            }
            if (contentType != null)
            {
                httpRequestWrapper.getHeaders().setContentType(contentType);
            }
            return clientHttpRequestExecution.execute(httpRequestWrapper, bytes);
        }
    }

    private static class DefaultErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

        }
    }

}
