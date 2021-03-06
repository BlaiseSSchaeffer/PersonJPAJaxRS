package application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import api.PersonRestResource;

@ApplicationPath("/api")
public class App extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>(1);
		classes.add(PersonRestResource.class);
		return classes;
	}

}
