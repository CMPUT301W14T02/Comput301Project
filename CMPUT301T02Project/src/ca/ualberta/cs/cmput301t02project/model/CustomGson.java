package ca.ualberta.cs.cmput301t02project.model;

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Provides a Gson object with a custom serializer / desserializer for Bitmaps
 *
 */
public class CustomGson {

	private static Gson gson;

	/**
	 * Constructs the Gson object.
	 */
	private static void constructGson() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Bitmap.class, new BitmapJsonConverter());
		gson = builder.create();
	}
	
	/**
	 * Returns the Gson object
	 * @return The Gson object
	 */
	public static Gson getGson() {
		if(gson == null) {
			constructGson();
		}
		return gson;
	}
	
}
