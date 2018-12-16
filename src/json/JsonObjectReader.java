package json;

import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * This class implements a static method "getJsonObjectFromFile" to 
 * create a JSON object for a given input file.
 * 
 * @author Christian Jeitziner
 * @since 15.12.2018
 *
 */
public class JsonObjectReader {
	/**
	 * Private constructor, class cannot be instantiated.
	 */
	private JsonObjectReader() {}

	/**
	 * Method to create a JSON object from a json file.
	 * 
	 * @param {String} filePath - path of a JSON file.
	 * @return {JsonObject} - initialized from filePath. 
	 */
	public static JsonObject getJsonObjectFromFile(String filePath) {
		if (!Files.exists(Paths.get(filePath))) {
			System.out.println(String.format("File '%s' does not exist", filePath));
			return null;
		}
		
		JsonObject jsonObject = null;
		try (InputStream fis = new FileInputStream(filePath)) {
			jsonObject = JsonObjectReader.getJsonObjectFromInputStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			System.out.println(String.format("Cannot read file '%s'", filePath));			
		}

		return jsonObject;		
	}

	/**
	 * Method to create a JSON object from a input string.
	 * 
	 * @param {String} inputString
	 * @return {JsonObject} - initialized from inputString.
	 */
	public static JsonObject getJsonObjectFromString(String inputString) {
		InputStream is = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
		return JsonObjectReader.getJsonObjectFromInputStream(is);		
	}

	/**
	 * Private helper method to create a JSON object from a input stream.
	 * 
	 * @param {InputStream} is - 
	 * @return {JsonObject}
	 */
	private static JsonObject getJsonObjectFromInputStream(InputStream is) {
		JsonReader jsonReader = Json.createReader(is);
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		return jsonObject;
	}
		
}
