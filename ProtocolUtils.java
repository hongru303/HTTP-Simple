package com.company.httprealize;
/**
 * 发送响应封装的协议工具类
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProtocolUtils {
    //服务端读取客户端的 Request 从输入流中反序列化 Request 对象 Byte2Int
    public static Request readRequest(InputStream inputStream) throws IOException {
        //读取编码
        byte[] encodeByte = new byte[1];//0,1 UTF-8编码
        inputStream.read(encodeByte);//读取
        byte encode = encodeByte[0];//取出

        //读取命令长度
        byte[] commandLengthBytes = new byte[4];//4个字节
        inputStream.read(commandLengthBytes);//读取
        int commandLength = ByteUtils.byte2Int(commandLengthBytes);//取出命令长度

        //读取命令
        byte[] commandBytes = new byte[commandLength];//数组初始化
        inputStream.read(commandBytes);//读取
        String command = "";//初始化命令字符串
        if (Encode.UTF8 == encode){//判断编码
            command = new String(commandBytes, "UTF-8");
        }else{
            command = new String(commandBytes, "GB2312");
        }

        //组装请求的返回
        Request request = new Request(encode, command, commandLength);
        return request;
    }

    //服务端返回给客户端的命令。序列号请求信息 Int2ByteArray
    public static void writeRequest(OutputStream outputStream, Request request) throws IOException{
        //将 response 响应返回给客户端
        outputStream.write(request.getEncode());
        outputStream.write(ByteUtils.Int2ByteArray(request.getCommandLength()));
        if (Encode.UTF8 == request.getEncode()){
            outputStream.write(request.getCommand().getBytes("UTF-8"));
        }else if (Encode.GBK == request.getEncode()){
            outputStream.write(request.getCommand().getBytes("GBK"));
        }
        outputStream.flush();
    }

    //客户端读取服务端的 Response 从输入流中反序列化 Response 对象  Byte2Int
    public static Response readResponse(InputStream inputStream) throws IOException {
        //读取编码
        byte[] encodeByte = new byte[1];
        inputStream.read(encodeByte);
        byte encode = encodeByte[0];//encode 为编码

        //读取响应长度
        byte[] requestLengthBytes = new byte[4];
        inputStream.read(requestLengthBytes);
        int responseLength = ByteUtils.byte2Int(requestLengthBytes);// responseLength 为长度

        //读取命令
        byte[] responseBytes = new byte[responseLength];
        inputStream.read(responseBytes);
        String response = "";
        if (Encode.UTF8 == encode){
            response = new String(responseBytes,"UTF-8");
        }else if (Encode.GBK == encode){
            response = new String(responseBytes,"GBK");
        }

        //组装请求返回
        Response resp = new Response(encode, response, responseLength);
        return resp;
    }
    //服务端发送响应给客户端
    public static void writeResponse(OutputStream outputStream, Response response) throws IOException{
        //将 response 响应返回给客户端
        outputStream.write(response.getEncode());
        outputStream.write(ByteUtils.Int2ByteArray(response.getResponseLength()));
        if (Encode.UTF8 == response.getEncode()){
            outputStream.write(response.getResponse().getBytes("UTF-8"));
        }else if (Encode.GBK == response.getEncode()){
            outputStream.write(response.getResponse().getBytes("GBK"));
        }
        outputStream.flush();
    }




}
