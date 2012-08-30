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
			jxlReader = new JXLReader(folder + "�ؼ���B.xls");
			keyword = jxlReader.getExcelData(0, 1);
			System.out.println("�ؼ���B:" + keyword);
			jta.append("�� �ؼ���B.xls\n"
					+ "�ؼ���B:" + keyword + "\n");
		}
		catch (BiffException e) {
			System.out.println("��ȡ  �ؼ���B.xls ����");
			jta.append("��ȡ  �ؼ���B.xls ����\n");
		} 
		catch (IOException e) {
			System.out.println("�ļ� �ؼ���B.xls ������");
			jta.append("�ļ� �ؼ���B.xls ������\n");
		}
		
		try {	
			jxlReader = new JXLReader(folder + "ʱ���.xls");
			jta.append("�� ʱ���.xls\n");
			startTime = jxlReader.getExcelData(0, 1);
			endTime = jxlReader.getExcelData(1, 1);
		}
		catch (BiffException e) {
			System.out.println("��ȡ  ʱ���.xls ����");
			jta.append("��ȡ  ʱ���.xls ����\n");
		} 
		catch (IOException e) {
			System.out.println("�ļ� ʱ���.xls ������");
			jta.append("�ļ� ʱ���.xls ������\n");
		}
		
		MyDate d1 = new MyDate(startTime, 0);
		MyDate d2 = new MyDate(endTime, 1);	
		System.out.println("ʱ���Ϊ:"+d1.toString() + "��" + d2.toString());
		jta.append("ʱ���Ϊ:"+d1.toString() + "��" + d2.toString() +"\n");
		
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
				System.out.println("�����ؼ���:" + searchWord);
				jta.append("�����ؼ���:" + searchWord + "\n");
				se = new SearchEngine(searchWord, keyword, d1, d2);
				ArrayList<SearchResult> temprs = se.getResult();
				if(temprs != null && temprs.size() > 0)
					alResults.addAll(temprs);
				else{
					alResults.add(new SearchResult(searchWord, 0, ""));
				}
			}
		} catch (BiffException e) {
			System.out.println("��ȡ  �ؼ��ʿ� ����");
			jta.append("��ȡ  �ؼ��ʿ� ����\n");
		} catch (IOException e) {
			System.out.println("�ļ� " + keywordRepoFile + "������");
			jta.append("�ļ� " + keywordRepoFile + "������" + "\n");
		}
		/*
		for(int i = 0 ; i < alResults.size(); i++){
			System.out.println(alResults.get(i));
		}
		*/
		String resultFile = folder + "���.xls";
		if(saveAll(resultFile, alResults)){
			jta.append("���浽 ���.xls �ɹ�\n���н���\n");
			System.out.println("���浽 ���.xls �ɹ�\n���н���");
		}
		else{
			jta.append("���浽 ���.xls ʧ��\n���н���\n");
			jta.setCaretPosition(jta.getText().length());
			System.out.println("���浽 ���.xls ʧ��\n���н���");
			
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
			System.out.println("�ļ������쳣");
			return false;
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			System.out.println("�ļ������쳣");
			return false;
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			System.out.println("�ļ�д���쳣");
			return false;
		}
		
	}
}