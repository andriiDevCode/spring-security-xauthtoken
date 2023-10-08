package security.xauthtoken.userdetails;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;
import security.xauthtoken.entity.UserEntity;

import java.util.ArrayList;

@Getter
public class UserDetails extends User {
    private final long userId;

    public UserDetails(UserEntity userEntity) {
        super(userEntity.getEmail(), userEntity.getPassword(), true, true, true, true, new ArrayList<>());
        this.userId = userEntity.getId();
    }

    public String getEmail() {
        return this.getUsername();
    }

}
