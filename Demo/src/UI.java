import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
public class UI extends JFrame implements ActionListener{
	private JTextField jtf = new JTextField();
	private JTextArea jta = new JTextArea();
	private JButton jbt = new JButton("Click");
	public UI(){
		super();
		setTitle("Search Demo");
		setSize(500,300);
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("ѡ��ؼ��ʿ�:"), BorderLayout.WEST);
		p.add(jtf, BorderLayout.CENTER);
		jtf.setHorizontalAlignment(JTextField.LEFT);
		p.add(jbt, BorderLayout.EAST);
		setLayout(new BorderLayout());
		add(p, BorderLayout.NORTH);
		jta.setEditable(false);
		add(new JScrollPane(jta), BorderLayout.CENTER);
		jbt.addActionListener(this);
		
		String readme = "�����ļ�Ϊ.xls(MS-excel2003)\n"+
			"�û�ѡ��ؼ��ʿ��ļ�\n" +
			"��������ͬ�ļ���������\"�ؼ���B.xls\"��\"ʱ���.xls\""+
			"(������Ĭ�ϲ���Ҫ������ع���)\n\n\n" +
			"********************************\n"+
			"********************************\n"+
			"CopyRight: Crazy Sheep\n"+
			"mail: notabigpig@gmail.com\n"+
			"********************************\n"+
			"********************************\n";
		jta.setText(readme);
	}

	public static void main(String [] args) {
		UI ui = new UI();
		ui.setLocationRelativeTo(null);
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbt){
			JFileChooser file = new MyChooser (".");
			file.setAcceptAllFileFilterUsed(false);
			file.addChoosableFileFilter(new ExcelFileFilter("xls"));
			int result = file.showOpenDialog(null);
			if(result == JFileChooser.APPROVE_OPTION){
			   String path = file.getSelectedFile().getAbsolutePath();
			   jtf.setText(path);
			   Main.demo(path, jta); //Call the main function
			}
		}
	}
	
	private static class ExcelFileFilter extends FileFilter {
		String ext;
		public ExcelFileFilter(String ext) {
		   this.ext = ext;
		}
		public boolean accept(File file) {
			if (file.isDirectory()){
				return true;
			}
			String fileName = file.getName();
		    int index = fileName.lastIndexOf('.');
		    if (index > 0 && index < fileName.length() - 1){
		       String extension = fileName.substring(index + 1).toLowerCase();
		       if (extension.equals(ext))
		    	   return true;
		       }
			return false;
		}
		public String getDescription() {
			if (ext.equals("xls")){
				return "Microsoft Excel�ļ�(*.xls)";
			}
			return "";
		}
	}
	
	private static class MyChooser extends JFileChooser {
		public MyChooser(String path) {
		   super(path);
		}
		public void approveSelection() {
		   File file = new File(getSelectedFile().getPath());
		   if (file.exists())
			   super.approveSelection();
		   else
			   JOptionPane.showMessageDialog(null, "��ѡ����ļ������ڣ�������ѡ��");
		   }
	}	
}