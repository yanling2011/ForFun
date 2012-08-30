import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

public class SearchEngine{
	String searchWord;
	String urlString;
	MyDate startTime;
	MyDate endTime;
	String keyWord;
	
	public SearchEngine(String searchWord, String keyWord, MyDate d1, MyDate d2) {
		this.urlString = "http://www.baidu.com/s?wd="+URLEncoder.encode(searchWord);
		this.searchWord = searchWord;
		this.keyWord = keyWord;
		this.startTime = d1;
		this.endTime = d2;
	}
	public ArrayList<SearchResult> getResult(){
		URL url = null;
		HttpURLConnection url_con = null;
		ArrayList<SearchResult> results = null;
		try {
			url = new URL(urlString);
			url_con = (HttpURLConnection) url.openConnection();
//			System.out.println("------------------------>open succeed!");
//			System.out.println("------------------------>It is a new visition!");
			InputStream in = url_con.getInputStream();
			InputStreamReader ird = new InputStreamReader(in);
			BufferedReader rd = new BufferedReader(ird);
			StringBuffer sbHtml = new StringBuffer("");
			String temp;
			while((temp = rd.readLine()) != null){
				sbHtml.append(temp);
				sbHtml.append("\n");
			}
		//	System.out.println(sbHtml);
			results = parse(sbHtml.toString(), searchWord, keyWord, startTime, endTime);
			in.close();
			ird.close();
			rd.close();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 finally{
			 if(url_con != null){
				 url_con.disconnect();
//				 System.out.println("-------------------------->end");
//				 System.out.println("-------------------------->end");
//				 System.out.println("-------------------------->end");
			 }
		 }
		 return results;
	}
	public static ArrayList<SearchResult> parse(String htmlcode, String searchWord, 
			String keyWord, MyDate startTime, MyDate endTime){
		ArrayList<SearchResult> results = new ArrayList<SearchResult>();
		Parser parser = Parser.createParser(htmlcode, "GBK");
		HtmlPage page = new HtmlPage(parser);
		try
		{ 
			parser.visitAllNodesWith(page);
		}
		catch(ParserException e1)
		{
			e1.printStackTrace();
		}
		NodeList nodelist = page.getBody();
		NodeFilter filterTag = new TagNameFilter("table");
		NodeFilter filterClass = new HasAttributeFilter("class", "result");
//		NodeFilter filterClass2 = new HasAttributeFilter("class", "result-op");

		nodelist = nodelist.extractAllNodesThatMatch(
				new AndFilter(filterTag, 
						filterClass), true);
		

		for(int i = 0; i < nodelist.size(); i++){
			TableTag tableTag = (TableTag) nodelist.elementAt(i);
			int id = Integer.parseInt(tableTag.getAttribute("id"));
			
			NodeList td = tableTag.getChild(0).getFirstChild().getChildren();
			String infoString = td.asString();
			
			//Exclude all Ads
			if(infoString.contains("ÍÆ¹ãÁ´½Ó")){
				continue;
			}
//			System.out.println("here");
			//GetDate
			String patDate = "\\d+-\\d+-\\d+";
			Pattern p = Pattern.compile(patDate);
			Matcher matcher = p.matcher(infoString);
			MyDate webDate;
			if(matcher.find()){
				webDate = new MyDate(matcher.group());
			}
			else webDate = null;
//			System.out.println(webDate);
//			System.out.println(startTime);
//			System.out.println(endTime);
			if(webDate != null && 
					(webDate.compareTo(startTime) < 0
					  || webDate.compareTo(endTime) > 0)){
				continue;
			}
			
			//FindKeyword
			if(keyWord != null && !keyWord.equals("") && !infoString.contains(keyWord)){
//				System.out.println("keyword:"+keyWord);
				continue;
			}
			
			LinkTag link = (LinkTag)td.elementAt(0).getFirstChild();
			String href = link.getAttribute("href");
			
			
			
//			System.out.println("Id = " + id);
//			System.out.println(infoString);
//			System.out.println(href);
//			System.out.println(webDate);
			
			SearchResult sr = new SearchResult(searchWord, id, href);
			results.add(sr);
		}
		return results;
	}

	
}
