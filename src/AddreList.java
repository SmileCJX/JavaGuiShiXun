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
	// ��ȡFILe�����װ��·���µ�ȫ���ļ����ļ���
	private File file = new File("E:" + File.separator + "information.txt");
	private JPanel jPanel = new JPanel();
	private JPanel panel = new JPanel();
	private JTextField nametextField = new JTextField();
	private JTextField emailtextField = new JTextField();
	private JTextField phonetextField = new JTextField();
	private JLabel[] jlArray = { new JLabel("��������"), new JLabel("����EMAIL"),
			new JLabel("����绰"), new JLabel("����¼��") };
	private JButton button = new JButton("¼��");
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem reveal;
	private JMenuItem kinescope;
	
	public static PersonalInformationCheck pic  = new PersonalInformationCheck();
	public AddreList() {
		// ���ò��ֹ�����
		jPanel.setLayout(null);
		panel.setLayout(null);

		// ���ñ�ǩ�Ĵ�Сλ��,ͬʱ����ǩ��ӽ�¼�������
		for (int i = 0; i < jlArray.length; i++) {
			jlArray[i].setBounds(20, 20 + i * 40, 100, 30);
			panel.add(jlArray[i]);
		}

		// �������������ı�������EMAIL�ı�������绰�ı���
		nametextField.setBounds(110, 20, 200, 30);
		emailtextField.setBounds(110, 60, 200, 30);
		phonetextField.setBounds(110, 100, 200, 30);

		// �����������ı�������EMAIL�ı�������绰�ı�����ӽ�¼�������
		panel.add(nametextField);
		panel.add(emailtextField);
		panel.add(phonetextField);

		// ���ñ�ǩ�Ĵ�Сλ�ã��Լ��¼���������ͬʱ��¼�밴ť��ӽ�¼�������
		button.setBounds(110, 140, 200, 30);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kinbuttonActionPerformed(e);
			}
		});
		panel.add(button);

		// ��Ӳ˵���
		menuBar = new JMenuBar();
		// �ڴ�������Ӳ˵���
		this.setJMenuBar(menuBar);
		// ��Ӳ˵��� "�ļ�"
		menu = new JMenu("�ļ�");
		// �ڴ�������Ӳ˵���
		menuBar.add(menu);
		// ����Ӳ˵���"��ʾ"
		reveal = new JMenuItem("��ʾ");
		// �ڲ˵���������Ӳ˵���
		menu.add(reveal);
		// Ϊ��ʾ�˵�����¼�����������
		reveal.addActionListener(this);

		// ��Ӳ˵���"¼��"
		kinescope = new JMenuItem("¼��");
		// �ڲ˵���������Ӳ˵���
		menu.add(kinescope);
		// Ϊ¼��˵�����¼�����������
		kinescope.addActionListener(this);

		// ����ʾ����¼�������ӽ�������
		this.add(jPanel);
		this.add(panel);

		// ���ô���Ĵ�С�����⣬λ�ã�����ɼ���
		this.setBounds(100, 100, 360, 300);
		this.setTitle("����ͨѶ¼");
		this.setVisible(true);
		this.setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {
		BufferedReader in = null;
		if (e.getSource() == reveal) { // ����û�����������ʾ�˵���
			try {
				getContentPane().remove(panel);
				jPanel.setLayout(null); // ���ô��岼��
				jPanel.setBounds(0, 0, 360, 300);
				
				JTextArea jtextarea = new JTextArea(); // �����ı������
				
				//���ı�����Ϊ�������ؼ�������������
				JScrollPane jscrollpane = new  JScrollPane(jtextarea);
				
				//����JScrollPane�Ĵ�Сλ��
				jscrollpane.setBounds(20,0,320,220);
			
				jtextarea.setLineWrap(false);
				getContentPane().add(jPanel); // ������������
				
				//��JScrollPane������ӽ������
				jPanel.add(jscrollpane);
				in = new BufferedReader(new FileReader(file)); // ����BufferedReader����
				String name = null;
				int number = 1;
				while ((name = in.readLine()) != null) { // ѭ�����ļ��ж�����
					jtextarea.append("\n" + number + "�� " + name); // ����ȡ������ʾ���ı�����
					name = new String(name);
					number++;
				}
				repaint();
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new RuntimeException("д���ļ�ʧ��");
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (Exception e2) {
					throw new RuntimeException("��ȡ�ر�ʧ��");
				}
			}
		}

		if (e.getSource() == kinescope) { // ����û�����¼��˵���
			getContentPane().remove(jPanel); // ������Ƴ�����
			getContentPane().add(panel);
			repaint(); // �����ػ�
		}
	}

	private void kinbuttonActionPerformed(java.awt.event.ActionEvent evt) {
		BufferedWriter out = null;
		try {
			if (nametextField.getText().equals("")
					|| (emailtextField.getText().equals(""))
					|| (phonetextField.getText().equals(""))) {// ����û�û�н���Ϣ��������
				// ������ʾ��Ϣ
				JOptionPane.showMessageDialog(this, "��������������", "��Ϣ��ʾ��",
						JOptionPane.WARNING_MESSAGE);
				return; // �˳�����
			}
			if (!pic.checkName((nametextField.getText()))){
				JOptionPane.showMessageDialog(this, "�����Ѽ���ͨѶ¼", "��Ϣ��ʾ��",
						JOptionPane.WARNING_MESSAGE);
				return; // �˳�����
			}
			if (!pic.checkEmail(emailtextField.getText())){
				JOptionPane.showMessageDialog(this, "EMAIL��ַ����ȷ", "��Ϣ��ʾ��",
						JOptionPane.WARNING_MESSAGE);
				return; // �˳�����
			}
			if (!pic.checkPhone(phonetextField.getText())){
				JOptionPane.showMessageDialog(this, "�绰���벻��ȷ", "��Ϣ��ʾ��",
						JOptionPane.WARNING_MESSAGE);
				return; // �˳�����
			}
			if (!file.exists()) // ����ļ�������
				file.createNewFile(); // �½��ļ�
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true))); // ����BufferedWriter����
			out.write("������" + nametextField.getText() + "�� "); // ���ļ���д����
			out.write("���䣺" + emailtextField.getText() + "�� ");
			out.write("�绰��" + phonetextField.getText());
			out.newLine(); // �½�һ��
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			try{
				if (out != null){
					out.close();
				}
			}catch(Exception e2){
				throw new RuntimeException("д��ر�ʧ��");
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
