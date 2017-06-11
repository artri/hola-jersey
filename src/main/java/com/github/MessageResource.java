
package com.github;

import com.github.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/message")
public class MessageResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GET
    @Path("ping")
    public String getServerTime() {
        logger.info("RESTful Service 'MessageService' is running ==> ping");
        return "received ping on " + new Date().toString();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})  //add MediaType.APPLICATION_XML if you want XML as well (don't forget @XmlRootElement)
    public List<Message> getAllMessages() throws Exception{

        List<Message> messages = new ArrayList<>();

        Message m = new Message();
        m.setDate(new Date());
        m.setFirstName("Nabi");
        m.setLastName("Zamani");
        m.setText("Hello World!");
        messages.add(m);

        logger.info("getAllMessages(): found "+messages.size()+" message(s) on DB");

        return messages; //do not use Response object because this causes issues when generating XML automatically
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/post")
    public String postMessage(Message msg) throws Exception{

        logger.info("First Name = "+msg.getFirstName());
        logger.info("Last Name  = "+msg.getLastName());

        return "ok";
    }
}
