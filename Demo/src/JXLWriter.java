import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class JXLWriter {
	private WritableWorkbook  workbook   = null;
	private WritableSheet sheet = null;
	
	public JXLWriter(File file) throws IOException{
		workbook = Workbook.createWorkbook(file);
		sheet = workbook.createSheet("first", 0);
	}
	
	public JXLWriter(String filePath) throws IOException{
		this(new File(filePath));
	}
	
	
	private void writeItem(int cell, int row, String data) throws RowsExceededException, WriteException{
		Label label = new Label(cell, row, data);
		sheet.addCell(label);
	}
	
	public void writeAll(ArrayList<SearchResult> alResults) throws RowsExceededException, WriteException, IOException{
		writeItem(0, 0, "¹Ø¼ü´Ê");
		writeItem(1, 0, "ÅÅÃû");
		writeItem(2, 0, "Á´½Ó");
		for(int i = 0; i < alResults.size(); i++){
			SearchResult sr = alResults.get(i);
			String keyword = sr.getKeyWord();
			String rank;
			int rankNo = sr.getRank();
			if(rankNo == 0) rank = "";
			else rank = Integer.toString(rankNo);
			String url = sr.getUrl();
			
			writeItem(0, i+1, keyword);
			writeItem(1, i+1, rank);
			writeItem(2, i+1, url);
			
		}
		workbook.write();   
		workbook.close();
	}
	
	
    
}
