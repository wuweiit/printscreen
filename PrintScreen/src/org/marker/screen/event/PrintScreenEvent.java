package org.marker.screen.event;

import java.awt.Image;
import org.marker.screen.PrintScreen;

/**
 * <p>PrintScreen事件类，可以重事件里获取到选区的图片，当点击菜单完成时候产生此事件。</p>
 * 
 * @author  吴伟
	  * @Date    2012-04-01
 * @Description 模块描述
 * @version 2.00 2012/04/01 吴伟
 * <p>      9.99 9999/99/99 修改者姓名 修改内容说明</p>
 * <p>      9.99 9999/99/99 修改者姓名 修改内容说明</p>
 * @see     参考类1
 * @see     参考类2
 */
public class PrintScreenEvent{

	//存截取的图片
	private Image screenImage;
	
	
	/**
	 * 构造方法，用来初始化事件里的数据。
	 * @param PrintScreen 数据源
	 * */
	public PrintScreenEvent(PrintScreen ps){
		screenImage = ps.getScreenImage();
	}
	
	

	/**
	 * 获取屏幕截图
	 * @author 吴伟
	 * @return Image 截图
	 * */
	public Image getScreenImage(){
		return screenImage;
	}
}
