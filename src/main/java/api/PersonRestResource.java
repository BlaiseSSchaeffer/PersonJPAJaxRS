package api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import model.Person;
import persistence.PersistenceService;
import utilities.Format;

@Path("/person")
public class PersonRestResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerson() throws JsonProcessingException {
		List<Person> people = PersistenceService.search("Person.findAll", Person.class);
		return Format.responseNoCache(people);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerson(@PathParam("id") String id) throws JsonProcessingException {
		return Format.responseNoCache(PersistenceService.find(Person.class, Long.parseLong(id)));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPerson(Person p) throws JsonProcessingException {
		return Format.responseNoCache(PersistenceService.persist(p));
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePerson(@PathParam("id") String id) throws JsonProcessingException {
		return Format.responseNoCache(PersistenceService.remove(Person.class, Long.parseLong(id)));
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePerson(Person p) throws JsonProcessingException {
		return Format.responseNoCache(PersistenceService.remove(p));
	}

}
