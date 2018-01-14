package utilities;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Format {

	private Format() {
		// Intentionally left empty.
	}

	public static Response responseNoCache(Object o) throws JsonProcessingException {
		ResponseBuilder builder = Response
				.ok(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(o));
		WebUtilities.addNoCacheHeaders(builder);
		return builder.build();
	}
}
