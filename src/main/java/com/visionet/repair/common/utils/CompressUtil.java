package com.visionet.repair.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import com.google.common.collect.Lists;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * Created by xuetao on 16/3/31.
 */
public class CompressUtil {

    private static Random rm = new Random();

    /**
     * 对文件进行加密压缩
     * @param fileName 待压缩文件名
     * @param zipName 压缩后文件名
     * @param password 压缩文件密码
     * @return
     */
    public static void zipFileWithEncrtype(
            String fileName,
            String zipName,
            String password)
            throws ZipException {
        ZipFile zipFile = new ZipFile(zipName);
        File file = new File(fileName);
        ArrayList fileList = new ArrayList();
        if (file.isFile()){
            fileList.add(file);
        }else if (file.isDirectory()) {
            fileList.add(file.listFiles());
        }
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_FASTEST);
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        if(password!=null) parameters.setPassword(password);
        zipFile.addFiles(fileList, parameters);
    }

    public static void zipFiles(String zipName,List<File> inputFiles)
            throws Exception {
        ZipOutputStream zos = null;
        byte[] buf = new byte[1024];
        try{
            zos=new ZipOutputStream(new FileOutputStream(zipName));
            for(File file : inputFiles){
                if (file.isFile()){
                    FileInputStream in = new FileInputStream(file);
                    // Add ZIP entry to output stream.
                    String path = file.getPath();
                    if(path.contains("wordOrder")){
                        path = path.substring(path.indexOf("wordOrder/")+10).replace("/","_");
                        zos.putNextEntry(new ZipEntry(path));
                    }else {
                        zos.putNextEntry(new ZipEntry(file.getParentFile().getName()+"_"+file.getName()));
                    }
                    // Transfer bytes from the file to the ZIP file
                    int len;
                    while ( (len = in.read(buf)) > 0) {
                        zos.write(buf, 0, len);
                    }
                    // Complete the entry
                    zos.closeEntry();
                    in.close();
                }
            }


        }catch(Exception e){
            File f = new File(zipName);
            if(f.exists()) {
                f.delete();
            }
            throw e;
        }finally{
            if(zos!=null) zos.close();
        }
    }

    /**
     * 对文件进行加密压缩,密码采用随机生成方式
     * @param fileName 待压缩文件名
     * @param zipName 压缩文件名
     * @return 密码
     */
    public static String zipFileWithEncrypt(String fileName, String zipName)
            throws ZipException {
        ZipFile zipFile = new ZipFile(zipName);
        File file = new File(fileName);
        ArrayList fileList = new ArrayList();
        if (file.isFile())
            fileList.add(file);
        else if (file.isDirectory()) {
            fileList.add(file.listFiles());
        }
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_FASTEST);
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        String pwd = generatePassword(0);
        parameters.setPassword(pwd);
        zipFile.addFiles(fileList, parameters);
        return pwd;
    }
    /**
     * 生成随机密码
     * @param pwd_len 密码长度,len为0时采用9-16位的随机长度
     * @return
     */
    public static String generatePassword(int pwd_len) {
        int numCount = 0;
        //len为0时设定密码长度9-16位
        if (pwd_len == 0) {
            pwd_len = rm.nextInt(16) % (16 - 9 + 1) + 9;
        }
        StringBuffer pwd = new StringBuffer();
        for (int i = 0; i < pwd_len; i++) {
            int pwdChar = rm.nextInt(94) + 33;
            if (pwdChar >= 48 && pwdChar <= 57) {
                numCount++;
            }
            pwd.append((char) (pwdChar));
        }
        if (numCount < 3) {
            return generatePassword(0);
        }
        return pwd.toString();
    }
    /**
     * 转换特殊字符为HTML符号
     * @param str 待转换字符
     * @return 转换后字符
     */
    public static String charConvertHTML(String str) {
        if (str != null && !"".equals(str)) {
            StringBuffer htmlStr = new StringBuffer();
            for (int i = 0; i < str.length(); i++) {
                htmlStr.append("&#" + ((int) str.charAt(i)) + ";");
            }
            return htmlStr.toString();
        } else {
            return str;
        }
    }

//    /**
//     * 根据KEY获取密码,该方法与DB绑定,需要从数据库中获取密码
//     *
//     * @param key DB关键字
//     *
//     * @return 密码
//     */
	/*
	public static String generatePassword(String key) {
		String querySql =
			"SELECT RP.REPORT_PASSWORD FROM TD_REPORT_PASSWORD RP WHERE RP.REPORT_KEY=? ";
		String password = null;
		try {
			password = DBOperation.selectOne(querySql, key);
			if (StringUtils.isEmpty(password)) {
				String insertSql =
					"INSERT INTO TD_REPORT_PASSWORD(REPORT_KEY,REPORT_PASSWORD) VALUES(?,?) ";
				password = generatePassword(0);
				DBOperation.execute(insertSql, new String[] { key, password });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}*/

    public static void main(String[] args) {
        try {
//            List<File> fileList = Lists.newArrayList(new File("/Users/xuetao/Downloads/48/T48W121C1U606.html"), new File("/Users/xuetao/Downloads/48/T48W124C2U675.html"));
//            zipFiles("/Users/xuetao/Downloads/ZipTest3.zip", fileList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
