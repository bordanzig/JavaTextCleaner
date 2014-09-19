package processor.post;

import java.util.ArrayList;
import java.util.Iterator;

import processor.resources.TextCleaner;


public class PostObject {
	
	private String body;
	public String getBody() {
		return body;
	}

	public String getTitle() {
		return title;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public String getId() {
		return id;
	}

	private String title;
	private ArrayList<String> tags;
	private String id;

	public PostObject(String id, String body, String title, ArrayList<String> tags) {
		this.id = id;
		this.body = body;
		this.title = title;
		this.tags = tags;
	}
	
	public void cleanStrings(){
		
		TextCleaner cleaner = new TextCleaner();
		
		/* TITLE CLEANING */
		this.title = cleaner.removeJumps(title);
		this.title = cleaner.removeBBCode(title);
		this.title = cleaner.removeAccent(title);
		this.title = cleaner.removeNonAscii(title);
		
		/* BODY CLEANING */
		this.body = cleaner.removeJumps(body);
		this.body = cleaner.removeBBCode(body);
		this.body = cleaner.removeURL(body);
		this.body = cleaner.removeAccent(body);
		this.body = cleaner.removeNonAscii(body);
		
		
		/* TAGS CLEANING */
		Iterator<String> it = this.tags.iterator();
		ArrayList<String> cleanTags = new ArrayList<String>();
		
		while (it.hasNext()){
			String cleanString = cleaner.removeBBCode(it.next());
			cleanString = cleaner.removeAccent(cleanString);
			cleanString = cleaner.removeNonAscii(cleanString);
			cleanTags.add(cleanString);
		}
		
		this.tags = cleanTags;
		
	}
	

}
