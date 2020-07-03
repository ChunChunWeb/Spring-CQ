package com.huangyichun.auto_cq.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseUtil {
    public static final String TAO_KOU_LIN_PARSE = "https://api.taokouling.com/tkl/tkljm?apikey=THolkSRsRu&tkl=";

    public static void main(String[] args) {
        System.out.println(doGet(TAO_KOU_LIN_PARSE + "%EF%BF%A5e08e1tPBtjU%EF%BF%A5"));
    }

    // 链接url下载图片
    public static void downloadPicture(String urlList,String path) {
        URL url;
        try {
            url = new URL(urlList);

            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TaoBao获取真实连接使用
    public static String doGet(String URL){
        HttpURLConnection conn = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder result = new StringBuilder();
        try{
            //创建远程url连接对象
            URL url = new URL(URL);
            //通过远程url连接对象打开一个连接，强转成HTTPURLConnection类
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //设置连接超时时间和读取超时时间
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(60000);
            conn.setRequestProperty("Accept", "application/json");
            //发送请求
            conn.connect();
            //通过conn取得输入流，并使用Reader读取
            if (200 == conn.getResponseCode()){
                is = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String line;
                while ((line = br.readLine()) != null){
                    result.append(line);
                }
            }else{
                System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(br != null){
                    br.close();
                }
                if(is != null){
                    is.close();
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
            assert conn != null;
            conn.disconnect();
        }
        return result.toString();
    }

    public static String doPost(String URL){
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn;
        try{
            URL url = new URL(URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //发送POST请求必须设置为true
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //设置连接超时时间和读取超时时间
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //获取输出流
            out = new OutputStreamWriter(conn.getOutputStream());
            String jsonStr = "{\"qry_by\":\"name\", \"name\":\"Tim\"}";
            out.write(jsonStr);
            out.flush();
            out.close();
            //取得输入流，并使用Reader读取
            if (200 == conn.getResponseCode()){
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = in.readLine()) != null){
                    result.append(line);
                    System.out.println(line);
                }
            }else{
                System.out.println("ResponseCode is an error code:" + conn.getResponseCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(out != null){
                    out.close();
                }
                if(in != null){
                    in.close();
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
        return result.toString();
    }

    // 是否包含淘宝的关键字
    public static boolean isContainsTaoBaoUrl(String url) {
        return url.contains("￥");
    }

    // 正则寻找TaoBao编码
    public static ArrayList<String> getTaoBaoUrls(String url) {
        String target="￥.{10,11}￥";
        Pattern pattern = Pattern.compile(target);
        Matcher matcher = pattern.matcher(url);

        ArrayList<String> list = new ArrayList<>();
        boolean find = matcher.find();
        while (find) {
            System.out.println(matcher.group(0));
            list.add(matcher.group(0));
            find = matcher.find();
        }

        return list;
    }

    // 转换json获得真实连接
    public static String getTaoBaoRealUrl(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        int code = jsonObject.getInteger("code");
        boolean isParseSuccess = code == 1;
        if (!isParseSuccess) {
            System.err.println("未能正确转换json:" + json);
            return "";
        }
        System.out.println(jsonObject.getString("url"));
        return jsonObject.getString("url");
    }

    // 正则寻找JD地址
    public static ArrayList<String> getJDUrls(String url) {
//        url = "亚瑟士 首页领券防身，\n https://u.jd.com/Aht6Do";
        String target="(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(target);
        Matcher matcher = pattern.matcher(url);

        ArrayList<String> list = new ArrayList<>();
        boolean find = matcher.find();
        while (find) {
            System.out.println(matcher.group(0));
            list.add(matcher.group(0));
            find = matcher.find();
        }

        return list;
    }

    // 是否包含京东的关键字
    public static boolean isContainsJDUrl(String url) {
        return url.contains("jd.com");
    }



    

}