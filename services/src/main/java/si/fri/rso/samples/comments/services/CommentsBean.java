package si.fri.rso.samples.comments.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.samples.comments.models.Comment;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
public class CommentsBean {

    @Inject
    private EntityManager em;

    public List<Comment> getComments(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, Comment.class, queryParameters);

    }

    public Comment getComment(String commentId) {

        Comment comment = em.find(Comment.class, commentId);

        if (comment == null) {
            throw new NotFoundException();
        }

        return comment;
    }

    public Comment createComment(Comment comment) {

        try {
            beginTx();
            em.persist(comment);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return comment;
    }

    public Comment putComment(String commentId, Comment comment) {

        Comment c = em.find(Comment.class, commentId);

        if (c == null) {
            return null;
        }

        try {
            beginTx();
            comment.setId(c.getId());
            comment = em.merge(comment);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return comment;
    }

    public boolean deleteComment(String commentId) {

        Comment comment = em.find(Comment.class, commentId);

        if (comment != null) {
            try {
                beginTx();
                em.remove(comment);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else
            return false;

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}
