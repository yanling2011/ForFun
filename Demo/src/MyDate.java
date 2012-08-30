import java.util.Date;


public class MyDate implements Comparable<MyDate>{
	private int year;
	private int month;
	private int day;
	public MyDate(int year, int month, int day){
		this.year = year;
		this.month = month;
		this.day = day;
	}
	/*
	 * if flag = 0 it's the start date
	 * if flag = 1 it's the end date
	 */
	public MyDate(String str, int flag){
		if(str != null && !str.equals("") && str.matches("\\d+-\\d+-\\d+")){
			String[] tempStrings = str.split("-");
			if(tempStrings[0].matches("\\d\\d")){
				this.year = Integer.parseInt("20" + tempStrings[0]);
			}
			else this.year = Integer.parseInt(tempStrings[0]);
			this.month = Integer.parseInt(tempStrings[1]);
			this.day = Integer.parseInt(tempStrings[2]);
		}
		else if(flag == 1){
			Date date = new Date();
			this.year = date.getYear();
			this.month = date.getMonth();
			this.day = date.getDay();
		}
		else{
			this.year = 0;
			this.month = 0;
			this.day = 0;
		}
	}
	
	public MyDate(String str){
		if(str != null && !str.equals("") && str.matches("\\d+-\\d+-\\d+")){
			String[] tempStrings = str.split("-");
			if(tempStrings[0].matches("\\d\\d")){
				this.year = Integer.parseInt("20" + tempStrings[0]);
			}
			else this.year = Integer.parseInt(tempStrings[0]);
			this.month = Integer.parseInt(tempStrings[1]);
			this.day = Integer.parseInt(tempStrings[2]);
		}
		else{
			this.year = 0;
			this.month = 0;
			this.day = 0;
		}
	}
	
	public String toString(){
		return year+"-"+month+"-"+day;
	}
	
	public int compareTo(MyDate d2){
		int t1 = this.year*12*30+this.month*30+this.day;
		int t2 = d2.year*12*30+d2.month*30+d2.day;
		if( t1 > t2) return 1;
		else if(t1 == t2) return 0;
		return -1;
	}
}
