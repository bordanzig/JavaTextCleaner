package processor.resources;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import processor.post.PostObject;

public class TextToObject {


	public TextToObject() {
	}
	
	public PostObject parseText(String text, String postId) throws JSONException{
		
		JSONObject jsonPost = new JSONObject(text);
		
		return new PostObject(postId,jsonPost.getString("body"),
							jsonPost.getString("title"), 
							getArrayList(jsonPost.getJSONArray("tags")));
	}

	private ArrayList<String> getArrayList(JSONArray jArray) throws JSONException{
		
		ArrayList<String> returnList = new ArrayList<String>();
		for (int i = 0; i < jArray.length(); i++) {
			String val = jArray.getString(i);
			returnList.add(val);
		}
		return returnList;
    }

}
