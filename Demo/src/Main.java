import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JTextArea;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Main{
	public static void demo(String file, JTextArea jta){
		String keywordRepoFile = file.replace("\\", "/");
		System.out.println(keywordRepoFile);
		String folder = keywordRepoFile.substring(0, keywordRepoFile.lastIndexOf("/")) + "/";
		System.out.println(folder);
		StringBuffer sb = new StringBuffer("");
		jta.setText(sb.toString());
		
		ArrayList<SearchResult> alResults = new ArrayList<SearchResult>();
		String keyword = "";
		String startTime = "";
		String endTime = "";
		JXLReader jxlReader = null;
		try {
			jxlReader = new JXLReader(folder + "关键词B.xls");
			keyword = jxlReader.getExcelData(0, 1);
			System.out.println("关键字B:" + keyword);
			jta.append("打开 关键词B.xls\n"
					+ "关键字B:" + keyword + "\n");
		}
		catch (BiffException e) {
			System.out.println("读取  关键词B.xls 出错");
			jta.append("读取  关键词B.xls 出错\n");
		} 
		catch (IOException e) {
			System.out.println("文件 关键词B.xls 不存在");
			jta.append("文件 关键词B.xls 不存在\n");
		}
		
		try {	
			jxlReader = new JXLReader(folder + "时间段.xls");
			jta.append("打开 时间段.xls\n");
			startTime = jxlReader.getExcelData(0, 1);
			endTime = jxlReader.getExcelData(1, 1);
		}
		catch (BiffException e) {
			System.out.println("读取  时间段.xls 出错");
			jta.append("读取  时间段.xls 出错\n");
		} 
		catch (IOException e) {
			System.out.println("文件 时间段.xls 不存在");
			jta.append("文件 时间段.xls 不存在\n");
		}
		
		MyDate d1 = new MyDate(startTime, 0);
		MyDate d2 = new MyDate(endTime, 1);	
		System.out.println("时间段为:"+d1.toString() + "到" + d2.toString());
		jta.append("时间段为:"+d1.toString() + "到" + d2.toString() +"\n");
		
		try{
			jxlReader = new JXLReader(keywordRepoFile);
			Map<String, String> m = jxlReader.getExcelDate();
			Set<String> keys = m.keySet();
			SearchEngine se;
			for(Iterator<String> it = keys.iterator(); it.hasNext();){
				String key = it.next();
				if(key.equals("00")) continue;
				String searchWord = m.get(key);
				if(searchWord == null || searchWord.equals("")) continue;
				System.out.println("搜索关键字:" + searchWord);
				jta.append("搜索关键字:" + searchWord + "\n");
				se = new SearchEngine(searchWord, keyword, d1, d2);
				ArrayList<SearchResult> temprs = se.getResult();
				if(temprs != null && temprs.size() > 0)
					alResults.addAll(temprs);
				else{
					alResults.add(new SearchResult(searchWord, 0, ""));
				}
			}
		} catch (BiffException e) {
			System.out.println("读取  关键词库 出错");
			jta.append("读取  关键词库 出错\n");
		} catch (IOException e) {
			System.out.println("文件 " + keywordRepoFile + "不存在");
			jta.append("文件 " + keywordRepoFile + "不存在" + "\n");
		}
		/*
		for(int i = 0 ; i < alResults.size(); i++){
			System.out.println(alResults.get(i));
		}
		*/
		String resultFile = folder + "结果.xls";
		if(saveAll(resultFile, alResults)){
			jta.append("保存到 结果.xls 成功\n运行结束\n");
			System.out.println("保存到 结果.xls 成功\n运行结束");
		}
		else{
			jta.append("保存到 结果.xls 失败\n运行结束\n");
			jta.setCaretPosition(jta.getText().length());
			System.out.println("保存到 结果.xls 失败\n运行结束");
			
		}
	}
	
	public static boolean saveAll(String resultFile, ArrayList<SearchResult> alResults){
		JXLWriter jxl;
		try {
			jxl = new JXLWriter(resultFile);
			jxl.writeAll(alResults);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("文件操作异常");
			return false;
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			System.out.println("文件操作异常");
			return false;
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			System.out.println("文件写入异常");
			return false;
		}
		
	}
}