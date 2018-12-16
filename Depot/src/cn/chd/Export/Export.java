
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
 * ����ʱ�䣺2018��10��28�� ����10:59:25
 * 
 * ��Ŀ���ƣ�Depot
 * 
 * @author homrls
 * @version 1.0
 * @since JDK 1.8.0_21 �ļ����ƣ�Export.java ��˵����
 */

public class Export {
	private List<Record> list = new ArrayList<Record>();

	public Export(List<cn.chd.Export.Record> rList) {
		this.list = rList;
	}

	public void exportToExcel() throws RowsExceededException, WriteException, IOException {

		// 1. ����Excel��·��
		String filePath = "F:\\JavaWorkspace\\Depot\\cc.xls";
		WritableWorkbook wwb = null;
		try {
			wwb = Workbook.createWorkbook(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ����Excel���"ѧ��"���������
		WritableSheet sheet = wwb.createSheet("ͣ����¼", 0);// ����rwb.getSheet(0)��ȡ��һ������
		sheet.addCell(new Label(0, 0, "���ƺ�"));
		sheet.addCell(new Label(1, 0, "���ʱ��"));
		sheet.addCell(new Label(2, 0, "����ʱ��"));
		sheet.addCell(new Label(3, 0, "����"));
		for (int i = 1; i < list.size(); i++) {
			// Number��Ӧ���ݿ��int��������
			sheet.addCell(new Label(0, i, list.get(i).getCarNumber()));
			// Label��Ӧ���ݿ�String��������
			sheet.addCell(new Label(1, i, list.get(i).getStartTime()));
			sheet.addCell(new Label(2, i, list.get(i).getEndTime()));
			sheet.addCell(new Label(3, i, new Double(list.get(i).getFee()).toString()));
		}
		wwb.write();

		wwb.close();
	}
}
