package services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Song;
import model.ttd;
import command.CreateSongCommand;
import command.CreateTTD;
import command.DeleteTTD;
import command.GetSongCommand;
import command.ListSongsCommand;
import command.ListTTD;
import command.UpdateSongCommand;
import util.Constants;


@Path("ttd")
public class Services {
	ObjectMapper mapper = new ObjectMapper();

	// get all ttd
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response listall(@QueryParam("offset") int offset,
			@QueryParam("count") int count) {
		ListTTD command = new ListTTD();
		ArrayList<ttd> list = command.execute();
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put(Constants.Pagination.DATA, list);
		hm.put(Constants.Pagination.OFFSET, offset);
		hm.put(Constants.Pagination.COUNT, count);
		String ttds = null;
		try {
			ttds = mapper.writeValueAsString(hm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(ttds).build();
	}

	// add tht ttd
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createSongs(String payload) {
		CreateTTD create = new CreateTTD();
		ttd s = null;
		String i = "";
		try {
			s = mapper.readValue(payload, ttd.class);
			
			System.out.println(s.getTitle());
			System.out.println(s.getDes());
		} catch (Exception ex) {
			ex.printStackTrace();
			Response.status(400).entity("could not read string").build();
		}
		try {
			i = create.execute(s);
		} catch (Exception e) {
			e.printStackTrace();
			Response.status(500).build();
		}
		return Response.status(200).entity(i).build();
	}
	
	
	
	//***********************************************************************************************//

	
	
	// delete ttd
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response delttd(String payload, @PathParam("id") int id) {
		DeleteTTD del = new DeleteTTD();
		
		try {
			if(del.execute(id)){
				return Response.status(200).build();
			}else
				Response.status(500).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			Response.status(400).entity("could not read string").build();
		}
		
		return Response.status(500).build();
	}
	
	
	

}

