package finalMessenger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MessengerLauncher{

	private static InetAddress me;

	public static void main(String[] args){
		Scanner KBReader = new Scanner(System.in);
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
		try{
			if(serverIP.size() == 0){
				System.out.println("No server was found. Enter \"0\" to continue.");
				int input = KBReader.nextInt();
				if(input == 0){
					System.out.println("The server is starting...");
					System.out
							.println("Please leave this window open, and open the launcher again to start messaging.");
					MessengerMultiServer.startServer();
				} else if(input == -1){
					System.out.println("Remote Connection Mode active. Input IP");
					String ip = KBReader.next();
					String address = ip + ":" + 46290;
					GUI.launch();
					MultiThreadClient.startClient(address);
				}

			} else{
				for(int i = 0; i < serverIP.size(); i++){
					System.out.println(i + ") " + serverIP.get(i));
				}
				System.out.println("Select the server to which you wish to connect by typing its number");
				int index = KBReader.nextInt();
				if(index == -1){
					try{
						System.out.println("Multi Server Mode active...");
						System.out.println("Starting second server instance.");
						System.out.println("This PC has the host name of: " + me.getHostName());
						MessengerMultiServer.startServer();
					} catch(Exception e){
						System.err.println(
								"This PC is already running an instance of the server. (Press any key to close)");
						System.exit(-1);
					}
				} else{
					String address = serverIP.get(index) + ":" + 46290;
					GUI.launch();
					MultiThreadClient.startClient(address);
				}
			}
		} catch(IOException e){
			System.err.println("IO Exception");
			e.printStackTrace();
		}
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
		final ExecutorService es = Executors.newFixedThreadPool(80);
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
				if((s = futures.get(i).get()) != null){
					validIP.add(s);
				}
				System.out.print("\r" + ((((int) (10000 * ((double) (i) / (double) size)))) / 100.0) + "%");
			}
			System.out.print("\r100.00%");
			System.out.println();
		} catch(Exception e){
			System.err.println("Error:");
			e.printStackTrace();
		}
		System.out.println("Done");
		return validIP;
	}

}
