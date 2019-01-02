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

public class SchoolfindActivity extends Activity {

	private String[] names=new String[]{"北京大学","清华大学","复旦大学","长安大学","上海交通大学","西安交通大学","武汉大学","浙江大学","南京大学","中国人民大学","吉林大学","华中科技大学","四川大学","中山大学","南开大学","中国科学技术大学","北京师范大学","西安电子科技大学","北京理工大学","天津大学","南开大学","武汉大学","哈尔滨工业大学"};
	private String[] descs=new String[]{"北京大学","清华大学","复旦大学","长安大学","上海交通大学","西安交通大学","武汉大学","浙江大学","南京大学","中国人民大学","吉林大学","华中科技大学","四川大学","中山大学","南开大学","中国科学技术大学","北京师范大学","西安电子科技大学","北京理工大学","天津大学","南开大学","武汉大学","哈尔滨工业大学"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schoolfind);
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
		String[] schoolname=new String[]{"北京大学","清华大学","复旦大学","长安大学","上海交通大学","西安交通大学","北京师范大学","西安电子科技大学","北京理工大学","天津大学","南开大学","武汉大学","哈尔滨工业大学"};
	}
	public void DDialog(int i)
	{
		switch(i)
		{
		case 0:
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
				.setTitle("北京大学")
				.setIcon(R.drawable.ic_launcher)
				.setMessage("北京大学，简称“北大”，诞生于1898年，初名京师大学堂，是中国近代第一所国立大学，也是第一个以“大学”之名创办的学校，其成立标志着中国近代高等教育的开端。");
				setPositiveButton(builder).create()
				.show();
				break;
		case 1:
			AlertDialog.Builder builder1 = new AlertDialog.Builder(this)
			.setTitle("清华大学")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("清华大学，简称“清华”，由中华人民共和国教育部直属，中央直管副部级建制的全国重点大学，位列“双一流”[1]  、“211工程”、“985工程”，入选“珠峰计划”、“2011计划”、“111计划”、“卓越工程师教育培养计划”、“卓越法律人才教育培养计划”、“卓越医生教育培养计划”");
			setPositiveButton(builder1).create()
			.show();
			break;
		case 2:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this)
			.setTitle("复旦大学")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("复旦大学创建于1905年，原名复旦公学，是中国人自主创办的第一所高等院校，创始人为中国近代知名教育家马相伯，首任校董为国父孙中山。校名“复旦”二字选自《尚书大传·虞夏传》名句“日月光华，旦复旦兮”，意在自强不息，寄托当时中国知识分子自主办学、教育强国的希望。");
			setPositiveButton(builder2).create()
			.show();
			break;
		case 3:
			AlertDialog.Builder builder3 = new AlertDialog.Builder(this)
			.setTitle("长安大学")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("长安大学（Chang'an University）座落于古都西安，是中华人民共和国教育部直属的全国重点大学，由教育部和交通运输部、国土资源部、住房和城乡建设部、陕西省人民政府“四部一省”共建，是国家首批“双一流”世界一流学科建设高校，是国家首批“211工程”、“985工程优势学科创新平台”、“111计划”、国家建设高水平大学公派研究生项目”和“卓越工程师教育培养计划”重点建设高校。是高水平行业特色大学优质资源共享联盟、中俄交通大学联盟成员高校，是招收留学生和首批招收港、澳、台学生的高校之一，拥有全国高校唯一的汽车综合试验场。");
			setPositiveButton(builder3).create()
			.show();
			break;
		case 4:
			AlertDialog.Builder builder4 = new AlertDialog.Builder(this)
			.setTitle("上海交通大学")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("上海交通大学（Shanghai Jiao Tong University），简称“上海交大”，位于中国直辖市上海，是中华人民共和国教育部直属并与上海市共建的“综合性、研究型、国际化”全国重点大学，也是国家“985工程”、“211工程”重点建设院校，入选“双一流、”“珠峰计划”、“111计划”、“2011计划”、“卓越医生教育培养计划”、“卓越法律人才教育培养计划”、“卓越工程师教育培养计划”、“卓越农林人才教育培养计划”，为九校联盟、中国大学校长联谊会、Universitas 21、21世纪学术联盟的重要成员。");
			setPositiveButton(builder4).create()
			.show();
			break;
		case 5:
			AlertDialog.Builder builder5 = new AlertDialog.Builder(this)
			.setTitle("西安交通大学")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("西安交通大学（Xi'an Jiaotong University）简称“西安交大”，位于古都西安，是教育部直属全国重点综合性研究型大学，是国家“七五”、“八五”首批重点建设高校之一，“211工程”首批重点建设的七所大学之一，“985工程”首批重点建设的九所高校之一，“双一流”战略的36所一流大学A类建设高校之一");
			setPositiveButton(builder5).create()
			.show();
			break;
		case 6:
			AlertDialog.Builder builder6 = new AlertDialog.Builder(this)
			.setTitle("武汉大学")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("武汉大学（Wuhan University），简称“武大”，是一所位于湖北武汉市的综合研究型大学，其办学源头溯源于清朝末期1893年湖广总督张之洞奏请清政府创办的自强学堂，已有一百多年历史，1913年改名国立武昌高等师范学校， 1926年组建国立武昌中山大学，1928年定名国立武汉大学，是民国四大名校之一。1949年新中国成立更名武汉大学沿用至今。");
			setPositiveButton(builder6).create()
			.show();
			break;
		case 7:
			AlertDialog.Builder builder7 = new AlertDialog.Builder(this)
			.setTitle("浙江大学")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("浙江大学（Zhejiang University），简称“浙大”，坐落于“人间天堂”杭州。前身是1897年创建的求是书院，是中国人自己最早创办的新式高等学校之一。1928年更名为国立浙江大学。中华民国时期，浙江大学在竺可桢老校长带领下，崛起为民国最高学府之一，被英国科学史家李约瑟誉为“东方剑桥”，迎来了浙大百年历史中最辉煌的时期。竺可桢老校长因其历史贡献，成为了浙大校史中最伟大的人，并为浙大确立了“求是”校训和文言文《浙江大学校歌》。");
			setPositiveButton(builder7).create()
			.show();
			break;
		case 8:
			AlertDialog.Builder builder8 = new AlertDialog.Builder(this)
			.setTitle("南京大学")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("南京大学（Nanjing University），简称“南大”[1]  ，是教育部直属、中央直管副部级建制的全国重点大学，国家首批“双一流”、“211工程”、“985工程”高校，首批“珠峰计划”、“111计划”、“2011计划”、“卓越计划”实施高校，也是九校联盟、中国大学校长联谊会、环太平洋大学联盟、21世纪学术联盟和东亚研究型大学协会成员。");
			setPositiveButton(builder8).create()
			.show();
			break;
		case 9:
			AlertDialog.Builder builder9 = new AlertDialog.Builder(this)
			.setTitle("中国人民大学")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("中国人民大学前身是1937年成立的陕北公学，以及后来的华北联合大学和华北大学。1949年12月16日，中央人民政府政务院通过了《关于成立中国人民大学的决定》。1950年10月3日，以华北大学为基础合并组建的中国人民大学正式开学，成为新中国创办的第一所新型正规大学。1954年，被确定为以社会科学为主的综合大学和首批全国重点大学；1960年，被确定为综合性全国重点大学；2001年，入选“985工程”。");
			setPositiveButton(builder9).create()
			.show();
			break;
		case 10:
			AlertDialog.Builder builder10 = new AlertDialog.Builder(this)
			.setTitle("吉林大学")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("学校前身是创办于1946年的东北行政学院，1950年更名为东北人民大学；1952年经院系调整成为中国共产党亲手创建的第一所综合性大学；1958年更名为吉林大学；1960年，吉林大学被国务院列为国家重点大学；2000年6月12日，合并吉林工业大学、白求恩医科大学、长春科技大学等6所院校组建新的吉林大学。");
			setPositiveButton(builder10).create()
			.show();
			break;
		default:
			AlertDialog.Builder builder11 = new AlertDialog.Builder(this)
			.setTitle("大学")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("尚未添加，点击确定反馈管理员添加学习信息");
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
