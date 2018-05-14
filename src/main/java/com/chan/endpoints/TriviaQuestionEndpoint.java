package com.chan.endpoints;

import com.chan.model.TriviaQuestion;
import com.chan.model.TriviaQuestionAccessible;
import com.chan.model.TriviaQuestoinArrayAccess;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Date;
import java.util.List;

@Path("/questions")
final public class TriviaQuestionEndpoint {
    final private TriviaQuestionAccessible dataAccess = new TriviaQuestoinArrayAccess();
    private static final int STARTING_OFFSET = 0;
    private static final int PAGE_SIZE = 10;
    final private Date quetionsUpdatedDate = new Date();

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestionCount(@Context UriInfo uri){
        long numberOfQuestions = dataAccess.getQuestionListSize();
        return Response.ok(numberOfQuestions)
                .header("question-count",numberOfQuestions)
                .lastModified(quetionsUpdatedDate)
                .location(uri.getRequestUri())
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestions(@Context UriInfo uri, @QueryParam("offset") @DefaultValue("0") long offset){
        //Calculate the effective offset
        long datasetSize = dataAccess.getQuestionListSize();
        long start = offset;
        if(start<STARTING_OFFSET){
            start=STARTING_OFFSET;
        } else if (start >= datasetSize){
            start = datasetSize;
        }

        //Setup navigation Links
        Link selfLink = Link.fromUri(uri.getBaseUri()+"questions?offset={offset}")
                .rel("self").type(MediaType.APPLICATION_JSON)
                .build(offset);
        long nextOffset = (offset+PAGE_SIZE)<datasetSize ? offset + PAGE_SIZE : datasetSize;
        Link nextLink = Link.fromUri(uri.getBaseUri()+"questions?offset={offset}")
                .rel("next").type(MediaType.APPLICATION_JSON)
                .build(nextOffset);
        long prevOffset = (offset-PAGE_SIZE)> STARTING_OFFSET ? offset - PAGE_SIZE : STARTING_OFFSET;
        Link prevLink = Link.fromUri(uri.getBaseUri()+"questions?offset={offset}")
                .rel("prev").type(MediaType.APPLICATION_JSON)
                .build(prevOffset);
        Link firstLink = Link.fromUri(uri.getBaseUri()+"questions?offset={offset}")
                .rel("first").type(MediaType.APPLICATION_JSON)
                .build(STARTING_OFFSET);
        Link lastLink = Link.fromUri(uri.getBaseUri()+"questions?offset={offset}")
                .rel("last").type(MediaType.APPLICATION_JSON)
                .build(datasetSize);
        Link countLink = Link.fromUri(uri.getBaseUri()+"questions/count")
                .rel("count").type(MediaType.APPLICATION_JSON)
                .build();
        Link rndLink = Link.fromUri(uri.getBaseUri()+"questions/random")
                .rel("random").type(MediaType.APPLICATION_JSON)
                .build();

        //Get the list of questions from starting point
        List<TriviaQuestion> list = dataAccess.getQuestionList(start);
        return Response.ok(list)
                .header("question-count",datasetSize)
                .header("current-question-list-size",list.size())
                .header("offset",start)
                .links(selfLink,nextLink,prevLink,firstLink,lastLink,countLink,rndLink)
                .lastModified(quetionsUpdatedDate)
                .location(uri.getRequestUri())
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestion(@Context UriInfo uri, @PathParam("id") String idString){
        Response response;
        if(idString.trim().equals("random")){
            TriviaQuestion question = dataAccess.getRandomQuestion();
            response = Response.ok(question)
                    .lastModified(question.getLastUpdated())
                    .location(uri.getRequestUri()).build();
        }
        else {
            long identifier = Long.parseLong(idString);
            try {
                if (identifier >= dataAccess.getQuestionListSize()) {
                    response = Response.status(Response.Status.NOT_FOUND).build();
                } else {
                    TriviaQuestion question = dataAccess.getQuestionById(identifier);
                    response = Response.ok(question)
                            .lastModified(question.getLastUpdated())
                            .location(uri.getRequestUri()).build();
                }
            }
            catch (NumberFormatException ne){
                response = Response.status(Response.Status.BAD_REQUEST).build();
            }

        }
        return response;
    }
}