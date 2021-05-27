package com.company.httprealize;

/**
 * 协议响应类定义
 */
public class Response {
    private byte encode;
    private String response;
    private int responseLength;

    public Response(){
        super();
    }
    public Response(byte encode, String response, int responseLength){
        super();
        this.encode = encode;
        this.response = response;
        this.responseLength = responseLength;
    }
    //读取
    public byte getEncode(){
        return encode;
    }
    public String getResponse(){
        return response;
    }
    public int getResponseLength(){
        return responseLength;
    }
    //写入
    public void setEncode(byte encode){
        this.encode = encode;
    }
    public void setResponse(String response){
        this.response = response;
    }
    public void setResponseLength(int responseLength){
        this.responseLength = responseLength;
    }

    @Override
    public String toString() {
        return "Response{" +
                "encode=" + encode +
                ", response='" + response + '\'' +
                ", responseLength=" + responseLength +
                '}';
    }
}
