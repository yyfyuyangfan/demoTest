package com.sxz.maxbi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Object2ByteArray {


	public static void main(String[] args) {
		try {
			Person person=new Person("userName", "password", "phone", "email", "sex", "age");
			System.out.println("person:"+person);
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(bos);
			oos.writeObject(person);
			//得到person对象的byte数组
			byte[] personByteArray = bos.toByteArray();
			System.out.println("before compress:"+personByteArray.length);
			//将byte数据压缩
			byte[] zipPersonByteArray = compress(personByteArray);
			System.out.println("after compress:"+zipPersonByteArray.length);
			closeStream(oos);
			closeStream(bos);
			//从byte数组中还原person对象
			ByteArrayInputStream bin=new ByteArrayInputStream(personByteArray);
			ObjectInputStream ois=new ObjectInputStream(bin);
			Person restorePerson = (Person) ois.readObject();
			System.out.println(restorePerson);
			closeStream(ois);
			closeStream(bin);
			//从压缩的byte数组中还原person对象
			byte[] unCompressByte = unCompress(zipPersonByteArray);
			ByteArrayInputStream zipBin=new ByteArrayInputStream(unCompressByte);
			ObjectInputStream zipOis=new ObjectInputStream(zipBin);
			Person zipBytePerson=(Person) zipOis.readObject();
			System.out.println("compress person:"+zipBytePerson.toString());
			closeStream(zipOis);
			closeStream(zipBin);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void closeStream(Closeable oStream){
		if(null!=oStream){
			try {
				oStream.close();
			} catch (IOException e) {
				oStream=null;//赋值为null,等待垃圾回收
				e.printStackTrace();
			}
		}
	}
	 public static byte[] compress(byte[] bt){
	        //将byte数据读入文件流
	        ByteArrayOutputStream bos=null;
	        GZIPOutputStream gzipos=null;
	        try {
	            bos=new ByteArrayOutputStream();
	            gzipos=new GZIPOutputStream(bos);
	            gzipos.write(bt);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	            closeStream(gzipos);
	            closeStream(bos);
	        }
	        return bos.toByteArray();
	    }


		public static byte[] unCompress(byte[] bt){
			//byte[] unCompress=null;
			ByteArrayOutputStream byteAos=null;
			ByteArrayInputStream byteArrayIn=null;
			GZIPInputStream gzipIn=null;
			try {
				byteArrayIn=new ByteArrayInputStream(bt);
				gzipIn=new GZIPInputStream(byteArrayIn);
				byteAos=new ByteArrayOutputStream();
				byte[] b=new byte[4096];
				int temp = -1;
				while((temp=gzipIn.read(b))>0){
					byteAos.write(b, 0, temp);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}finally{
				closeStream(byteAos);
				closeStream(gzipIn);
				closeStream(byteArrayIn);
			}
			return byteAos.toByteArray();
		}
	}
