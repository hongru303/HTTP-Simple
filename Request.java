package com.company.httprealize;
/**
 * 协议请求类的定义
 */
public class Request {
    private byte encode;
    //命令
    private String command;
    //命令长度
    private int commandLength;
    //默认构造方法
    public Request(){
        super();
    }
    //请求的参数
    public Request(byte encode, String command, int commandLength){
        super();
        this.encode = encode;
        this.command = command;
        this.commandLength = commandLength;
    }
    //读取
    public byte getEncode(){
        return encode;
    }
    public String getCommand(){
        return command;
    }
    public int getCommandLength(){
        return commandLength;
    }
    //写入
    public void setEncode(byte encode){
        this.encode = encode;
    }
    public void setCommand(String command){
        this.command = command;
    }
    public void setCommandLength(int commandLength){
        this.commandLength = commandLength;
    }

    @Override
    public String toString() {
        return "Request{" +
                "encode=" + encode +
                ", command='" + command + '\'' +
                ", commandLength=" + commandLength +
                '}';
    }
}
