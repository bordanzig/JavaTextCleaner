package processor.resources;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextCleaner {
	

	public String removeAccent(String str) {
		
	    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    String response = pattern.matcher(nfdNormalizedString).replaceAll("");
	    
	    return response.toLowerCase().replaceAll("\\s+", " ").trim();
	}
	
	public String removeURL(String str){
		
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        int i = 0;
        
        while (m.find()) {
            str = str.replaceAll(m.group(i),"").replaceAll("\\s+", " ").trim();
            i++;
        }
       
        return str.toLowerCase();
    }
	
	public String removeBBCode(String str) {
		
	    String nobbstr = str.replaceAll("\\[.*?\\]", "");
	    
	    return nobbstr.toLowerCase().replaceAll("\\s+", " ").trim();
	}

	public String removeNonAscii(String str) {
		
		String noascstr = str.replaceAll("[^a-zA-Z0-9\\s]+"," ");;
		
		return noascstr.toLowerCase().replaceAll("\\s+", " ").trim();
	}

	public String removeJumps(String str) {
		String nojumpstr = str.replaceAll("\n"," ");
		nojumpstr = nojumpstr.replaceAll("\r"," ");
		return nojumpstr.toLowerCase().replaceAll("\\s+", " ").trim();
	}

}
