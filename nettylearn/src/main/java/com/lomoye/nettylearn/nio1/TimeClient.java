package com.lomoye.nettylearn.nio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by lomoye on 2017/8/25.
 * 时间客户端
 */
public class TimeClient {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }

        Socket socket = null;

        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            socket = new Socket("127.0.0.1", port);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("QT");

            String resp = reader.readLine();
            System.out.println("server time is:" + resp);
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (socket != null) {
                socket.close();
            }

            writer = null;
            reader = null;
            socket = null;
        }
    }
}
