package uitl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class DownloadUtil {
    private static OkHttpClient okHttpClient;

    public DownloadUtil(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    /**
     * @param url ��������
     * @param saveDir ���������ļ���SDCardĿ¼
     * @param listener ���ؼ���
     */
    private static void download(final String url, final String saveDir, final OnDownloadListener listener) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // ����ʧ��
                listener.onDownloadFailed(e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // ���������ļ���Ŀ¼
                String savePath = isExistDir(saveDir);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(savePath, getNameFromUrl(url));
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // ������
                        if(!listener.onDownloading(progress, sum, total))
                            break;
                    }
                    fos.flush();
                    // �������
                    listener.onDownloadSuccess(file);
                } catch (Exception e) {
                    listener.onDownloadFailed(e);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    /**
     * @param saveDir
     * @return
     * @throws IOException
     * �ж�����Ŀ¼�Ƿ����
     */
    private static String isExistDir(String saveDir) throws IOException {
        // ����λ��
        File downloadFile = new File(saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

    /**
     * @param url
     * @return
     * �����������н������ļ���
     */
    private static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public interface OnDownloadListener {
        /**
         * ���سɹ�
         */
        void onDownloadSuccess(File file);

        /**
         * @param progress
         * ���ؽ��ȣ�����booleanΪ�Ƿ�������أ�����false��ֹͣ����
         */
        boolean onDownloading(int progress, long downloadedBytes, long totalBytes);

        /**
         * ����ʧ��
         */
        void onDownloadFailed(Exception e);
    }
    
    public static void main(String[] args) {
		String url = "http://square.github.io/okhttp/static/logo-square.png";
		String path = "src/res/";
		download(url, path, new OnDownloadListener() {
			
			@Override
			public boolean onDownloading(int progress, long downloadedBytes, long totalBytes) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void onDownloadSuccess(File file) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDownloadFailed(Exception e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
