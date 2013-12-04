package org.marker.screen.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import org.marker.screen.PrintScreen;
import org.marker.screen.tools.Direction;
import org.marker.screen.tools.Tools;

/**
 * <p>
 * 绘制空心矩形和灰暗效果的对象
 * </p>
 * 
 * @author 吴伟
 * @Date 2011-12-25
 * @Description 模块描述
 * @version 1.00 2011/12/25 吴伟
 *          <p>
 *          9.99 9999/99/99 修改者姓名 修改内容说明
 *          </p>
 *          <p>
 *          9.99 9999/99/99 修改者姓名 修改内容说明
 *          </p>
 * @see 参考类1
 * @see 参考类2
 */
public class Rect {

	private int screenWidth = Tools.SCREEN_WIDTH;// 屏幕的宽度
	private int screenHeight = Tools.SCREEN_HEIGHT;// //屏幕的高度
	private PrintScreen ps;
 
	
	private int lux;
	private int luy;
	private int rdx;
	private int rdy;
	private int width;// 宽度
	private int height;// 高度

	
	private int dot = 5;// 移动线条节点

	
	// 绘制各个方向的可移动点
	private int lxd;// 左x点
	private int uyd;// 上y点
	private int rxd;// 右x点
	private int dyd;// 下y点
	private int cxd;// 中x点
	private int cyd;// 中y点
	
	//鼠标 和 两点临时位置信息
	private int mXTemp;
	private int mYTemp;
	private Point tdATemp;
	private Point tdBTemp;
	
	public Direction direction = Direction.STOP;//鼠标拖动方向
	private boolean xuandingtime = true; //第一步：选定
	private int mouseKeyCode;//鼠标按键
	
	
	
