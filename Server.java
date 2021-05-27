package com.company.httprealize;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //绑定服务端端口号
        ServerSocket server = new ServerSocket(1234);
        while (true){
            Socket client = server.accept();//监听客户端
            //读取数据
            InputStream inputStream = client.getInputStream();
            Request request = ProtocolUtils.readRequest(inputStream);//服务端读取客户端的数据
            System.out.println("服务端收到的请求参数为：" + request.toString());
            OutputStream outputStream = client.getOutputStream();
            //组装响应返回的数据
            Response response = new Response();
            response.setEncode(Encode.UTF8);
            response.setResponse(request.getCommand());
            response.setResponseLength(response.getResponse().length());
            ProtocolUtils.writeResponse(outputStream, response);//服务端发送响应给客户端

        }
    }
}
