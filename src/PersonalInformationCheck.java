import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//����������ݽ��м��
public class PersonalInformationCheck {
	// �ж�ĳ�˵���Ϣ�Ƿ��Ѿ���д��ͨѶ¼��
	public boolean checkName(String name) {
		boolean flag = true;
		BufferedReader bfr = null;
		try {
			bfr = new BufferedReader(new FileReader("E:" + File.separator
					+ "information.txt"));
			String line = null;
			// ��ȡÿһ�У�ֱ�������ļ��Ľ���
			while ((line = bfr.readLine()) != null) {
				String[] str = line.split(" ");
				// ��ȡ�ַ����е������ַ���
				if (str[0].substring(3, str[0].length() - 1).equals(name)) {
					flag = false;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("�û��ļ���ȡʧ��");
		} finally {
			try {
				if (bfr != null) {
					bfr.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("�û��ļ���ȡ�ر���Դʧ��");
			}

		}
		return flag;
	}

	// ��������EMAIL��ַ�Ƿ���Ϲ淶
	public boolean checkEmail(String email) {
		/*
		boolean flag = true; // EMAIL��ַ�������@����
		if (!email.contains("@")) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
		*/
		/*
		 * ʹ��������ʽ�������Ƿ���Ϲ淶��������Ӧ��ƥ��
		 */
		
		boolean flag = true;
		Pattern pattern = Pattern
				.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	
	}

	// �ж�����ĵ绰�Ƿ���Ϲ淶
	public boolean checkPhone(String phone) {
		boolean flag = true;
		// �绰�����Ƿ�11λ����������11λ��Ϊ��
		if (phone.length() != 11) {
			flag = false;
		} else {
			// �绰�����Ƿ������֣���һ���ַ���Ϊ���֣���Ϊ��
			for (int i = 0; i < phone.length(); i++) {
				if (phone.charAt(i) > '9' || phone.charAt(i) < '0') {
					flag = false;
					break;
				} else {
					flag = true;
				}
			}
		}
		return flag;
	}
}
