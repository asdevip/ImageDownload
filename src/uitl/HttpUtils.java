package uitl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {

	public static void main(String[] args) {
		getJson();
	}

	public static void getJson() {

		String url = "http://ask.android-studio.org/uploads/avatar/000/00/00/04_avatar_mid.jpg";//"http://m.shencou.com/wapreader.php?aid=112&cid=37637";

		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();

			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Android/1.0");
			conn.setRequestProperty("Content-Type", "application/octet-stream;charset=UTF-8");
			conn.setConnectTimeout(10 * 1000);// �������ӳ�ʱ
			conn.setReadTimeout(10 * 1000);// ���ö�ȡ��ʱ
			conn.setDoOutput(true);
			conn.setDoInput(true);


			// ��ȡ�������˵Ļ�Ӧֵ
			InputStream inputStream = conn.getInputStream();
			// ��ȡ�ֽ�����
			byte[] bytes = new byte[2048];
			int length = 0;
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			while ((length = inputStream.read(bytes)) != -1) {
				arrayOutputStream.write(bytes, 0, length);
			}
			arrayOutputStream.close();
			bytes = arrayOutputStream.toByteArray();

			// ����·��
			File savePath = new File("src/res/");
			// ���Դ˳���·������ʾ���ļ���Ŀ¼�Ƿ���ڡ�
			if (savePath.exists()) {
				// �����˳���·����ָ����Ŀ¼��
				savePath.mkdir();
			}

			// ����ļ�
			File file = new File(savePath, File.separator + getNameFromUrl(url));
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(bytes);
			fileOutputStream.close();
			
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param url
	 * @return �����������н������ļ���
	 */
	private static String getNameFromUrl(String url) {
		return url.substring(url.lastIndexOf("/") + 1);
	}
}