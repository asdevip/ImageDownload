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
			conn.setConnectTimeout(10 * 1000);// 设置连接超时
			conn.setReadTimeout(10 * 1000);// 设置读取超时
			conn.setDoOutput(true);
			conn.setDoInput(true);


			// 读取服务器端的回应值
			InputStream inputStream = conn.getInputStream();
			// 获取字节数组
			byte[] bytes = new byte[2048];
			int length = 0;
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			while ((length = inputStream.read(bytes)) != -1) {
				arrayOutputStream.write(bytes, 0, length);
			}
			arrayOutputStream.close();
			bytes = arrayOutputStream.toByteArray();

			// 保存路径
			File savePath = new File("src/res/");
			// 测试此抽象路径名表示的文件或目录是否存在。
			if (savePath.exists()) {
				// 创建此抽象路径名指定的目录。
				savePath.mkdir();
			}

			// 输出文件
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
	 * @return 从下载连接中解析出文件名
	 */
	private static String getNameFromUrl(String url) {
		return url.substring(url.lastIndexOf("/") + 1);
	}
}