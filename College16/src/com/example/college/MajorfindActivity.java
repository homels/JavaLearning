package com.example.college;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MajorfindActivity extends Activity {
	private String[] names=new String[]{"电子信息类","生物技术类","现代医药类","物流运输类","新材料类","环境能源类","管理类","法学类","计算机类","土木工程类","传媒类","政治文化类","道路交通类","语言类"};
	private String[] descs=new String[]{"电子信息类","生物技术类","现代医药类","物流运输类","新材料类","环境能源类","管理类","法学类","计算机类","土木工程类","传媒类","政治文化类","道路交通类","语言类"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_majorfind);
		List<Map<String,Object>> listItems= new ArrayList<Map<String,Object>>();
		for(int i=0;i<names.length;i++)
		{
			Map<String,Object> listItem = new HashMap<String,Object>();
			listItem.put("personName", names[i]);
			listItem.put("desc", descs[i]);
			listItems.add(listItem);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.simple_item,new String[]{"personName","desc"},new int[]{R.id.name,R.id.desc});
		ListView list = (ListView)findViewById(R.id.mylist);
		list.setAdapter(simpleAdapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent,View view,int position,long id)
			{
				DDialog(position);
			}
		});
	}
	public void SchoolName()
	{
		String[] schoolname=new String[]{"北京大学","清华大学","复旦大学","长安大学","上海交通大学","西安交通大学","北京师范大学"};
	}
	public void DDialog(int i)
	{
		switch(i)
		{
		case 0:
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
				.setTitle("电子信息类")
				.setIcon(R.drawable.ic_launcher)
				.setMessage("电子信息工程是一门应用计算机等现代化技术进行电子信息控制和信息处理的学科，主要研究信息的获取与处理，电子设备与信息系统的设计、开发、应用和集成");
				setPositiveButton(builder).create()
				.show();
				break;
		case 1:
			AlertDialog.Builder builder1 = new AlertDialog.Builder(this)
			.setTitle("生物技术类")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("生物技术（biotechnology），是指人们以现代生命科学为基础，结合其他基础科学的科学原理，采用先进的科学技术手段，按照预先的设计改造生物体或加工生物原料，为人类生产出所需产品或达到某种目的。");
			setPositiveButton(builder1).create()
			.show();
			break;
		case 2:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this)
			.setTitle("现代医药类")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("制药工程是一个化学、生物学、药学（中药学）和工程学交叉的工科类专业，以培养从事药品研发制造，新工艺、新设备、新品种的开发、放大和设计人才为目标。");
			setPositiveButton(builder2).create()
			.show();
			break;
		case 3:
			AlertDialog.Builder builder3 = new AlertDialog.Builder(this)
			.setTitle("物流运输类")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("物流运输技术主要包括运输设施和运输作业两大类，前者属于运输硬技术，后者属于运输软技术。运输硬技术主要包括运输基础设施，如公路，铁路，海运，运输车等基础设施的完善，运输软技术则包括管理方法，物流技术，物流人员素养等");
			setPositiveButton(builder3).create()
			.show();
			break;
		case 4:
			AlertDialog.Builder builder4 = new AlertDialog.Builder(this)
			.setTitle("新材料类")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("新材料是指新近发展的或正在研发的、性能超群的一些材料，具有比传统材料更为优异的性能。近年来，新材料越来越受重视，国家也重视新材料产业发展。");
			setPositiveButton(builder4).create()
			.show();
			break;
		case 5:
			AlertDialog.Builder builder5 = new AlertDialog.Builder(this)
			.setTitle("环境能源类")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("能源环境是指社会生产发展和经济生活所依赖的主要能源的自然状态。因为能源的种类、性质、分布及其储量是反映人类环境特征的重要因素，故美国学者里夫金与霍华德提出这一概念。");
			setPositiveButton(builder5).create()
			.show();
			break;
		case 6:
			AlertDialog.Builder builder6 = new AlertDialog.Builder(this)
			.setTitle("管理类")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("管理学是一门综合性的交叉学科，是系统研究管理活动的基本规律和一般方法的科学。管理学是适应现代社会化大生产的需要产生的，它的目的是：研究在现有的条件下，如何通过合理的组织和配置人、财、物等因素，提高生产力的水平。");
			setPositiveButton(builder6).create()
			.show();
			break;
		case 7:
			AlertDialog.Builder builder7 = new AlertDialog.Builder(this)
			.setTitle("法学类")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("法学，又称法律学、法律科学，是以法律、法律现象以及其规律性为研究内容的科学，它是研究与法相关问题的专门学问，是关于法律问题的知识和理论体系");
			setPositiveButton(builder7).create()
			.show();
			break;
		case 8:
			AlertDialog.Builder builder8 = new AlertDialog.Builder(this)
			.setTitle("计算机类")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("计算机相关专业指在开设学科是以软件和系统开发方向主的计算机相关学科。计算机技术具有明显的综合特性，它与电子工程、应用物理、机械工程、现代通信技术和数学等紧密结合，发展迅速");
			setPositiveButton(builder8).create()
			.show();
			break;
		case 9:
			AlertDialog.Builder builder9 = new AlertDialog.Builder(this)
			.setTitle("土木工程类")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("它既指所应用的材料、设备和所进行的勘测、设计、施工、保养、维修等技术活动，也指工程建设的对象。即建造在地上或地下、陆上或水中 ，直接或间接为人类生活、生产、军事、科研服务的各种工程设施，例如房屋、道路、铁路、管道、隧道、桥梁、运河、堤坝、港口、电站、飞机场、海洋平台、给水排水以及防护工程等");
			setPositiveButton(builder9).create()
			.show();
			break;
		case 10:
			AlertDialog.Builder builder10 = new AlertDialog.Builder(this)
			.setTitle("传媒类")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("学校前身是创办于1946年的东北行政学院，1950年更名为东北人民大学；1952年经院系调整成为中国共产党亲手创建的第一所综合性大学；1958年更名为吉林大学；1960年，吉林大学被国务院列为国家重点大学；2000年6月12日，合并吉林工业大学、白求恩医科大学、长春科技大学等6所院校组建新的吉林大学。");
			setPositiveButton(builder10).create()
			.show();
			break;
		default:
			AlertDialog.Builder builder11 = new AlertDialog.Builder(this)
			.setTitle("  ")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("尚未添加，点击确定反馈管理员添加信息");
			setPositiveButton(builder11).create()
			.show();
			break;
		}
		
	}
	private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder)
	{
		return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
