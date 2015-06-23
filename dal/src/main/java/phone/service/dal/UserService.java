package phone.service.dal;

import com.fasterxml.jackson.databind.ObjectMapper;
import phone.service.dal.client.UserServiceClient;
import phone.service.dal.model.User;
import rx.Observable;

import java.io.IOException;
import java.nio.charset.Charset;

public class UserService {
    private final UserServiceClient userServiceClient;
    private final ObjectMapper mapper;

    public UserService(UserServiceClient userServiceClient, ObjectMapper objectMapper) {
        this.userServiceClient = userServiceClient;
        this.mapper = objectMapper;
    }

    public Observable<User> getUser(final String userId) {
        return userServiceClient.getUser(userId).observe()
                .map(byteBuf -> byteBuf.toString(Charset.defaultCharset()))
                .map(this::getUserFromJson);
    }

    private User getUserFromJson(String json) {
        try {
            return mapper.readValue(json, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
