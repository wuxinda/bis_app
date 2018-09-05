package com.bluemobi.service.device.shelve;

import java.io.IOException;

public interface ShelveTcpService {
	
	public void open(String ip, int port, int channel)throws IOException, InterruptedException;
	
	public void close(String ip, int port)throws IOException, InterruptedException;
	
	public void stopOrCancel(String ip, int port)throws IOException, InterruptedException;
	
	public void reset(String ip, int port) throws IOException, InterruptedException;
	
	public void vent(String ip, int port) throws IOException, InterruptedException;
	
	public void humid(String ip, int port);

}
