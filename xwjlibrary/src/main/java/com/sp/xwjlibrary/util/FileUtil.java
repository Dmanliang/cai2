package com.sp.xwjlibrary.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FileUtil {

	/**
	 * 文件的字节单位 BYTE,KB,MB,GB,TB;
	 */
	public enum FileUnit{
		BYTE,KB,MB,GB,TB
	}
	
	public static class SdcardDisableException extends Exception{
		private final long serialVersionUID = 1L;
		public SdcardDisableException(){}
		public SdcardDisableException(String ex){
			super(ex);
		}
	}

	/**
	 * 在sdcard中创建文件夹
	 * @param folderPath
	 * @return 文件夹路径 
	 */
	public static String createFolderPath(String folderPath)throws SdcardDisableException{
		String fileDir = "";
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

			String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			if (!folderPath.contains(sdPath)) {
				if (folderPath.startsWith("/")) {
					fileDir = sdPath + folderPath;
				} else {
					fileDir = sdPath + "/" + folderPath;
				}
			}else {
				fileDir = folderPath;
			}

			File f = new File(fileDir);
	    	//如果文件目录存在返回true,否则false
	    	if(!f.exists() || !f.isDirectory()){  
	    		//创建一个文件目录(多个目录 +s)
	    		//创建成功返回true,否则false表示文件已经存在
				boolean mkdirs = f.mkdirs();
				if (!mkdirs){
					throw new SdcardDisableException("创建文件目录失败");
				}
			}
		}else{
			throw new SdcardDisableException("sdcard不存在或不可用");
		}
		return fileDir + File.separator;
	}

	/**
	 * 判断文件或文件夹是否存在
	 * @param folderPath
	 * @return
	 */
	public static boolean isExist(File folderPath,String filePath){
		File file = new File(folderPath,filePath);
		if (file.exists() || file.isDirectory()){
			return true;
		}
		return false;
	}

	public static boolean isFile(File folderPath,String filePath){
		File file = new File(folderPath,filePath);
		if (file.exists()){
			return true;
		}
		return false;
	}

	/**
	 * 获取文件内的文件
	 * @param file
	 * @param keyword 查找关键字,为空查找全部,以"."开头为后缀名查找
	 */
	public static ArrayList<File> getFiles(File file, final String keyword){
		ArrayList<File> files = new ArrayList<>();
		if (file.exists() || file.isDirectory()) {
            File[] listFiles = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (keyword.startsWith(".")) return name.endsWith(keyword);
                    if (TextUtils.isEmpty(keyword)) return true;
                    if (name.contains(keyword)) return true;
                    return false;
                }
            });
            if (listFiles != null)
                files.addAll(Arrays.asList(listFiles));
        }
		return files;
	}
  
    /**
     * 删除指定文件(文件夹)
     * @param filePath
     * @param context
     * @return false,表示文件(文件夹)不存在
     */
    public static boolean delFileOrFolder(Context context,String filePath) {
    	File file = new File(filePath);
    	if(!file.exists()){
			return false;
		}
		if (file.isDirectory()){
			File[] files = file.listFiles();
			for (File f : files) {
				Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
				Uri uri = Uri.fromFile(f);
				intent.setData(uri);
				context.sendBroadcast(intent);
				f.delete();
			}
		}

		Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		Uri uri = Uri.fromFile(file);
		intent.setData(uri);
		context.sendBroadcast(intent);
		file.delete();
		return true;
	}

	/**
	 * 根据文件名删除,删除包含fileName的所有文件
	 * @param context
	 * @param folderPath
	 * @param fileName
     * @return
     */
	public static boolean delFileByName(Context context,String folderPath,String fileName){
		File file = new File(folderPath);
		if(!file.exists()){
			return false;
		}
		File[] files = file.listFiles();
		for (File f : files) {
			String name = f.getName();
			if(name != null || fileName != null){
				if(name.contains(fileName)){
					Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
					Uri uri = Uri.fromFile(f);
					intent.setData(uri);
					context.sendBroadcast(intent);
					f.delete();
				}
			}else{
				return false;
			}
		}
		return true;
	}
    
    /**
     * 根据给定大小将文件夹中的文件逐个删除
     * <h1>如果fileSmall=0,删除(file < fileBig)
     * <h1>如果fileBig=0,删除(file > fileSmall)
     * <h1>如果都不为0,删除(fileSmall < file < fileBig)
     * <h1>如果fileSmall=fileBig,删除该文件夹下的全部文件
     * @param context
     * @param folderPath
     * @param fileSmall
     * @param fileBig
     * @return false,表示文件夹不存在
     */
    public static boolean delFolder2Files(Context context,String folderPath,
    		double fileSmall,double fileBig,FileUnit fileUnit){
    	File file = new File(folderPath);
    	if(!file.exists()){
			return false;
		}
    	
    	File[] files = file.listFiles();
    	for (File f : files) {
    		double size = f.length();

    		if(fileUnit == FileUnit.BYTE){
    		}else if(fileUnit == FileUnit.KB){
    			size = size/1024;
    		}else if(fileUnit == FileUnit.MB){
    			size = size/1024/1024;
    		}else if(fileUnit == FileUnit.GB){
    			size = size/1024/1024/1024;
    		}else if(fileUnit == FileUnit.TB){
    			size = size/1024/1024/1024/1024;
    		}
    		
    		if(fileSmall == fileBig){
    		}else if(fileSmall == 0 && fileBig != 0){
    			if(size > fileBig){
    				break;
    			}
    		}else if(fileSmall != 0 && fileBig == 0){
    			if(size < fileSmall){
    				break;
    			}
    		}else if(fileSmall != 0 && fileBig != 0){
    			if((size > fileBig) || (size < fileSmall)){
    				break;
    			}
    		}
			Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
			Uri uri = Uri.fromFile(f);
			intent.setData(uri);
			context.sendBroadcast(intent);
			f.delete();
		}
    	
    	return true;
    }
    
    /**
     * 获取文件夹大小
     * @param folderPath 文件夹路径
     * @param fileUnit 大小单位
     * @return -1,表示文件不存在
     */
    public static double getFolderSize(String folderPath,FileUnit fileUnit){
		File file = new File(folderPath);
		if(!file.exists()){
			return -1;
		}
		double size = 0;
		File[] files = file.listFiles();
		for (File f : files) {
			size += f.length();
		}
		if(fileUnit == FileUnit.BYTE){
		}else if(fileUnit == FileUnit.KB){
			size = size/1024;
		}else if(fileUnit == FileUnit.MB){
			size = size/1024/1024;
		}else if(fileUnit == FileUnit.GB){
			size = size/1024/1024/1024;
		}else if(fileUnit == FileUnit.TB){
			size = size/1024/1024/1024/1024;
		}
		return size;
		
	}
    
    /**
     * 获取文件大小
     * @param filePath 不能获取整个文件夹,.txt/.mp4等才行
     * @param fileUnit 文件大小单位
     * @return -1,表示文件不存在
     */
    public static double getFileSize(String filePath,FileUnit fileUnit){
		File file = new File(filePath);
		if(!file.exists()){
			return 0;
		}
		long size = file.length();
		if(fileUnit == FileUnit.BYTE){
		}else if(fileUnit == FileUnit.KB){
			size = size/1024;
		}else if(fileUnit == FileUnit.MB){
			size = size/1024/1024;
		}else if(fileUnit == FileUnit.GB){
			size = size/1024/1024/1024;
		}else if(fileUnit == FileUnit.TB){
			size = size/1024/1024/1024/1024;
		}
		return size;
	}

	/**
	 * 将文件保存到内部存储APP名字下
	 * @param context
	 * @param path 文件路径
	 * @return 保存路径
	 * @throws SdcardDisableException
	 */
	public static String saveToAppName(Context context,String folderName,String path) throws SdcardDisableException {
		String fileDir = "";
		String fileName = path.substring(path.lastIndexOf("/"));
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			try {
				int labelRes = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes;
				fileDir = sdPath + File.separator + context.getResources().getString(labelRes) + File.separator + folderName;
			} catch (PackageManager.NameNotFoundException e) {
				e.printStackTrace();
			}

			File f = new File(fileDir);
			//如果文件目录存在返回true,否则false
			if(!f.exists() || !f.isDirectory()){
				//创建一个文件目录(多个目录 +s)
				//创建成功返回true,否则false表示文件已经存在
				boolean mkdirs = f.mkdirs();
				if (!mkdirs){
					throw new SdcardDisableException("创建文件目录失败");
				}
			}
		}else{
			throw new SdcardDisableException("sdcard不存在或不可用");
		}
		return fileDir + fileName;
	}
}













