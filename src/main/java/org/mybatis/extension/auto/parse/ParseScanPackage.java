package org.mybatis.extension.auto.parse;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ParseScanPackage {

	/**
	 * 获取某包下（包括该包的所有子包）所有类
	 * 
	 * @param packageName
	 *            包名
	 * @return 类的完整名称
	 */
	public static List<Class<?>> getClassName(String packageName) {
		return getClassName(packageName, true);
	}

	/**
	 * 获取某包下所有类
	 * 
	 * @param packageName
	 *            包名
	 * @param childPackage
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	public static List<Class<?>> getClassName(String packageName,
			boolean childPackage) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		try {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			String packagePath = packageName.replace(".", "/");
			URL url = classLoader.getResource(packagePath);
			if (url != null) {
				String type = url.getProtocol();
				String path = url.toURI().getPath();
				if (type.equals("file")) {
					classes = getClassNameByFile(path, childPackage);
				} else if (type.equals("jar")) {
					classes = getClassNameByJar(path, childPackage);
				}
			} else {
				classes = getClassNameByJars(
						((URLClassLoader) classLoader).getURLs(), packagePath,
						childPackage);
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classes;
	}

	/**
	 * 从项目文件获取某包下所有类
	 * 
	 * @param filePath
	 *            文件路径
	 * @param className
	 *            类名集合
	 * @param childPackage
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	private static List<Class<?>> getClassNameByFile(String filePath,
			boolean childPackage) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		File file = new File(filePath);
		if (!file.exists()) {
			return classes;
		}
		try {
			File[] childFiles = file.listFiles();
			for (File childFile : childFiles) {
				if (childFile.isDirectory()) {
					if (childPackage) {
						classes.addAll(getClassNameByFile(childFile.getPath(),
								childPackage));
					}
				} else {
					String childFilePath = childFile.getPath();
					if (childFilePath.endsWith(".class")) {
						childFilePath = childFilePath.substring(
								childFilePath.indexOf("\\classes") + 9,
								childFilePath.lastIndexOf("."));
						childFilePath = childFilePath.replace("\\", ".");
						classes.add(Class.forName(childFilePath));
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return classes;
	}

	/**
	 * 从jar获取某包下所有类
	 * 
	 * @param jarPath
	 *            jar文件路径
	 * @param childPackage
	 *            是否遍历子包
	 * @return 类的完整名称
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static List<Class<?>> getClassNameByJar(String jarPath,
			boolean childPackage) throws ClassNotFoundException, IOException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		String[] jarInfo = jarPath.split("!");
		String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
		String packagePath = jarInfo[1].substring(1);
		JarFile jarFile = new JarFile(jarFilePath);
		Enumeration<JarEntry> entrys = jarFile.entries();
		while (entrys.hasMoreElements()) {
			JarEntry jarEntry = entrys.nextElement();
			String entryName = jarEntry.getName();
			if (entryName.endsWith(".class")) {
				if (childPackage) {
					if (entryName.startsWith(packagePath)) {
						entryName = entryName.replace("/", ".").substring(0,
								entryName.lastIndexOf("."));
						classes.add(Class.forName(entryName));
					}
				} else {
					int index = entryName.lastIndexOf("/");
					String myPackagePath;
					if (index != -1) {
						myPackagePath = entryName.substring(0, index);
					} else {
						myPackagePath = entryName;
					}
					if (myPackagePath.equals(packagePath)) {
						entryName = entryName.replace("/", ".").substring(0,
								entryName.lastIndexOf("."));
						classes.add(Class.forName(entryName));
					}
				}
			}
		}
		return classes;
	}

	/**
	 * 从所有jar中搜索该包，并获取该包下所有类
	 * 
	 * @param urls
	 *            URL集合
	 * @param packagePath
	 *            包路径
	 * @param childPackage
	 *            是否遍历子包
	 * @return 类的完整名称
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static List<Class<?>> getClassNameByJars(URL[] urls,
			String packagePath, boolean childPackage)
			throws URISyntaxException, ClassNotFoundException, IOException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		if (urls != null) {
			for (URL url : urls) {
				String urlPath = url.toURI().getPath();
				// 不必搜索classes文件夹
				if (urlPath.endsWith("classes/")) {
					continue;
				}
				String jarPath = urlPath + "!/" + packagePath;
				classes.addAll(getClassNameByJar(jarPath, childPackage));
			}
		}
		return classes;
	}

	public static void main(String[] args) {
		for (Class<?> clazz : getClassName("demo.*.pojo")) {
			System.out.println(clazz.getName());
		}
	}
}
