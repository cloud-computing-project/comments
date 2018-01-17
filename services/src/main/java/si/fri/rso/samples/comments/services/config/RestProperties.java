package si.fri.rso.samples.comments.services.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("rest-properties")
public class RestProperties {

    @ConfigValue(value = "external-services.comment-service.enabled", watch = true)
    private boolean commentServiceEnabled;

    public boolean isCommentServiceEnabled() {
        return commentServiceEnabled;
    }

    public void setCommentServiceEnabled(boolean commentServiceEnabled) {
        this.commentServiceEnabled = commentServiceEnabled;
    }
}
