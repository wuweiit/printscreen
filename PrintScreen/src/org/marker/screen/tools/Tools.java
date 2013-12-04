package org.marker.screen.tools;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * <p>工具类</p>
 * 
 * @author  吴伟
	  * @Date    2011-12-25
 * @Description 模块描述
 * @version 1.00 2011/12/25 吴伟
 * <p>      9.99 9999/99/99 修改者姓名 修改内容说明</p>
 * <p>      9.99 9999/99/99 修改者姓名 修改内容说明</p>
 * @see     参考类1
 * @see     参考类2
 */
public class Tools {
	
	//获取的屏幕 宽度（screenWidth） 和 高度（screenHeight）
	public static final int  SCREEN_WIDTH  = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int  SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	//系统剪切板
	public static Clipboard clipboard =  Toolkit.getDefaultToolkit().getSystemClipboard();
	/**
	 * 获取当前时间
	 * @return String
	 * */
	public static String getLocalDate(){
		return  new SimpleDateFormat("yyyymmddhhss").format(new Date());
	}
	//
	public static final Rectangle SCREEN_RECTANGLE = new Rectangle(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
	
	public static final Color RECT_COLOR = Color.BLUE;
	
}
