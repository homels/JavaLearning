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
	private String[] names=new String[]{"������Ϣ��","���＼����","�ִ�ҽҩ��","����������","�²�����","������Դ��","������","��ѧ��","�������","��ľ������","��ý��","�����Ļ���","��·��ͨ��","������"};
	private String[] descs=new String[]{"������Ϣ��","���＼����","�ִ�ҽҩ��","����������","�²�����","������Դ��","������","��ѧ��","�������","��ľ������","��ý��","�����Ļ���","��·��ͨ��","������"};
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
		String[] schoolname=new String[]{"������ѧ","�廪��ѧ","������ѧ","������ѧ","�Ϻ���ͨ��ѧ","������ͨ��ѧ","����ʦ����ѧ"};
	}
	public void DDialog(int i)
	{
		switch(i)
		{
		case 0:
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
				.setTitle("������Ϣ��")
				.setIcon(R.drawable.ic_launcher)
				.setMessage("������Ϣ������һ��Ӧ�ü�������ִ����������е�����Ϣ���ƺ���Ϣ�����ѧ�ƣ���Ҫ�о���Ϣ�Ļ�ȡ�봦�������豸����Ϣϵͳ����ơ�������Ӧ�úͼ���");
				setPositiveButton(builder).create()
				.show();
				break;
		case 1:
			AlertDialog.Builder builder1 = new AlertDialog.Builder(this)
			.setTitle("���＼����")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("���＼����biotechnology������ָ�������ִ�������ѧΪ�������������������ѧ�Ŀ�ѧԭ�������Ƚ��Ŀ�ѧ�����ֶΣ�����Ԥ�ȵ���Ƹ����������ӹ�����ԭ�ϣ�Ϊ���������������Ʒ��ﵽĳ��Ŀ�ġ�");
			setPositiveButton(builder1).create()
			.show();
			break;
		case 2:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this)
			.setTitle("�ִ�ҽҩ��")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("��ҩ������һ����ѧ������ѧ��ҩѧ����ҩѧ���͹���ѧ����Ĺ�����רҵ������������ҩƷ�з����죬�¹��ա����豸����Ʒ�ֵĿ������Ŵ������˲�ΪĿ�ꡣ");
			setPositiveButton(builder2).create()
			.show();
			break;
		case 3:
			AlertDialog.Builder builder3 = new AlertDialog.Builder(this)
			.setTitle("����������")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("�������似����Ҫ����������ʩ��������ҵ�����࣬ǰ����������Ӳ����������������������������Ӳ������Ҫ�������������ʩ���繫·����·�����ˣ����䳵�Ȼ�����ʩ�����ƣ��������������������������������������Ա������");
			setPositiveButton(builder3).create()
			.show();
			break;
		case 4:
			AlertDialog.Builder builder4 = new AlertDialog.Builder(this)
			.setTitle("�²�����")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("�²�����ָ�½���չ�Ļ������з��ġ����ܳ�Ⱥ��һЩ���ϣ����бȴ�ͳ���ϸ�Ϊ��������ܡ����������²���Խ��Խ�����ӣ�����Ҳ�����²��ϲ�ҵ��չ��");
			setPositiveButton(builder4).create()
			.show();
			break;
		case 5:
			AlertDialog.Builder builder5 = new AlertDialog.Builder(this)
			.setTitle("������Դ��")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("��Դ������ָ���������չ�;�����������������Ҫ��Դ����Ȼ״̬����Ϊ��Դ�����ࡢ���ʡ��ֲ����䴢���Ƿ�ӳ���໷����������Ҫ���أ�������ѧ������������������һ���");
			setPositiveButton(builder5).create()
			.show();
			break;
		case 6:
			AlertDialog.Builder builder6 = new AlertDialog.Builder(this)
			.setTitle("������")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("����ѧ��һ���ۺ��ԵĽ���ѧ�ƣ���ϵͳ�о������Ļ������ɺ�һ�㷽���Ŀ�ѧ������ѧ����Ӧ�ִ���ữ����������Ҫ�����ģ�����Ŀ���ǣ��о������е������£����ͨ���������֯�������ˡ��ơ�������أ������������ˮƽ��");
			setPositiveButton(builder6).create()
			.show();
			break;
		case 7:
			AlertDialog.Builder builder7 = new AlertDialog.Builder(this)
			.setTitle("��ѧ��")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("��ѧ���ֳƷ���ѧ�����ɿ�ѧ�����Է��ɡ����������Լ��������Ϊ�о����ݵĿ�ѧ�������о��뷨��������ר��ѧ�ʣ��ǹ��ڷ��������֪ʶ��������ϵ");
			setPositiveButton(builder7).create()
			.show();
			break;
		case 8:
			AlertDialog.Builder builder8 = new AlertDialog.Builder(this)
			.setTitle("�������")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("��������רҵָ�ڿ���ѧ�����������ϵͳ�����������ļ�������ѧ�ơ�����������������Ե��ۺ����ԣ�������ӹ��̡�Ӧ��������е���̡��ִ�ͨ�ż�������ѧ�Ƚ��ܽ�ϣ���չѸ��");
			setPositiveButton(builder8).create()
			.show();
			break;
		case 9:
			AlertDialog.Builder builder9 = new AlertDialog.Builder(this)
			.setTitle("��ľ������")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("����ָ��Ӧ�õĲ��ϡ��豸�������еĿ��⡢��ơ�ʩ����������ά�޵ȼ������Ҳָ���̽���Ķ��󡣼������ڵ��ϻ���¡�½�ϻ�ˮ�� ��ֱ�ӻ���Ϊ����������������¡����з���ĸ��ֹ�����ʩ�����緿�ݡ���·����·���ܵ���������������˺ӡ��̰ӡ��ۿڡ���վ���ɻ���������ƽ̨����ˮ��ˮ�Լ��������̵�");
			setPositiveButton(builder9).create()
			.show();
			break;
		case 10:
			AlertDialog.Builder builder10 = new AlertDialog.Builder(this)
			.setTitle("��ý��")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("ѧУǰ���Ǵ�����1946��Ķ�������ѧԺ��1950�����Ϊ���������ѧ��1952�꾭Ժϵ������Ϊ�й����������ִ����ĵ�һ���ۺ��Դ�ѧ��1958�����Ϊ���ִ�ѧ��1960�꣬���ִ�ѧ������Ժ��Ϊ�����ص��ѧ��2000��6��12�գ��ϲ����ֹ�ҵ��ѧ�������ҽ�ƴ�ѧ�������Ƽ���ѧ��6��ԺУ�齨�µļ��ִ�ѧ��");
			setPositiveButton(builder10).create()
			.show();
			break;
		default:
			AlertDialog.Builder builder11 = new AlertDialog.Builder(this)
			.setTitle("  ")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("��δ��ӣ����ȷ����������Ա�����Ϣ");
			setPositiveButton(builder11).create()
			.show();
			break;
		}
		
	}
	private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder)
	{
		return builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
