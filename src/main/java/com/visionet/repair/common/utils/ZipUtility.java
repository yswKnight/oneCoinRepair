package com.visionet.repair.common.utils;

import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * Created by xuetao on 16/3/30.
 */
public class ZipUtility {

//    private  static int k = 1; // 定义递归次数变量
    private static String ZIP_ENCODEING = "UTF-8";


    public ZipUtility() {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
//            ZipUtility.zip("/Users/xuetao/Downloads/ZipTest1.zip", new File("/Users/xuetao/Downloads/testDoc"));
            List<File> fileList = Lists.newArrayList(new File("/Users/xuetao/Downloads/48/T48W121C1U606.html"),new File("/Users/xuetao/Downloads/48/T48W124C2U675.html"));
            ZipUtility.zip("/Users/xuetao/Downloads/ZipTest3.zip", fileList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void zip(String zipFileName, List<File> inputFiles) throws Exception {
//        System.out.println("压缩中...");
        ZipOutputStream out = null;
        BufferedOutputStream bo = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFileName));
            bo = new BufferedOutputStream(out);


            for (File inputFile : inputFiles) {
                out.putNextEntry(new ZipEntry(inputFile.getName())); // 创建zip压缩进入点base
                FileInputStream in = new FileInputStream(inputFile);
                BufferedInputStream bi = new BufferedInputStream(in);
                int b;
                while ((b = bi.read()) != -1) {
                    bo.write(b); // 将字节流写入当前zip目录
                }
                bi.close();
                in.close(); // 输入流关闭

            }

//            System.out.println("压缩完成");
        }catch (Exception e){
            throw e;
        }finally {
            if(bo!=null) bo.close();
            if(out!=null) out.close(); // 输出流关闭
        }

    }

    public static void zip(String zipFileName, File inputFile) throws Exception {
//        System.out.println("压缩中...");
        ZipOutputStream out = null;
        BufferedOutputStream bo = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFileName));
            bo = new BufferedOutputStream(out);
            zip(out, inputFile, inputFile.getName(), bo);
//            System.out.println("压缩完成");
        }catch (Exception e){
            throw e;
        }finally {
            if(bo!=null) bo.close();
            if(out!=null) out.close(); // 输出流关闭
        }
    }

    private static void zip(ZipOutputStream out, File f, String base,
                     BufferedOutputStream bo) throws Exception { // 方法重载
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            if (fl.length == 0) {
                out.putNextEntry(new ZipEntry(base + "/")); // 创建zip压缩进入点base
                System.out.println("base d:"+base + "/");
            }
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹
            }
//            System.out.println("第" + k + "次递归");
//            k++;
        } else {
            out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
            System.out.println("base f:"+base);
            FileInputStream in = new FileInputStream(f);
            BufferedInputStream bi = new BufferedInputStream(in);
            int b;
            while ((b = bi.read()) != -1) {
                bo.write(b); // 将字节流写入当前zip目录
            }
            bi.close();
            in.close(); // 输入流关闭
        }
    }
}