	private Cursor NW = new Cursor(Cursor.NW_RESIZE_CURSOR);
	private Cursor  W = new Cursor(Cursor.W_RESIZE_CURSOR );
	private Cursor SW = new Cursor(Cursor.SW_RESIZE_CURSOR);
	private Cursor NE = new Cursor(Cursor.NE_RESIZE_CURSOR);
	private Cursor  E = new Cursor(Cursor.E_RESIZE_CURSOR );
	private Cursor SE = new Cursor(Cursor.SE_RESIZE_CURSOR);
	private Cursor  S = new Cursor(Cursor.N_RESIZE_CURSOR );
	private Cursor  D = new Cursor(Cursor.DEFAULT_CURSOR);
	private Cursor  M = new Cursor(Cursor.MOVE_CURSOR);

	
	public Rect(PrintScreen ps){
		this.ps = ps;
	}
	
	
	public void drawRect(Graphics g) {
		Color c = g.getColor();// 获取原色
		g.setColor(Tools.RECT_COLOR);
		
		width  = rdx - lux;
		height = rdy - luy;
		
		g.drawRect(lux, luy, width, height);// 绘制矩形选区

		//计算一些通用的值，避免重复计算，提高执行效率。
		lxd = lux - 2;// 左x点
		uyd = luy - 2;// 上y点
		rxd = rdx - 2;// 右x点
		dyd = rdy - 2;// 下y点
		cxd = (lux+rdx-1)>>1;// 中x点
		cyd = (luy+rdy-1)>>1;// 中y点
		
		// 绘制各个方向的可移动点
		g.fillRect(lxd, uyd, dot, dot);// left-up
		g.fillRect(lxd, dyd, dot, dot);// left-down
		g.fillRect(rxd, uyd, dot, dot);// right-up
		g.fillRect(rxd, dyd, dot, dot);// right-down
		g.fillRect(lxd, cyd, dot, dot);// left
		g.fillRect(rxd, cyd, dot, dot);// right
		g.fillRect(cxd, uyd, dot, dot);// up
		g.fillRect(cxd, dyd, dot, dot);// down

		
		//绘制灰色透明区域
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) 0.32));
		g2d.fillRect(0, luy, lux, height);// left
		g2d.fillRect(0, 0, screenWidth, luy);// up
		g2d.fillRect(0, luy + height, screenWidth, screenHeight - luy - height);// down
		g2d.fillRect(lux + width, luy, screenWidth - lux - width, height);// rigcxht

		//绘制文字提示信息
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.7));
		g2d.setColor(Color.BLACK);
		String text = "分辨率: " + width + " * " + height;
		g2d.fillRect(lux, luy - 15, text.length() * 7 + 10, 15);
		g.setColor(Color.WHITE);
		g2d.drawString(text, lux + 3, luy - 4);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
		g.setColor(c);// 还原色
	}


	/**
	 * Rectangle
	 * */
	public Rectangle getRect() {
		return new Rectangle(lux, luy, rdx-lux, rdy-luy);
	}
	
	
	/**
	 * 只是判断了鼠标的状态
	 * */
	public void mouseMoved(MouseEvent e) {
		if(!xuandingtime){//如果选定区域结束
			int x = e.getX();
			int y = e.getY();
			
			//确定节点方向
			if( x >= lxd && x <= lxd+dot && y >= uyd-2 && y <= uyd+dot){
				direction = Direction.LEFT_UP;
			}else if( x >= lxd && x <= lxd+dot && y >= dyd && y <= dyd+dot){
				direction = Direction.LEFT_DOWN;
			}else if( x >= lxd && x <= lxd+dot && y >= cyd && y <= cyd+dot){
				direction = Direction.LEFT;
			}else if( x >= rxd && x <= rxd+dot && y >= uyd && y <= uyd+dot){
				direction = Direction.RIGHT_UP;
			}else if( x >= rxd && x <= rxd+dot && y >= dyd && y <= dyd+dot){
				direction = Direction.RIGHT_DOWN;
			}else if( x >= rxd && x <= rxd+dot && y >= cyd && y <= cyd+dot){
				direction = Direction.RIGHT;
			}else if( x >= cxd && x <= cxd+dot && y >= uyd && y <= uyd+dot){
				direction = Direction.UP;
			}else if( x >= cxd && x <= cxd+dot && y >= dyd && y <= dyd+dot){
				direction = Direction.DOWN;
			}else if( x > lxd+dot && x < rxd   && y > uyd+dot && y < dyd){
				direction = Direction.AT_CENTER;
			}else{
				direction = Direction.STOP;
			}
			
			//根据方向信息设置鼠标状态
			switch(direction){
			case LEFT_UP   : ps.setCursor(NW);break;
			case LEFT      : ps.setCursor(W);break;
			case LEFT_DOWN : ps.setCursor(SW);break;
			case RIGHT_UP  : ps.setCursor(NE);break;
			case RIGHT     : ps.setCursor(E);break;
			case RIGHT_DOWN: ps.setCursor(SE);break;
			case UP        : ps.setCursor(S);break;
			case DOWN      : ps.setCursor(S);break;
			default        : ps.setCursor(D);
			}
		}
	}
	
	
	///设置位置
	public void mouseDragged(MouseEvent e) {
		if(mouseKeyCode == 1){
			if(xuandingtime){
				rdx = e.getX();
				rdy = e.getY();
			}else{
				//根据拖动方向，改变两点位置信息
				int x = e.getX();
				int y = e.getY();
				
				switch(direction){
				case LEFT  : lux = x; break;
				case RIGHT : rdx = x; break;
				case UP    : luy = y; break;
				case DOWN  : rdy = y; break;
				case LEFT_UP:    lux = x; luy = y; break;
				case LEFT_DOWN:	 lux = x; rdy = y; break;
				case RIGHT_UP:   rdx = x; luy = y; break;
				case RIGHT_DOWN: rdx = x; rdy = y; break;
				case AT_CENTER ://移动整个矩形区域
					lux = tdATemp.x + (x - mXTemp);
					luy = tdATemp.y + (y - mYTemp);
					rdx = tdBTemp.x + (x - mXTemp);
					rdy = tdBTemp.y + (y - mYTemp);
					break;
				default: //默认是拖拽中
					rdx = e.getX();
					rdy = e.getY();
				}
				
				//验证选区是否越界
				if( lux < 0) lux = 0;
				if( luy < 0) luy = 0;
				if( rdx > screenWidth)  rdx = screenWidth;
				if( rdy > screenHeight) rdy = screenHeight;
			}
		}
		//判断矩形是否反向，相反就复位
		if( lux > rdx ) rdx = lux + 1;
		if( luy > rdy ) rdy = luy + 1;
	}
	
	public void mousePressed(MouseEvent e) {
		mouseKeyCode = e.getButton();//获取按键值，方便判断拖拽
		if(mouseKeyCode == 1){//左键
			if(xuandingtime){//选定开始
				lux = e.getX();//设置左边点X
				luy = e.getY();//设置左边点Y
			}else{//移动矩形选区
				if(direction == Direction.AT_CENTER) ps.setCursor(M);
				mXTemp  = e.getX();
				mYTemp  = e.getY();
				tdATemp = new Point(lux,luy);
				tdBTemp = new Point(rdx,rdy);
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {//鼠标按键弹起
		if(mouseKeyCode == 1){//左键
			if(xuandingtime){
				xuandingtime = !xuandingtime;//结束选定
			}else{
				ps.setCursor(D);//设置鼠标默认状态
			}
		}
		
	}
}
