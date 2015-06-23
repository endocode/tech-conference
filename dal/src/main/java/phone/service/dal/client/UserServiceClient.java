package phone.service.dal.client;

import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.ClientProperties;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.TemplateName;
import com.netflix.ribbon.proxy.annotation.Var;
import io.netty.buffer.ByteBuf;

@ClientProperties(properties = {
        @ClientProperties.Property(name = "ReadTimeout", value = "500"),
        @ClientProperties.Property(name = "ConnectTimeout", value = "100"),
        @ClientProperties.Property(name = "MaxAutoRetries", value = "1"),
        @ClientProperties.Property(name = "listOfServers", value = "http://demo3330886.mockable.io")
}, exportToArchaius = true)
public interface UserServiceClient {
    @Http(
            method = Http.HttpMethod.GET,
            uri = "/user/{userId}",
            headers =  {
                    @Http.Header(name = "Accept", value = "application/json")
            })
    @TemplateName("getUser")
    RibbonRequest<ByteBuf> getUser(@Var("userId") final String userId);
}
