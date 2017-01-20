import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class EnctryAndUnEntryFrame extends JFrame {
	// 设置JTabbedPane控件，为窗体添加选项卡面板
	private JTabbedPane tabbedPane = new JTabbedPane();

	// 设置标签
	private JLabel[] jlArray = { new JLabel("加密的文件："), new JLabel("保存地址："),
			new JLabel("解密的文件："), new JLabel("保存地址：") };

	// 添加解密面板和加密面板
	private JPanel entryPanel = new JPanel();
	private JPanel untryPanene = new JPanel();

	// 设置一个JButton按钮
	private JButton[] jbArray = { new JButton("选择"), new JButton("选择"),
			new JButton("选择"), new JButton("选择") };

	// 设置两个按钮，分别用于确认加密和确认解密
	private JButton encryButton = new JButton("确认加密");
	private JButton uncryButton = new JButton("确认解密");

	/*
	 * entryTextField 显示要加密文件地址文本框 saveTextField 显示加密后文本的保存地址文本框
	 */
	private JTextField entryTextField = new JTextField();
	private JTextField saveTextField1 = new JTextField();

	private JTextField untryTextField = new JTextField();
	private JTextField saveTextField2 = new JTextField();

	public EnctryAndUnEntryFrame() {
		// 设置JPanel的布局管理器
		entryPanel.setLayout(null);
		untryPanene.setLayout(null);

		// 设置标签的大小位置
		jlArray[0].setBounds(20, 20, 100, 30);
		jlArray[1].setBounds(20, 80, 100, 30);

		jlArray[2].setBounds(20, 20, 100, 30);
		jlArray[3].setBounds(20, 80, 100, 30);

		// 将标签添加到解密面板和加密面板中
		entryPanel.add(jlArray[0]);
		entryPanel.add(jlArray[1]);

		untryPanene.add(jlArray[2]);
		untryPanene.add(jlArray[3]);
		// 将解密面板和加密面板添加到选项卡面板
		tabbedPane.add(entryPanel);
		tabbedPane.add(untryPanene);

		// 为四个按钮添加位置尺寸信息
		jbArray[0].setBounds(290, 20, 70, 30);
		jbArray[1].setBounds(290, 80, 70, 30);

		jbArray[2].setBounds(290, 20, 70, 30);
		jbArray[3].setBounds(290, 80, 70, 30);

		// 为两个加密，解密按钮添加位置尺寸信息
		encryButton.setBounds(130, 150, 140, 30);
		uncryButton.setBounds(130, 150, 140, 30);

		// 设置加密文件地址文本框，解密文件地址文本框，加密后文本的保存地址文本框
		entryTextField.setBounds(110, 20, 170, 30);
		untryTextField.setBounds(110, 20, 170, 30);
		saveTextField1.setBounds(110, 80, 170, 30);
		saveTextField2.setBounds(110, 80, 170, 30);

		// 将4个按钮分别对应载入文件和保存文件
		jbArray[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(e);
			}
		});

		jbArray[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(e);
			}
		});
		jbArray[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load(e);
			}
		});
		jbArray[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save(e);
			}
		});

		encryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encry(entryTextField.getText(), saveTextField1.getText());
			}
		});

		uncryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				untryPanene(untryTextField.getText(), saveTextField2.getText());
			}
		});

		// 将两个按钮添加到加密面板中
		entryPanel.add(jbArray[0]);
		entryPanel.add(jbArray[1]);

		// 将两个按钮添加到解密面板中
		untryPanene.add(jbArray[2]);
		untryPanene.add(jbArray[3]);

		// 将两个进行加密和解密按钮添加到面板中
		entryPanel.add(encryButton);
		untryPanene.add(uncryButton);

		// 将加密文件地址文本框，加密后文本的保存地址文本框放入加密面板中
		entryPanel.add(entryTextField);
		entryPanel.add(saveTextField1);

		// 将解密文件地址文本框，加密后文本的保存地址文本框放入解密面板中
		untryPanene.add(untryTextField);
		untryPanene.add(saveTextField2);

		/*
		 * addTab(String title, Component component) 添加一个由 title 表示，且没有图标的
		 * component
		 */
		tabbedPane.addTab("加密面板", entryPanel);
		tabbedPane.addTab("解密面板", untryPanene);

		// 为窗体添加选项卡面板
		this.add(tabbedPane);

		// 设置窗体的大小，标题，位置，及其可见性
		this.setBounds(100, 100, 400, 250);
		this.setTitle("文件简单加密解密");
		this.setVisible(true);
		this.setResizable(false);
	}

	public void save(ActionEvent e) {
		FileDialog 选择 = new FileDialog(this, "选择", FileDialog.SAVE);
		选择.setVisible(true);
		if (e.getSource() == jbArray[1]) {
			saveTextField1.setText(选择.getDirectory() + 选择.getFile());
		} else if (e.getSource() == jbArray[3]) {
			saveTextField2.setText(选择.getDirectory() + 选择.getFile());
		}
	}

	public void load(ActionEvent e) {
		FileDialog 选择 = new FileDialog(this, "选择", FileDialog.LOAD);
		选择.setVisible(true);
		if (e.getSource() == jbArray[0]) {
			entryTextField.setText(选择.getDirectory() + 选择.getFile());
		} else if (e.getSource() == jbArray[2]) {
			untryTextField.setText(选择.getDirectory() + 选择.getFile());
		}
	}

	// 文件加密处理算法
	public void encry(String str1, String str2) {
		// 将磁盘文件的输入流和输出流对象设置为空
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {

			fis = new FileInputStream(str1);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);

			for (int i = 0; i < buffer.length; i++) { // 循环遍历从流中读取的数组
				int ibt = buffer[i];
				ibt += 100; // 将数组中数据做相加运算
				ibt %= 256;
				buffer[i] = (byte) ibt;
			}

			fos = new FileOutputStream(str2);
			fos.write(buffer);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("加密文件失败");
		} finally {
			// 关闭输入流和输出流对象
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("写出文件失败");
			}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("读取文件失败");
			}

		}
		/*
		 * 显示对话框，提示加密成功，标题为信息提示框
		 */
		JOptionPane.showMessageDialog(this, "加密成功！", "信息提示框",
				JOptionPane.PLAIN_MESSAGE);
	}

	// 文件的解密处理算法
	public void untryPanene(String str1, String str2) {
		// 将磁盘文件的输入流和输出流对象设置为空
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(str1);

			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);

			for (int i = 0; i < buffer.length; i++) {// 循环遍历从流中读取的数组
				int ibt = buffer[i];
				ibt -= 100; // 对从流中读取的数据进行运算处理
				ibt += 256;
				ibt %= 256;
				buffer[i] = (byte) ibt;
			}
			fos = new FileOutputStream(str2);
			fos.write(buffer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解密文件失败");
		} finally {
			// 关闭输入流和输出流对象
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("写出文件失败");
			}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("读取文件失败");
			}

		}
		/*
		 * 显示对话框，提示解密成功，标题为信息提示框
		 */
		JOptionPane.showMessageDialog(this, "解密成功！", "信息提示框",
				JOptionPane.PLAIN_MESSAGE);
	}

	public static void main(String[] args) {
		new EnctryAndUnEntryFrame();
	}

}
