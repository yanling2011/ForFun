
public class SearchResult {
	String keyWord;
	int rank;
	String url;
	
	public SearchResult(String keyWord, int rate, String url){
		this.keyWord = keyWord;
		this.rank = rate;
		this.url = url;
	}
	
	public String getKeyWord(){
		return keyWord;
	}
	
	public int getRank(){
		return rank;
	}
	
	public String getUrl(){
		return url;
	}
	
	public String toString(){
		return keyWord + " " + rank + " " + url;
	}
}
