package app.utils;

import java.io.StringWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {
	private static final Log log = LogFactory.getLog(JsonUtils.class);

	private static final ObjectMapper mapper = new ObjectMapper();

	public static String jsonFromObject(Object object) {

		StringWriter writer = new StringWriter();

		try {

			mapper.writeValue(writer, object);

		} catch (RuntimeException e) {

			throw e;

		} catch (Exception e) {

			log.error("Unable to serialize to json: " + object, e);

			return null;

		}

		return writer.toString();

	}

	public static <T> T objectFromJson(String json, Class klass) {

		T object;

		try {

			object = (T) mapper.readValue(json, klass);

		} catch (RuntimeException e) {

			log.error("Runtime exception during deserializing "

			+ klass.getSimpleName() + " from "

			+ StringUtils.abbreviate(json, 80));

			throw e;

		} catch (Exception e) {

			log.error("Exception during deserializing " + klass.getSimpleName()

			+ " from " + StringUtils.abbreviate(json, 80));

			return null;

		}

		return object;

	}
}
