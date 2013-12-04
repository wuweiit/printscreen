package org.marker.screen.tools;

import java.io.File;
 

public class FileFilter extends javax.swing.filechooser.FileFilter {

	String ends; // 文件后缀
	String description; // 文件描述文字

	public FileFilter(String ends, String description) { // 构造函数
		this.ends = ends; // 设置文件后缀
		this.description = description; // 设置文件描述文字
	}

	/**
	 * 接受显示的文件格式
	 * 
	 * */
	public boolean accept(File file) {
		if (file.isDirectory()) return true;//如果是目录
		return file.getName().toUpperCase().endsWith(this.ends.toUpperCase());
	}

	/** 
	 * 
	 * 返回这个扩展名过滤器的描述
	 * 
	 * 
	 * 
	 * */
	public String getDescription() {
		return this.description;
	}

	// 返回这个扩展名过滤器的扩展名
	public String getEnds() {
		return this.ends;
	}
}
