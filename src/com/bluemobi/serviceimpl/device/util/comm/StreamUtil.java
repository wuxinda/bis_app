package com.bluemobi.serviceimpl.device.util.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StreamUtil {
	
	public static byte[] readStream(InputStream stream,int length) throws IOException{
		byte[]streamData=null;
		List<Integer> lengths = new ArrayList<Integer>();
		List<byte[]> buffers = new ArrayList<byte[]>();
		int l = 0;  int totalLength = 0;  byte[] buffer = null; // 
		while (totalLength < length && l != -1) { //
			buffer = new byte[length];
			l = stream.read(buffer);
			if (l != -1) {
				lengths.add(new Integer(l));
				buffers.add(buffer);
				totalLength+=l;
			}
		}
		if(totalLength==0) {
			return null;
		}
		l=0;
		streamData = new byte[totalLength];
		length =buffers.size();
		int blength=0;
		byte[] bbuffer=null;
		for (int i = 0; i < length; i++) {
			blength = ((Integer) lengths.get(i)).intValue();
			bbuffer = (byte[]) buffers.get(i);
			System.arraycopy(bbuffer, 0, streamData, l,blength);
			l=l+blength;
		}
		stream=null; lengths=null; buffers=null;	buffer=null;
		return streamData;
	}
	

}
