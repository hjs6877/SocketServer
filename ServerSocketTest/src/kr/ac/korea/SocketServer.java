package kr.ac.korea;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

/**
 * Created by ideapad on 2015-11-22.
 */
public class SocketServer {

    public static void main(String[] args){
        new SocketServer().createServerSocket();
    }

    public void createServerSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("waiting client connection..");
            while(true){
                Socket clientSocket = serverSocket.accept();
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();

                System.out.println("got a connection!!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class ClientHandler implements Runnable {
        BufferedReader br;
        Socket socket;

        public ClientHandler(Socket socket){
            try{
                this.socket = socket;
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                br = new BufferedReader(isr);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            /**
             * 클라이언트 소켓에서 들어온 스트림을 처리
             */
            String message;
            try{
                while((message = br.readLine()) != null){
                    System.out.println("message: " + message);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
