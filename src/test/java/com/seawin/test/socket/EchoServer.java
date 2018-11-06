/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author lijin
 * @version $Id: EchoServer.java, v 0.1 2018年10月24日 下午1:03:59 lijin Exp $
 * 本代码参考https://juejin.im/post/5b3649d751882552f052703b
 * function 创建 ServerSocket 并监听客户连接
 * 另外参考https://www.jianshu.com/p/06fd661d492b
 */
public class EchoServer {

    private final ServerSocket mServerSocket;

    public EchoServer(int port) throws IOException {
        // 1. 创建一个 ServerSocket 并监听端口 port
        mServerSocket = new ServerSocket(port);
    }

    public void run() throws IOException {
        // 2. 开始接受客户连接
        Socket client = mServerSocket.accept();
        handleClient(client);
    }

    private void handleClient(Socket socket) throws IOException {
        // 3. 使用 socket 进行通信 ...
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        byte[] buffer = new byte[1024];
        int n;
        while ((n = in.read(buffer)) > 0) {
            out.write(buffer, 0, n);
        }
    }


    public static void main(String[] argv) {
        try {
            EchoServer server = new EchoServer(9877);
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

