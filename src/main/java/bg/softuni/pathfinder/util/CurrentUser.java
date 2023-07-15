package bg.softuni.pathfinder.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
public class CurrentUser {
    private Long id;
    private String name;
    private String email;
    private boolean loggedIn;

    public void clear() {
        this.id=null;
        this.loggedIn=false;
        this.name=null;
        this.email=null;
    }

    public boolean isAnonymous() {
        return !isLoggedIn();
    }
}
