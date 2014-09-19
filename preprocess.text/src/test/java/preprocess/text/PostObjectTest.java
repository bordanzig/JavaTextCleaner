package preprocess.text;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import processor.post.PostObject;


public class PostObjectTest {

	@Test
	public void cleanTextBBCODETest(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("tExT");
		tags.add("Te[x]Xt");
		PostObject obj = new PostObject("123","I[b] am[/b] body","This Is Title", tags);
		
		obj.cleanStrings();
		
		assertTrue(obj.getBody().equals("i am body"));
		assertTrue(obj.getTitle().equals("this is title"));
		Iterator<String> it = obj.getTags().iterator();
		while (it.hasNext()){
			assertTrue(it.next().equals("text"));
		}
	}
	
	@Test
	public void cleanTextURLTest(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("tExT");
		PostObject obj = new PostObject("123","I am body http://static.riverplate.com","This Is Title", tags);
		
		obj.cleanStrings();
		
		assertTrue(obj.getBody().equals("i am body"));
	}
	
	@Test
	public void cleanTextAccentsTest(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("ñ");
		PostObject obj = new PostObject("123","nñìñô hola","Ûlóoña", tags);
		
		obj.cleanStrings();
		
		assertTrue(obj.getBody().equals("nnino hola"));
		assertTrue(obj.getTitle().equals("uloona"));
		Iterator<String> it = obj.getTags().iterator();
		while (it.hasNext()){
			assertTrue(it.next().equals("n"));
		}
	}

	@Test
	public void cleanTextSpecialCharactersTest(){
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("n!$");
		tags.add("n-.,");
		PostObject obj = new PostObject("123","h'ola pl-%$· 213'3'4-,.*","*++mas')s9(", tags);
		
		obj.cleanStrings();
		
		assertTrue(obj.getBody().equals("h ola pl 213 3 4"));
		assertTrue(obj.getTitle().equals("mas s9"));
		Iterator<String> it = obj.getTags().iterator();
		while (it.hasNext()){
			assertTrue(it.next().equals("n"));
		}
	}
	
}
