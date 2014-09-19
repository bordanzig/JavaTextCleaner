package processor.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import processor.post.PostObject;

public class FileManager {
	
	
	public ArrayList<String> getPostsFromFile(String fileName) throws IOException{
		
		ArrayList<String> response = new ArrayList<String>();
		
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		
		String line;
		while ((line = in.readLine()) != null) {
			response.add(line);
		}
		in.close();
		
		return response;
	}

	public void writeToFile(PostObject post) throws IOException {
		
		BufferedWriter out = new BufferedWriter(new FileWriter(post.getId()+"save.txt"));
		
		out.write(post.getTitle()+"\n");
		out.write(post.getBody()+"\n");
		
		Iterator<String> it = post.getTags().iterator();
		
		while(it.hasNext()){
		    String obj = it.next();
		    out.write(obj+" ");
		}
		
        out.close();
	}

}
