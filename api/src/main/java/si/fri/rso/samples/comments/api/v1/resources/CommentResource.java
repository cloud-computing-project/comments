package si.fri.rso.samples.comments.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso.samples.comments.models.Comment;
import si.fri.rso.samples.comments.services.CommentsBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log
public class CommentResource {

    @Inject
    private CommentsBean commentsBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getComments() {

        List<Comment> comments = commentsBean.getComments(uriInfo);

        return Response.ok(comments).build();
    }


    @GET
    @Path("{commentId}")
    public Response getComment(@PathParam("commentId") String commentId) {

        Comment comment = commentsBean.getComment(commentId);

        if (comment == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(comment).build();
    }

    @POST
    public Response createComment(Comment comment) {

        if (comment.getCommentContent() == null || comment.getCommentContent().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            comment = commentsBean.createComment(comment);
        }

        if (comment.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(comment).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(comment).build();
        }
    }

    @PUT
    @Path("{commentId}")
    public Response putComment(@PathParam("commentId") String commentId, Comment comment) {

        comment = commentsBean.putComment(commentId, comment);

        if (comment == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (comment.getId() != null)
                return Response.status(Response.Status.OK).entity(comment).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("{commentId}")
    public Response deleteComment(@PathParam("commentId") String commentId) {

        boolean deleted = commentsBean.deleteComment(commentId);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
