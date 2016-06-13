package com.puxtech.ybk.hangqing.protocol;

import com.puxtech.ybk.hangqing.HangQingLogger;
import com.puxtech.ybk.hangqing.exception.ServerException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HangQingHttpSender {

	private static final String ENCODE = "UTF-8";
	/** 注册通讯访问方式，以及UI工具类处理地址时使用 */
	private static final String HTTP = "http";
	/** 注册通讯访问方式，以及UI工具类处理地址时使用 */
	private static final String HTTPS = "https";
	/** 设置链接池 */
	private static final int CONN_PER_ROUTE_BEAN = 12;
	/** 设置最大连接数 */
	private static final int MAX_TOTAL_CONNECTIONS = 20;
	/** 设置链接超时时间 */
	private static final int CONNECTION_TIME_OUT = 10 * 1000;
	/** 设置socket超时时间 */
	private static final int SOCKET_TIME_OUT = 10 * 1000;
	/** 设置sokect缓存最大字节数 */
	private static final int SOCKET_BUFFER_SIZE = 8 * 1024;;
	/** http协议代理端口 */
	private static final int HTTP_PROXY_PORT = 80;
	/** https协议代理端口 */
	private static final int HTTPS_PROXY_PORT = 443;


	public String requestJson(byte[] requestData, String urlStr) throws IOException, ServerException {
			URL url = new URL(urlStr);
			// 利用HttpURLConnection对象,我们可以从网络中获取网页数据.
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 单位是毫秒，设置超时时间为10秒
			conn.setConnectTimeout(CONNECTION_TIME_OUT);
			conn.setReadTimeout(SOCKET_TIME_OUT);
			conn.setRequestProperty("Content-Length",String.valueOf(requestData.length));
			// HttpURLConnection是通过HTTP协议请求path路径的，所以需要设置请求方式,可以不设置，因为默认为GET
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setDoOutput(true);// 准备写出数据
			conn.setDoInput(true);
			conn.getOutputStream().write(requestData);
			conn.getOutputStream().flush();
			conn.getOutputStream().close();
			if (conn.getResponseCode() == 200) {
				InputStream is = conn.getInputStream();
				return new String(readStream(is));
			} else {
				HangQingLogger.e("requestBinary faild, ResponseCode:"
						+ conn.getResponseCode());
				throw new ServerException("服务器异常（"+conn.getResponseCode()+"）");
			}

	}

	/**
	 * 将输入流转化为字符数组
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	private byte[] readStream(InputStream is) {
		ByteArrayOutputStream bout = null;
		try {
			bout = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				bout.write(buffer, 0, len);
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bout.toByteArray();
	}

}
