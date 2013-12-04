package org.marker.screen.tools;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
/**
 * <p>构造可转移的数据类型，方便转移数据到剪切板</p>
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
public class MouseTransferable implements Transferable {

	private Image image;
	
	public MouseTransferable(Image image){
		this.image = image;
	}
	
	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if (isDataFlavorSupported(flavor))
            return image;
		throw new UnsupportedFlavorException(flavor);  
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] { DataFlavor.imageFlavor };
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return DataFlavor.imageFlavor.equals(flavor);
	}

}
