package si.fri.rso.samples.comments.models;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "comment")
@NamedQueries(value =
{
        @NamedQuery(name = "Comment.getAll", query = "SELECT c FROM comment c"),
        @NamedQuery(name = "Comment.findByCustomer", query = "SELECT o FROM comment o WHERE o.customerId = " +
                ":customerId")
})
@UuidGenerator(name = "idGenerator")
public class Comment {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "date_posted")
    private Date datePosted;

    @Column(name = "customer_id")
    private String customerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}