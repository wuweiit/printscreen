package org.marker.screen.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;

import org.marker.screen.tools.Tools;


/**
 * 放大镜位置
 * */
public class ScreenGamer {
	
	private int screenWidth  = Tools.SCREEN_WIDTH;//屏幕的宽度
	
	//放大镜的左右两个位置
	private int[][] frame = 
		{
			{200,50},
			{screenWidth-320,50}
		};
	private int frameX = 0;//放大镜的X位置
	private int frameY = 0;//放大镜的Y位置

	private int x = 0;//鼠标位置
	private int y = 0;//鼠标位置
	
	private int frameSize = 30;//实际截取大小为61像素
	private int multiple  = 2;//放大倍数
	private int framebigSize = 60*multiple;//放大镜大小
	
	
	/**
	 * 放大镜构造方法
	 * */
	public ScreenGamer(){}
	
	
	/**
	 * 绘制放大内容
	 * */
	public void drawGamer(Graphics g, Image imageCache){
  	  	Color c = g.getColor();//获取画笔颜色
		//绘制位置Camer
  	  	if( x > screenWidth>>1){
  	  		frameX = frame[1][0];
  	  		frameY = frame[1][1];
  	  	}else{
  	  		frameX = frame[0][0];
  	  		frameY = frame[0][1];
  	  	}
  	  	g.drawImage(imageCache,frameX,frameY,frameX+framebigSize,frameY+framebigSize,x-frameSize,y-frameSize,x+frameSize,y+frameSize,null);
  	  	g.setColor(Color.red);
  	  	
  	  	//绘制十字架
  	  	Graphics2D g2d = (Graphics2D) g;
  	  	g2d.setStroke(new BasicStroke(multiple));//设置线宽为2.0
  	  	g.drawLine(frameX, frameY+(framebigSize>>1), frameX+framebigSize, frameY+(framebigSize>>1));
  	  	g.drawLine(frameX+(framebigSize>>1), frameY, frameX+(framebigSize>>1), frameY+framebigSize);
  	  	g.setColor(c);//还原画笔颜色
	}
	
	/**
	 * 获取鼠标拖拽时的XY坐标
	 * */
	public void mouseDragged(MouseEvent e){
		x = e.getX();
		y = e.getY();
	}
}
