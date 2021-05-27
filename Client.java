package com.company.httprealize;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Request request = new Request();//new 一个 Request 对象
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            request.setCommand(str);
            request.setCommandLength(request.getCommand().length());
            request.setEncode(Encode.UTF8);

            Socket client = new Socket("127.0.0.1", 1234);//创建 Socket 套接字，异常处理
//            Socket client = new Socket("1.15.75.93", 1234);//创建 Socket 套接字，异常处理
            OutputStream outputStream = client.getOutputStream();
            //客户端发送请求给服务的的
            ProtocolUtils.writeRequest(outputStream, request);
            //读取相应数据
            InputStream inputStream = client.getInputStream();
            //客户端读取服务端的请求
            Response response = ProtocolUtils.readResponse(inputStream);
            System.out.println("客户端获取的响应结果信息为：" + response.toString());
        }
    }
}
