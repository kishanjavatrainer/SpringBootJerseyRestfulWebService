package com.infotech.jersey.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.infotech.jersey.model.User;
import com.infotech.jersey.model.Users;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
@Path("/users")
public class UserRestService {
	private static Map<Integer, User> INMEMORYDB = new HashMap<>();
	static {
		User user1 = new User();
		user1.setId(1);
		user1.setFirstName("Pamela");
		user1.setLastName("Smith");

		User user2 = new User();
		user2.setId(2);
		user2.setFirstName("Louis");
		user2.setLastName("Lopez");
		
		INMEMORYDB.put(user1.getId(), user1);
		INMEMORYDB.put(user2.getId(), user2);
	}

	@GET
	@Produces("application/json")
	public Users getAllUsers() {
		Users users = new Users();
		users.setUsers(new ArrayList<>(INMEMORYDB.values()));
		return users;
	}

	@GET
	@Path("/user/{userId}")
	@Produces("application/json")
	public Response getUserById(@PathParam("userId") int userId) throws URISyntaxException {
		User user = INMEMORYDB.get(userId);
		if (user == null) {
			return Response.status(404).build();
		}
		return Response.status(200).entity(user).build();
	}

	@POST
	@Consumes("application/json")
	@Path("/user/create")
	public Response createUser(User user) throws URISyntaxException {
		if (user.getFirstName() == null || user.getLastName() == null) {
			return Response.status(400).entity("Please provide all mandatory inputs").build();
		}
		user.setId(INMEMORYDB.values().size() + 1);
		INMEMORYDB.put(user.getId(), user);
		return Response.status(201).build();
	}

	@PUT
	@Path("/update/{userId}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateUser(@PathParam("userId") int userId, User user) throws URISyntaxException {
		User temp = INMEMORYDB.get(userId);
		if (user == null) {
			return Response.status(404).build();
		}
		temp.setFirstName(user.getFirstName());
		temp.setLastName(user.getLastName());
		INMEMORYDB.put(temp.getId(), temp);
		return Response.status(200).entity(temp).build();
	}

	@DELETE
	@Path("/delete/{userId}")
	public Response deleteUser(@PathParam("userId") int userId) throws URISyntaxException {
		User user = INMEMORYDB.get(userId);
		if (user != null) {
			INMEMORYDB.remove(user.getId());
			return Response.status(200).build();
		}
		return Response.status(404).build();
	}
}
