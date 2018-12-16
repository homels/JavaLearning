
package cn.chd.Export;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import cn.chd.Export.Record;

/**
 * 创建时间：2018年10月28日 上午10:59:25
 * 
 * 项目名称：Depot
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 文件名称：Export.java 类说明：
 */

public class Export {
	private List<Record> list = new ArrayList<Record>();

	public Export(List<cn.chd.Export.Record> rList) {
		this.list = rList;
	}

	public void exportToExcel() throws RowsExceededException, WriteException, IOException {

		// 1. 导出Excel的路径
		String filePath = "F:\\JavaWorkspace\\Depot\\cc.xls";
		WritableWorkbook wwb = null;
		try {
			wwb = Workbook.createWorkbook(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 创建Excel表的"学生"区域的数据
		WritableSheet sheet = wwb.createSheet("停车记录", 0);// 或者rwb.getSheet(0)获取第一个区域
		sheet.addCell(new Label(0, 0, "车牌号"));
		sheet.addCell(new Label(1, 0, "入库时间"));
		sheet.addCell(new Label(2, 0, "出库时间"));
		sheet.addCell(new Label(3, 0, "费用"));
		for (int i = 1; i < list.size(); i++) {
			// Number对应数据库的int类型数据
			sheet.addCell(new Label(0, i, list.get(i).getCarNumber()));
			// Label对应数据库String类型数据
			sheet.addCell(new Label(1, i, list.get(i).getStartTime()));
			sheet.addCell(new Label(2, i, list.get(i).getEndTime()));
			sheet.addCell(new Label(3, i, new Double(list.get(i).getFee()).toString()));
		}
		wwb.write();

		wwb.close();
	}
}
