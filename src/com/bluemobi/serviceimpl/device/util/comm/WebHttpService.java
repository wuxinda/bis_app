package com.bluemobi.serviceimpl.device.util.comm;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import sun.net.www.protocol.http.Handler;

public class WebHttpService {

	public static HttpResult webHttpHerlp(String ActionURL, String ActionMethod,
			String ContentType, byte[] FormData, String Charset, int RPCTimeout) throws IOException {
		Handler handler = new Handler();
		URL url = new URL(null, ActionURL, handler);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod(ActionMethod);
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);
		urlConnection.setConnectTimeout(RPCTimeout);
		urlConnection.setRequestProperty("Accept-Charset", Charset);
		//urlConnection.setRequestProperty("Accept-Encoding", "gzip");
		urlConnection.setRequestProperty("Content-Type", ContentType);

		if (ActionMethod.equalsIgnoreCase("POST") && FormData != null) {
			urlConnection.getOutputStream().write(FormData);
			urlConnection.getOutputStream().flush();
			urlConnection.getOutputStream().close();
		}

		return makeContent(urlConnection);
	}

	private static HttpResult makeContent(HttpURLConnection urlConnection) throws IOException {
		try {
			HttpResult result = new HttpResult();
			int StatusCode = urlConnection.getResponseCode();
			if (StatusCode != HttpURLConnection.HTTP_OK) {
				result.setResponse(String.valueOf(StatusCode));
				result.setResult(false);
				return result;
			}
			InputStream in = urlConnection.getInputStream();
			int s = urlConnection.getContentLength();
			byte[] streamData = StreamUtil.readStream(in, s);
			//result.setResponse(ZipUtil.ungzip(streamData));
			result.setResponse(new String(streamData, "utf-8"));
			in.close();
			result.setResult(true);
			return result;
		} catch (IOException e) {
			throw e;
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
	}
}
