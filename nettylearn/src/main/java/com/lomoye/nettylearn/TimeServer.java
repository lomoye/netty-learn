package com.lomoye.nettylearn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lomoye on 2017/8/25.
 * 时间服务器
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }

        ServerSocket server = null;
        ExecutorService service = Executors.newFixedThreadPool(1000);

        try {
            server = new ServerSocket(port);
            System.out.println("the timeServer is start in port:" + port);
            Socket socket = null;
            while (true) {
                socket = server.accept();
                service.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                System.out.println("close server");
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class TimeServerHandler implements Runnable {
        private Socket socket;

        public TimeServerHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                writer = new PrintWriter(this.socket.getOutputStream(), true);

                String currentTime;
                String body;

                while (true) {
                    body = reader.readLine();
                    if (body == null) {
                        break;
                    }
                    System.out.println("接收到数据:" + body);
                    if (!"QT".equals(body)) {
                        currentTime = "BAD ORDER";
                    } else {
                        currentTime = new Date().toString();
                    }
                    writer.println(currentTime);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (writer != null) {
                    writer.close();
                }

                if (this.socket != null) {
                    try {
                        this.socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
