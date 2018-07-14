package uitl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class test {

	/**
	 * ������Url�������ļ�
	 * 
	 * @param urlStr
	 * @param fileName
	 * @param savePath
	 * @throws IOException
	 */
	public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent", "Android/1.0");
		conn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(10 * 1000);// �������ӳ�ʱ
		conn.setReadTimeout(10 * 1000);// ���ö�ȡ��ʱ
		conn.setDoOutput(true);
		conn.setDoInput(true);
		// ���ó�ʱ��Ϊ3��
		// conn.setConnectTimeout(10 * 1000);
		// ��ֹ���γ���ץȡ������403����

		System.out.println(conn.getResponseMessage());
		// �õ�������
		InputStream inputStream = conn.getInputStream();

		// ��ȡ�Լ�����
		byte[] getData = readInputStream(inputStream);

		// �ļ�����λ��
		File saveDir = new File(savePath);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		File file = new File(saveDir + File.separator + fileName);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(getData);
		if (fos != null) {
			fos.close();
		}
		if (inputStream != null) {
			inputStream.close();
		}

		System.out.println("info:" + url + " download success");

	}

	/**
	 * ���������л�ȡ�ֽ�����
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	public static void main(String[] args) {
		//
		try {
			String u = "http://e.hiphotos.baidu.com/image/pic/item/03087bf40ad162d9a62a929b1ddfa9ec8b13cd75.jpg";
			downLoadFromUrl(u, "bai.jpg", "src/res/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
