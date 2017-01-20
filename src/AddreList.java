import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class AddreList extends JFrame implements ActionListener {
	// 获取FILe对象封装的路径下的全部文件和文件夹
	private File file = new File("E:" + File.separator + "information.txt");
	private JPanel jPanel = new JPanel();
	private JPanel panel = new JPanel();
	private JTextField nametextField = new JTextField();
	private JTextField emailtextField = new JTextField();
	private JTextField phonetextField = new JTextField();
	private JLabel[] jlArray = { new JLabel("输入姓名"), new JLabel("输入EMAIL"),
			new JLabel("输入电话"), new JLabel("单击录入") };
	private JButton button = new JButton("录入");
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem reveal;
	private JMenuItem kinescope;
	
	public static PersonalInformationCheck pic  = new PersonalInformationCheck();
	public AddreList() {
		// 设置布局管理器
		jPanel.setLayout(null);
		panel.setLayout(null);

		// 设置标签的大小位置,同时将标签添加进录入面板中
		for (int i = 0; i < jlArray.length; i++) {
			jlArray[i].setBounds(20, 20 + i * 40, 100, 30);
			panel.add(jlArray[i]);
		}

		// 设置输入姓名文本框，输入EMAIL文本框，输入电话文本框
		nametextField.setBounds(110, 20, 200, 30);
		emailtextField.setBounds(110, 60, 200, 30);
		phonetextField.setBounds(110, 100, 200, 30);

		// 将输入姓名文本框，输入EMAIL文本框，输入电话文本框添加进录入面板中
		panel.add(nametextField);
		panel.add(emailtextField);
		panel.add(phonetextField);

		// 设置标签的大小位置，以及事件监听器，同时将录入按钮添加进录入面板中
		button.setBounds(110, 140, 200, 30);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kinbuttonActionPerformed(e);
			}
		});
		panel.add(button);

		// 添加菜单栏
		menuBar = new JMenuBar();
		// 在窗体上添加菜单栏
		this.setJMenuBar(menuBar);
		// 添加菜单项 "文件"
		menu = new JMenu("文件");
		// 在窗体上添加菜单项
		menuBar.add(menu);
		// 添加子菜单项"显示"
		reveal = new JMenuItem("显示");
		// 在菜单项中添加子菜单项
		menu.add(reveal);
		// 为显示菜单添加事件及监听机制
		reveal.addActionListener(this);

		// 添加菜单项"录入"
		kinescope = new JMenuItem("录入");
		// 在菜单项中添加子菜单项
		menu.add(kinescope);
		// 为录入菜单添加事件及监听机制
		kinescope.addActionListener(this);

		// 将显示面板和录入面板添加进窗体中
		this.add(jPanel);
		this.add(panel);

		// 设置窗体的大小，标题，位置，及其可见性
		this.setBounds(100, 100, 360, 300);
		this.setTitle("个人通讯录");
		this.setVisible(true);
		this.setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {
		BufferedReader in = null;
		if (e.getSource() == reveal) { // 如果用户单击的是显示菜单项
			try {
				getContentPane().remove(panel);
				jPanel.setLayout(null); // 设置窗体布局
				jPanel.setBounds(0, 0, 360, 300);
				
				JTextArea jtextarea = new JTextArea(); // 创建文本域对象
				
				//将文本区作为被滚动控件创建滚动窗体
				JScrollPane jscrollpane = new  JScrollPane(jtextarea);
				
				//设置JScrollPane的大小位置
				jscrollpane.setBounds(20,0,320,220);
			
				jtextarea.setLineWrap(false);
				getContentPane().add(jPanel); // 窗体中添加面板
				
				//将JScrollPane容器添加进面板中
				jPanel.add(jscrollpane);
				in = new BufferedReader(new FileReader(file)); // 创建BufferedReader对象
				String name = null;
				int number = 1;
				while ((name = in.readLine()) != null) { // 循环从文件中读数据
					jtextarea.append("\n" + number + "、 " + name); // 将读取数据显示在文本域中
					name = new String(name);
					number++;
				}
				repaint();
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new RuntimeException("写入文件失败");
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (Exception e2) {
					throw new RuntimeException("读取关闭失败");
				}
			}
		}

		if (e.getSource() == kinescope) { // 如果用户单击录入菜单项
			getContentPane().remove(jPanel); // 将面板移除窗体
			getContentPane().add(panel);
			repaint(); // 窗体重绘
		}
	}

	private void kinbuttonActionPerformed(java.awt.event.ActionEvent evt) {
		BufferedWriter out = null;
		try {
			if (nametextField.getText().equals("")
					|| (emailtextField.getText().equals(""))
					|| (phonetextField.getText().equals(""))) {// 如果用户没有将信息输入完整
				// 给出提示信息
				JOptionPane.showMessageDialog(this, "请输入完整内容", "信息提示框",
						JOptionPane.WARNING_MESSAGE);
				return; // 退出程序
			}
			if (!pic.checkName((nametextField.getText()))){
				JOptionPane.showMessageDialog(this, "此人已加入通讯录", "信息提示框",
						JOptionPane.WARNING_MESSAGE);
				return; // 退出程序
			}
			if (!pic.checkEmail(emailtextField.getText())){
				JOptionPane.showMessageDialog(this, "EMAIL地址不正确", "信息提示框",
						JOptionPane.WARNING_MESSAGE);
				return; // 退出程序
			}
			if (!pic.checkPhone(phonetextField.getText())){
				JOptionPane.showMessageDialog(this, "电话号码不正确", "信息提示框",
						JOptionPane.WARNING_MESSAGE);
				return; // 退出程序
			}
			if (!file.exists()) // 如果文件不存在
				file.createNewFile(); // 新建文件
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true))); // 创建BufferedWriter对象
			out.write("姓名：" + nametextField.getText() + "， "); // 向文件中写内容
			out.write("邮箱：" + emailtextField.getText() + "， ");
			out.write("电话：" + phonetextField.getText());
			out.newLine(); // 新建一行
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			try{
				if (out != null){
					out.close();
				}
			}catch(Exception e2){
				throw new RuntimeException("写入关闭失败");
			}
		}

	}
	

	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Throwable e){
			e.printStackTrace();
		}
		new AddreList();
	}

}
