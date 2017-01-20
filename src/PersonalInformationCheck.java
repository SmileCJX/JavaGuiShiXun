import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//对输入的数据进行检查
public class PersonalInformationCheck {
	// 判断某人的信息是否已经被写入通讯录中
	public boolean checkName(String name) {
		boolean flag = true;
		BufferedReader bfr = null;
		try {
			bfr = new BufferedReader(new FileReader("E:" + File.separator
					+ "information.txt"));
			String line = null;
			// 读取每一行，直到整个文件的结束
			while ((line = bfr.readLine()) != null) {
				String[] str = line.split(" ");
				// 截取字符串中的姓名字符串
				if (str[0].substring(3, str[0].length() - 1).equals(name)) {
					flag = false;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("用户文件读取失败");
		} finally {
			try {
				if (bfr != null) {
					bfr.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("用户文件读取关闭资源失败");
			}

		}
		return flag;
	}

	// 检查输入的EMAIL地址是否符合规范
	public boolean checkEmail(String email) {
		/*
		boolean flag = true; // EMAIL地址必须包括@符号
		if (!email.contains("@")) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
		*/
		/*
		 * 使用正则表达式对邮箱是否符合规范，作出相应的匹配
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

	// 判断输入的电话是否符合规范
	public boolean checkPhone(String phone) {
		boolean flag = true;
		// 电话号码是否11位，超过或不足11位则为假
		if (phone.length() != 11) {
			flag = false;
		} else {
			// 电话号码是否都是数字，有一个字符不为数字，则为假
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
