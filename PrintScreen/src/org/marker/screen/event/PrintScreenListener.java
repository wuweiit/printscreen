package org.marker.screen.event;


/**
 * <p>嵌入PrintScreen的项目必须实现此接口才能产生回调机制获取截区图片</p>
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
public interface PrintScreenListener {

	/**
	 * 获取图片后的操作方法
	 * @param PrintScreenEvent 截图事件
	 * */
	public void PrintScreenSaved(PrintScreenEvent e);
	
}
