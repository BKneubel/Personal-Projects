package earlyBuilds;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerFinder{

	private static InetAddress me;

	public static void main(String[] args){
		String inString;
		try{
			me = InetAddress.getLocalHost();
			inString = me.getHostAddress();
		} catch(Exception e){
			inString = null;
			System.out.println("Error:");
			e.printStackTrace();
		}
		String subnet = inString.replaceAll("(\\d{0,3}\\.\\d{0,3}\\.).*", "$1");
		int control = Integer.parseInt(inString.replaceAll("\\d{0,3}\\.\\d{0,3}\\.(\\d{0,3}).*", "$1"));
		ArrayList<String> serverIP = portScan(subnet, control);
		System.out.println(Arrays.toString(serverIP.toArray()));
	}

	public static Future<String> portIsOpen(final ExecutorService es, final String ip, final int port,
			final int timeout){
		return es.submit(new Callable<String>(){
			@Override
			public String call(){
				try{
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ip, port), timeout);
					socket.close();
					return ip;
				} catch(Exception ex){
					return null;
				}
			}
		});
	}

	public static ArrayList<String> portScan(String subnet, int control){
		final ExecutorService es = Executors.newFixedThreadPool(60);
		final int port = 46290;
		final int timeout = 200;
		final List<Future<String>> futures = new ArrayList<Future<String>>();
		System.out.println("Searching for Messenger Servers");
		for(int i = 0; i <= 255; i++){
			futures.add(portIsOpen(es, subnet + control + "." + i, port, timeout));
		}
		for(int i = 0; i < 255 && i != control; i++){
			for(int j = 0; j <= 255; j++){
				futures.add(portIsOpen(es, subnet + i + "." + j, port, timeout));
			}
		}
		es.shutdown();
		ArrayList<String> validIP = new ArrayList<String>();
		try{
			String s = null;
			int size = futures.size();
			for(int i = 0; i < size; i++){
				System.out.print("\r" + (int) (100 * ((double) (i) / (double) size)));
				if((s = futures.get(i).get()) != null){
					validIP.add(s);
				}
			}
			System.out.println();
		} catch(Exception e){
			System.err.println("Error:");
			e.printStackTrace();
		}
		System.out.println("Done");
		return validIP;
	}

}
