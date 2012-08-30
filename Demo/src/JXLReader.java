import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class JXLReader
{  
    private Workbook                workbook   = null;
    private HashMap<String, String> mapData    = null;
    private Sheet                   sheet      = null;
    public int                      totalRows  = 0;                                                        
    public int                      totalCells = 0;
                                                         
    /** 
     * ��һ��InputStreamΪ�����Ĺ����� 
     *  
     * @param inputStream 
     * @throws IOException 
     * @throws BiffException 
     */  
    public JXLReader(InputStream inputStream) throws BiffException, IOException  
    {  
        this.workbook = Workbook.getWorkbook(inputStream); 
        this.sheet = this.workbook.getSheet(0);  
        this.getRows();
        this.getCells();
    }   
    /** 
     * ��һ��FileΪ�����Ĺ����� 
     *  
     * @param file 
     * @throws IOException 
     * @throws BiffException 
     */  
    public JXLReader(File file) throws BiffException, IOException  
    {  
        this(new FileInputStream(file));  
    }  
      
    /** 
     * ��һ���ļ�·��path�Ĺ����� 
     *  
     * @param filePath 
     * @throws IOException 
     * @throws BiffException 
     */  
    public JXLReader(String filePath) throws BiffException, IOException  
    {     
        this(new File(filePath));  
    }  
      
    /** 
     * ���������ݷŵ�һ��map��ȥ,keyΪ�кż��к� 
     *  
     * @return 
     */  
    public HashMap<String, String> getExcelDate()  
    {  
        mapData = new HashMap<String, String>();  
        for (int i = 0; i < this.totalRows; i++)  
        {  
            for (int j = 0; j < this.totalCells; j++)  
            {  
                this.mapData.put(i + "" + j, this.getData(j, i));  
            }  
        }  
        return this.mapData;  
    }
    
    public String getExcelData(int cell, int row){
    	try{
    		Cell rs = this.sheet.getCell(cell, row);
    		return rs.getContents();
    		}
    	catch (Exception e) {
			return null;
		}
    }
     
    /** 
     * �õ������� 
     */  
    private void getRows()  
    {  
        this.totalRows = sheet.getRows();  
    }  
      
    /** 
     * �õ������� 
     */  
    private void getCells()  
    {  
        this.totalCells = this.sheet.getColumns();  
    }  
      
    /** 
     * �õ����� 
     *  
     * @param cell 
     * @param row 
     * @return 
     */  
    private String getData(int cell, int row)  
    {  
        Cell rs = this.sheet.getCell(cell, row);  
        return rs.getContents();  
    }  
}  