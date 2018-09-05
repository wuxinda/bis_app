/*package com.bluemobi.serviceimpl.device.shelve;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.bluemobi.service.device.shelve.ShelveTcpService;

@Service("shelveTcpService")
public class ShelveTcpServiceImpl implements ShelveTcpService {

	@Override
	public void open(String ip, int port, int channel)  throws IOException,InterruptedException {
		ShelveControlByTcp.open(ip, port, channel);
	}

	@Override
	public void close(String ip, int port) throws IOException,InterruptedException  {
		ShelveControlByTcp.close(ip, port);
	}

	@Override
	public void stop(String ip, int port) throws IOException,InterruptedException  {
		ShelveControlByTcp.stop(ip, port);
	}

	@Override
	public void vent(String ip, int port) throws IOException,InterruptedException {
		ShelveControlByTcp.vent(ip, port);
	}

	@Override
	public void humid(String ip, int port) {
		ShelveControlByTcp.humid(ip, port);
	}

}
*/