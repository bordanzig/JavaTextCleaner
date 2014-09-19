package processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import processor.file.FileManager;
import processor.post.PostObject;
import processor.resources.TextToObject;
import processor.url.URLreader;

public class ProcessorMain {
	
	private String ids_file;
	private URLreader ureader;
	private FileManager fm;
	private String urlPrefix;
	private TextToObject jsonMediator;

	public ProcessorMain(String file) {
		this.ids_file = file;
		this.ureader = new URLreader();
		this.fm = new FileManager();
		this.jsonMediator = new TextToObject();
		this.urlPrefix = "http://api.taringa.net/post/view/";
	}
	public ProcessorMain() {
		this.ids_file = "id_test";
		this.ureader = new URLreader();
		this.fm = new FileManager();
		this.jsonMediator = new TextToObject();
		this.urlPrefix = "http://api.taringa.net/post/view/";
	}


	public static void main(String[] args) {
		ProcessorMain main = new ProcessorMain();
		
			main.run();

	}

	private void run() {
		try {
			ArrayList<String> postsToAnalize = fm.getPostsFromFile(ids_file);
			
			Iterator<String> it = postsToAnalize.iterator();
			
			while (it.hasNext()){
				String postID = it.next();
			
				String url = ureader.getText(urlPrefix+postID);
				PostObject post = jsonMediator.parseText(url, postID);
				
				post.cleanStrings();
				
				fm.writeToFile(post);	
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
