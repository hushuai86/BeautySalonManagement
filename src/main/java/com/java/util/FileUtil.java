package com.java.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;

public class FileUtil {
	/**
	 * 将对应比特流转换为一个文件
	 * @param buf 比特流
	 * @param filePath 文件路径
	 * @param fileName 文件名
	 */
	public static byte[] byteToFile(byte[] buf, String filePath, String fileName)  
    {  
        BufferedOutputStream bufferOut = null;  
        FileOutputStream fileOut = null;  
        File file =null;
        
        byte[] byteFile=null;
        try{  
            File resFile = new File(filePath);  
            if (!resFile.exists()){  
                resFile.mkdirs();  
            }  
            file = new File(filePath + File.separator + fileName); 
            
            //读取压缩后的文件的byte
            String path=filePath + File.separator + fileName+".jpg";
            FileSystemResource fsr=new FileSystemResource(path);
            byteFile=FileCopyUtils.copyToByteArray(fsr.getFile());
        }catch (Exception e){  
            e.printStackTrace();  
        }finally{  
            if (bufferOut != null){  
                try{  
                    bufferOut.close();  
                }  
                catch (IOException e){  
                    e.printStackTrace();  
                }  
            }  
            if (fileOut != null){  
                try{  
                    fileOut.close();  
                }  
                catch (IOException e){  
                    e.printStackTrace();  
                }  
            }  
        }          
        return byteFile;
    } 
	//不压缩
	public static void byteToFile2(byte[] buf, String filePath, String fileName)  
    {  
		BufferedOutputStream bufferOut = null;  
        FileOutputStream fileOut = null;  
        File file = null;  
        try{  
            File resFile = new File(filePath);  
            if (!resFile.exists()){  
                resFile.mkdirs();  
            }  
            file = new File(filePath + File.separator + fileName);  
            fileOut = new FileOutputStream(file);  
            bufferOut = new BufferedOutputStream(fileOut);  
            bufferOut.write(buf);  
        }catch (Exception e){  
            e.printStackTrace();  
        }finally{  
            if (bufferOut != null){  
                try{  
                    bufferOut.close();  
                }  
                catch (IOException e){  
                    e.printStackTrace();  
                }  
            }  
            if (fileOut != null){  
                try{  
                    fileOut.close();  
                }  
                catch (IOException e){  
                    e.printStackTrace();  
                }  
            }  
        }          
        
    } 
	/**
	 * 判断文件是否存在
	 * @param path 路径 
	 * @param name 文件名
	 * @return 
	 */
	public static Boolean isExistence(String path,String name){
		if(!new File(path).exists()){
			new File(path).mkdirs();
		}
		return new File(path+"/"+name).exists();
	}
	
	
}
