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
	// ����JTabbedPane�ؼ���Ϊ�������ѡ����
	private JTabbedPane tabbedPane = new JTabbedPane();

	// ���ñ�ǩ
	private JLabel[] jlArray = { new JLabel("���ܵ��ļ���"), new JLabel("�����ַ��"),
			new JLabel("���ܵ��ļ���"), new JLabel("�����ַ��") };

	// ��ӽ������ͼ������
	private JPanel entryPanel = new JPanel();
	private JPanel untryPanene = new JPanel();

	// ����һ��JButton��ť
	private JButton[] jbArray = { new JButton("ѡ��"), new JButton("ѡ��"),
			new JButton("ѡ��"), new JButton("ѡ��") };

	// ����������ť���ֱ�����ȷ�ϼ��ܺ�ȷ�Ͻ���
	private JButton encryButton = new JButton("ȷ�ϼ���");
	private JButton uncryButton = new JButton("ȷ�Ͻ���");

	/*
	 * entryTextField ��ʾҪ�����ļ���ַ�ı��� saveTextField ��ʾ���ܺ��ı��ı����ַ�ı���
	 */
	private JTextField entryTextField = new JTextField();
	private JTextField saveTextField1 = new JTextField();

	private JTextField untryTextField = new JTextField();
	private JTextField saveTextField2 = new JTextField();

	public EnctryAndUnEntryFrame() {
		// ����JPanel�Ĳ��ֹ�����
		entryPanel.setLayout(null);
		untryPanene.setLayout(null);

		// ���ñ�ǩ�Ĵ�Сλ��
		jlArray[0].setBounds(20, 20, 100, 30);
		jlArray[1].setBounds(20, 80, 100, 30);

		jlArray[2].setBounds(20, 20, 100, 30);
		jlArray[3].setBounds(20, 80, 100, 30);

		// ����ǩ��ӵ��������ͼ��������
		entryPanel.add(jlArray[0]);
		entryPanel.add(jlArray[1]);

		untryPanene.add(jlArray[2]);
		untryPanene.add(jlArray[3]);
		// ���������ͼ��������ӵ�ѡ����
		tabbedPane.add(entryPanel);
		tabbedPane.add(untryPanene);

		// Ϊ�ĸ���ť���λ�óߴ���Ϣ
		jbArray[0].setBounds(290, 20, 70, 30);
		jbArray[1].setBounds(290, 80, 70, 30);

		jbArray[2].setBounds(290, 20, 70, 30);
		jbArray[3].setBounds(290, 80, 70, 30);

		// Ϊ�������ܣ����ܰ�ť���λ�óߴ���Ϣ
		encryButton.setBounds(130, 150, 140, 30);
		uncryButton.setBounds(130, 150, 140, 30);

		// ���ü����ļ���ַ�ı��򣬽����ļ���ַ�ı��򣬼��ܺ��ı��ı����ַ�ı���
		entryTextField.setBounds(110, 20, 170, 30);
		untryTextField.setBounds(110, 20, 170, 30);
		saveTextField1.setBounds(110, 80, 170, 30);
		saveTextField2.setBounds(110, 80, 170, 30);

		// ��4����ť�ֱ��Ӧ�����ļ��ͱ����ļ�
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

		// ��������ť��ӵ����������
		entryPanel.add(jbArray[0]);
		entryPanel.add(jbArray[1]);

		// ��������ť��ӵ����������
		untryPanene.add(jbArray[2]);
		untryPanene.add(jbArray[3]);

		// ���������м��ܺͽ��ܰ�ť��ӵ������
		entryPanel.add(encryButton);
		untryPanene.add(uncryButton);

		// �������ļ���ַ�ı��򣬼��ܺ��ı��ı����ַ�ı��������������
		entryPanel.add(entryTextField);
		entryPanel.add(saveTextField1);

		// �������ļ���ַ�ı��򣬼��ܺ��ı��ı����ַ�ı��������������
		untryPanene.add(untryTextField);
		untryPanene.add(saveTextField2);

		/*
		 * addTab(String title, Component component) ���һ���� title ��ʾ����û��ͼ���
		 * component
		 */
		tabbedPane.addTab("�������", entryPanel);
		tabbedPane.addTab("�������", untryPanene);

		// Ϊ�������ѡ����
		this.add(tabbedPane);

		// ���ô���Ĵ�С�����⣬λ�ã�����ɼ���
		this.setBounds(100, 100, 400, 250);
		this.setTitle("�ļ��򵥼��ܽ���");
		this.setVisible(true);
		this.setResizable(false);
	}

	public void save(ActionEvent e) {
		FileDialog ѡ�� = new FileDialog(this, "ѡ��", FileDialog.SAVE);
		ѡ��.setVisible(true);
		if (e.getSource() == jbArray[1]) {
			saveTextField1.setText(ѡ��.getDirectory() + ѡ��.getFile());
		} else if (e.getSource() == jbArray[3]) {
			saveTextField2.setText(ѡ��.getDirectory() + ѡ��.getFile());
		}
	}

	public void load(ActionEvent e) {
		FileDialog ѡ�� = new FileDialog(this, "ѡ��", FileDialog.LOAD);
		ѡ��.setVisible(true);
		if (e.getSource() == jbArray[0]) {
			entryTextField.setText(ѡ��.getDirectory() + ѡ��.getFile());
		} else if (e.getSource() == jbArray[2]) {
			untryTextField.setText(ѡ��.getDirectory() + ѡ��.getFile());
		}
	}

	// �ļ����ܴ����㷨
	public void encry(String str1, String str2) {
		// �������ļ������������������������Ϊ��
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {

			fis = new FileInputStream(str1);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);

			for (int i = 0; i < buffer.length; i++) { // ѭ�����������ж�ȡ������
				int ibt = buffer[i];
				ibt += 100; // ���������������������
				ibt %= 256;
				buffer[i] = (byte) ibt;
			}

			fos = new FileOutputStream(str2);
			fos.write(buffer);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�����ļ�ʧ��");
		} finally {
			// �ر������������������
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("д���ļ�ʧ��");
			}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("��ȡ�ļ�ʧ��");
			}

		}
		/*
		 * ��ʾ�Ի�����ʾ���ܳɹ�������Ϊ��Ϣ��ʾ��
		 */
		JOptionPane.showMessageDialog(this, "���ܳɹ���", "��Ϣ��ʾ��",
				JOptionPane.PLAIN_MESSAGE);
	}

	// �ļ��Ľ��ܴ����㷨
	public void untryPanene(String str1, String str2) {
		// �������ļ������������������������Ϊ��
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(str1);

			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);

			for (int i = 0; i < buffer.length; i++) {// ѭ�����������ж�ȡ������
				int ibt = buffer[i];
				ibt -= 100; // �Դ����ж�ȡ�����ݽ������㴦��
				ibt += 256;
				ibt %= 256;
				buffer[i] = (byte) ibt;
			}
			fos = new FileOutputStream(str2);
			fos.write(buffer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�����ļ�ʧ��");
		} finally {
			// �ر������������������
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("д���ļ�ʧ��");
			}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("��ȡ�ļ�ʧ��");
			}

		}
		/*
		 * ��ʾ�Ի�����ʾ���ܳɹ�������Ϊ��Ϣ��ʾ��
		 */
		JOptionPane.showMessageDialog(this, "���ܳɹ���", "��Ϣ��ʾ��",
				JOptionPane.PLAIN_MESSAGE);
	}

	public static void main(String[] args) {
		new EnctryAndUnEntryFrame();
	}

}
