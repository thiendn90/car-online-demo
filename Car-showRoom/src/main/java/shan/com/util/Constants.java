package shan.com.util;

import javassist.LoaderClassPath;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class Constants
{
	public static final String APPLICATION_NAME = "ThymeLeaf Demo";
//	get param from project
	public static ResourceBundle customerConfig = ResourceBundle.getBundle("car-customer-config");
	public static ResourceBundle urlBundle = ResourceBundle.getBundle("url");
//	get param from file
//	<begin>
//	static {
//		File file = new File("/etc/car-properties");
//		URL [] urls = new URL[0];
//		try{
//			urls = new URL[]{file.toURI().toURL()};
//		}catch (MalformedURLException e){
//			e.printStackTrace();
//		}
//		ClassLoader loader = new URLClassLoader(urls);
//		customerConfig = ResourceBundle.getBundle("car-customer-config", Locale.getDefault(),loader);
//	}
//	<end>
}
