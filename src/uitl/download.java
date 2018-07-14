package uitl;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class download {

	
    // ��ַ
    private static final String URL = "data:image/png;base64,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";
    // ��ȡimg��ǩ����
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    // ��ȡsrc·��������
    private static final String IMGSRC_REG = "[a-zA-z]+://[^\\s]*";


    public static void main(String[] args) {
        try {
        	
            
            
            download cm=new download();
            //���html�ı�����
            String HTML = cm.getHtml(URL);
            //��ȡͼƬ��ǩ
            List<String> imgUrl = cm.getImageUrl(HTML);
            //��ȡͼƬsrc��ַ
            List<String> imgSrc = cm.getImageSrc(imgUrl);
//            List<String> imgSrc = new ArrayList<>();
//            imgSrc.add("https://exhentai.org/s/ba220a0bb1/678-63?nl=8819-423845");
            //����ͼƬ
            cm.Download(imgSrc);

        }catch (Exception e){
            System.out.println("��������");
        }

    }

   //��ȡHTML����
    private String getHtml(String url)throws Exception{
    	PrintWriter out = null;
		BufferedReader in = null;
		OutputStream os;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();

			// conn.setRequestProperty("accept", "*/*");
			// conn.setRequestProperty("connection", "Keep-Alive");
			// conn.setRequestProperty("user-agent","Android/1.0");
			// conn.setRequestProperty("Content-Type",
			// "application/octet-stream;charset=UTF-8");
			conn.setConnectTimeout(30 * 1000);// �������ӳ�ʱ
			conn.setReadTimeout(30 * 1000);// ���ö�ȡ��ʱ
			conn.setDoOutput(true);
			conn.setDoInput(true);

			// ����http body����
			os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
			//osw.write(params);
			osw.flush();
			osw.close();

			// ��ȡ�������˵Ļ�Ӧֵ
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

    //��ȡImageUrl��ַ
    private List<String> getImageUrl(String html){
        Matcher matcher=Pattern.compile(IMGURL_REG).matcher(html);
        List<String>listimgurl=new ArrayList<String>();
        while (matcher.find()){
            listimgurl.add(matcher.group());
        }
        return listimgurl;
    }

    //��ȡImageSrc��ַ
    private List<String> getImageSrc(List<String> listimageurl){
        List<String> listImageSrc=new ArrayList<String>();
        for (String image:listimageurl){
            Matcher matcher=Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()){
                listImageSrc.add(matcher.group().substring(0, matcher.group().length()-1));
            }
        }
        return listImageSrc;
    }

    //����ͼƬ
    private void Download(List<String> listImgSrc) {
        try {
        	  
            //��ʼʱ��
            Date begindate = new Date();
            for (String url : listImgSrc) {
                //��ʼʱ��
                Date begindate2 = new Date();
                String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());
                URL uri = new URL(url);
                InputStream in = uri.openStream();
                FileOutputStream fo = new FileOutputStream(new File("src/res/"+imageName));
                byte[] buf = new byte[1024];
                int length = 0;
                System.out.println("��ʼ����:" + url);
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                in.close();
                fo.close();
                System.out.println(imageName + "�������");
                //����ʱ��
                Date overdate2 = new Date();
                double time = overdate2.getTime() - begindate2.getTime();
                System.out.println("��ʱ��" + time / 1000 + "s");
            }
            Date overdate = new Date();
            double time = overdate.getTime() - begindate.getTime();
            System.out.println("�ܺ�ʱ��" + time / 1000 + "s");
        } catch (Exception e) {
            System.out.println("����ʧ��");
            e.printStackTrace();
        }
    }
}