package uitl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class fuyouUtils {

	public static String getData(String params) {

		String url = "http://aipaytest.fuiou.com/aggregatePay/wapPreCreate";

		PrintWriter out = null;
		BufferedReader in = null;
		OutputStream os;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();

			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type","text/html;charset=UTF-8");
			conn.setRequestProperty("user-agent", "Android/1.0");
			conn.setRequestProperty("Content-Type", "application/octet-stream;charset=UTF-8");
			conn.setConnectTimeout(10 * 1000);// 设置连接超时
			conn.setReadTimeout(10 * 1000);// 设置读取超时
			conn.setDoOutput(true);
			conn.setDoInput(true);

			// 发�?�http body内容
			os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
			osw.write(params);
			osw.flush();
			osw.close();

			// 读取服务器端的回应�??
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				if (result.equals("")) {
					result += line;
				} else {
					result += "\n" + line;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;

	}
	
	public static void main(String[] args) {
		
	
		System.out.println(getData(""));
	}

}
