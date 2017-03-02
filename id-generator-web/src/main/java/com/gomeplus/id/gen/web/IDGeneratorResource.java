package com.gomeplus.id.gen.web;

import com.gomeplus.id.gen.web.holder.GeneratorHolder;
import com.gomeplus.id.gen.web.holder.PropertiesHolder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Tony
 * @version 0.1.0
 */
@Path("/id")
public class IDGeneratorResource {



    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String nextId(@QueryParam("business") String business,@QueryParam("type") Integer type) {
        return String.valueOf(GeneratorHolder.getId(business,type));
    }
}
