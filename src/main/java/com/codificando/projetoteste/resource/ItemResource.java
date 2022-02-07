package com.codificando.projetoteste.resource;

import java.net.URI;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.codificando.projetoteste.dao.ItemDao;
import com.codificando.projetoteste.model.Item;
import com.google.gson.Gson;

/*import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET; 
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;*/

@Path("/item")
@Transactional
public class ItemResource {
	
	
	//@Autowired
	private ItemDao itemDao;
	
	public ItemResource() {
		this.itemDao = new ItemDao();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String find(@PathParam("id") int id ) {
		Item item = this.itemDao.find(id);
		return new Gson().toJson(item);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(String item) {
		Item obj = new Gson().fromJson(item, Item.class);
		obj.setId(null);
		try {
			obj = itemDao.insert(obj);
			return Response.status(Response.Status.CREATED).entity(obj).build();
		} catch(Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 	
	}

}
