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

	private String[] names=new String[]{"������ѧ","�廪��ѧ","������ѧ","������ѧ","�Ϻ���ͨ��ѧ","������ͨ��ѧ","�人��ѧ","�㽭��ѧ","�Ͼ���ѧ","�й������ѧ","���ִ�ѧ","���пƼ���ѧ","�Ĵ���ѧ","��ɽ��ѧ","�Ͽ���ѧ","�й���ѧ������ѧ","����ʦ����ѧ","�������ӿƼ���ѧ","��������ѧ","����ѧ","�Ͽ���ѧ","�人��ѧ","��������ҵ��ѧ"};
	private String[] descs=new String[]{"������ѧ","�廪��ѧ","������ѧ","������ѧ","�Ϻ���ͨ��ѧ","������ͨ��ѧ","�人��ѧ","�㽭��ѧ","�Ͼ���ѧ","�й������ѧ","���ִ�ѧ","���пƼ���ѧ","�Ĵ���ѧ","��ɽ��ѧ","�Ͽ���ѧ","�й���ѧ������ѧ","����ʦ����ѧ","�������ӿƼ���ѧ","��������ѧ","����ѧ","�Ͽ���ѧ","�人��ѧ","��������ҵ��ѧ"};
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
		String[] schoolname=new String[]{"������ѧ","�廪��ѧ","������ѧ","������ѧ","�Ϻ���ͨ��ѧ","������ͨ��ѧ","����ʦ����ѧ","�������ӿƼ���ѧ","��������ѧ","����ѧ","�Ͽ���ѧ","�人��ѧ","��������ҵ��ѧ"};
	}
	public void DDialog(int i)
	{
		switch(i)
		{
		case 0:
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
				.setTitle("������ѧ")
				.setIcon(R.drawable.ic_launcher)
				.setMessage("������ѧ����ơ����󡱣�������1898�꣬������ʦ��ѧ�ã����й�������һ��������ѧ��Ҳ�ǵ�һ���ԡ���ѧ��֮�������ѧУ���������־���й������ߵȽ����Ŀ��ˡ�");
				setPositiveButton(builder).create()
				.show();
				break;
		case 1:
			AlertDialog.Builder builder1 = new AlertDialog.Builder(this)
			.setTitle("�廪��ѧ")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("�廪��ѧ����ơ��廪�������л����񹲺͹�������ֱ��������ֱ�ܸ��������Ƶ�ȫ���ص��ѧ��λ�С�˫һ����[1]  ����211���̡�����985���̡�����ѡ�����ƻ�������2011�ƻ�������111�ƻ�������׿Խ����ʦ���������ƻ�������׿Խ�����˲Ž��������ƻ�������׿Խҽ�����������ƻ���");
			setPositiveButton(builder1).create()
			.show();
			break;
		case 2:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this)
			.setTitle("������ѧ")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("������ѧ������1905�꣬ԭ��������ѧ�����й�����������ĵ�һ���ߵ�ԺУ����ʼ��Ϊ�й�����֪�����������ಮ������У��Ϊ��������ɽ��У��������������ѡ�ԡ�����󴫡����Ĵ������䡰���¹⻪���������⡱��������ǿ��Ϣ�����е�ʱ�й�֪ʶ����������ѧ������ǿ����ϣ����");
			setPositiveButton(builder2).create()
			.show();
			break;
		case 3:
			AlertDialog.Builder builder3 = new AlertDialog.Builder(this)
			.setTitle("������ѧ")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("������ѧ��Chang'an University�������ڹŶ����������л����񹲺͹�������ֱ����ȫ���ص��ѧ���ɽ������ͽ�ͨ���䲿��������Դ����ס���ͳ��罨�貿������ʡ�����������Ĳ�һʡ���������ǹ���������˫һ��������һ��ѧ�ƽ����У���ǹ���������211���̡�����985��������ѧ�ƴ���ƽ̨������111�ƻ��������ҽ����ˮƽ��ѧ�����о�����Ŀ���͡�׿Խ����ʦ���������ƻ����ص㽨���У���Ǹ�ˮƽ��ҵ��ɫ��ѧ������Դ�������ˡ��ж�ͨ��ѧ���˳�Ա��У����������ѧ�����������ոۡ��ġ�̨ѧ���ĸ�У֮һ��ӵ��ȫ����УΨһ�������ۺ����鳡��");
			setPositiveButton(builder3).create()
			.show();
			break;
		case 4:
			AlertDialog.Builder builder4 = new AlertDialog.Builder(this)
			.setTitle("�Ϻ���ͨ��ѧ")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("�Ϻ���ͨ��ѧ��Shanghai Jiao Tong University������ơ��Ϻ����󡱣�λ���й�ֱϽ���Ϻ������л����񹲺͹�������ֱ�������Ϻ��й����ġ��ۺ��ԡ��о��͡����ʻ���ȫ���ص��ѧ��Ҳ�ǹ��ҡ�985���̡�����211���̡��ص㽨��ԺУ����ѡ��˫һ�����������ƻ�������111�ƻ�������2011�ƻ�������׿Խҽ�����������ƻ�������׿Խ�����˲Ž��������ƻ�������׿Խ����ʦ���������ƻ�������׿Խũ���˲Ž��������ƻ�����Ϊ��У���ˡ��й���ѧУ������ᡢUniversitas 21��21����ѧ�����˵���Ҫ��Ա��");
			setPositiveButton(builder4).create()
			.show();
			break;
		case 5:
			AlertDialog.Builder builder5 = new AlertDialog.Builder(this)
			.setTitle("������ͨ��ѧ")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("������ͨ��ѧ��Xi'an Jiaotong University����ơ��������󡱣�λ�ڹŶ��������ǽ�����ֱ��ȫ���ص��ۺ����о��ʹ�ѧ���ǹ��ҡ����塱�������塱�����ص㽨���У֮һ����211���̡������ص㽨���������ѧ֮һ����985���̡������ص㽨��ľ�����У֮һ����˫һ����ս�Ե�36��һ����ѧA�ཨ���У֮һ");
			setPositiveButton(builder5).create()
			.show();
			break;
		case 6:
			AlertDialog.Builder builder6 = new AlertDialog.Builder(this)
			.setTitle("�人��ѧ")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("�人��ѧ��Wuhan University������ơ���󡱣���һ��λ�ں����人�е��ۺ��о��ʹ�ѧ�����ѧԴͷ��Դ���峯ĩ��1893������ܶ���֮�������������������ǿѧ�ã�����һ�ٶ�����ʷ��1913�������������ߵ�ʦ��ѧУ�� 1926���齨���������ɽ��ѧ��1928�궨�������人��ѧ��������Ĵ���У֮һ��1949�����й����������人��ѧ��������");
			setPositiveButton(builder6).create()
			.show();
			break;
		case 7:
			AlertDialog.Builder builder7 = new AlertDialog.Builder(this)
			.setTitle("�㽭��ѧ")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("�㽭��ѧ��Zhejiang University������ơ���󡱣������ڡ��˼����á����ݡ�ǰ����1897�괴����������Ժ�����й����Լ����紴�����ʽ�ߵ�ѧУ֮һ��1928�����Ϊ�����㽭��ѧ���л����ʱ�ڣ��㽭��ѧ���ÿ�����У�������£�����Ϊ������ѧ��֮һ����Ӣ����ѧʷ����Լɪ��Ϊ���������š���ӭ������������ʷ����Ի͵�ʱ�ڡ��ÿ�����У��������ʷ���ף���Ϊ�����Уʷ����ΰ����ˣ���Ϊ���ȷ���ˡ����ǡ�Уѵ�������ġ��㽭��ѧУ�衷��");
			setPositiveButton(builder7).create()
			.show();
			break;
		case 8:
			AlertDialog.Builder builder8 = new AlertDialog.Builder(this)
			.setTitle("�Ͼ���ѧ")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("�Ͼ���ѧ��Nanjing University������ơ��ϴ�[1]  ���ǽ�����ֱ��������ֱ�ܸ��������Ƶ�ȫ���ص��ѧ������������˫һ��������211���̡�����985���̡���У�����������ƻ�������111�ƻ�������2011�ƻ�������׿Խ�ƻ���ʵʩ��У��Ҳ�Ǿ�У���ˡ��й���ѧУ������ᡢ��̫ƽ���ѧ���ˡ�21����ѧ�����˺Ͷ����о��ʹ�ѧЭ���Ա��");
			setPositiveButton(builder8).create()
			.show();
			break;
		case 9:
			AlertDialog.Builder builder9 = new AlertDialog.Builder(this)
			.setTitle("�й������ѧ")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("�й������ѧǰ����1937��������±���ѧ���Լ������Ļ������ϴ�ѧ�ͻ�����ѧ��1949��12��16�գ�����������������Ժͨ���ˡ����ڳ����й������ѧ�ľ�������1950��10��3�գ��Ի�����ѧΪ�����ϲ��齨���й������ѧ��ʽ��ѧ����Ϊ���й�����ĵ�һ�����������ѧ��1954�꣬��ȷ��Ϊ������ѧΪ�����ۺϴ�ѧ������ȫ���ص��ѧ��1960�꣬��ȷ��Ϊ�ۺ���ȫ���ص��ѧ��2001�꣬��ѡ��985���̡���");
			setPositiveButton(builder9).create()
			.show();
			break;
		case 10:
			AlertDialog.Builder builder10 = new AlertDialog.Builder(this)
			.setTitle("���ִ�ѧ")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("ѧУǰ���Ǵ�����1946��Ķ�������ѧԺ��1950�����Ϊ���������ѧ��1952�꾭Ժϵ������Ϊ�й����������ִ����ĵ�һ���ۺ��Դ�ѧ��1958�����Ϊ���ִ�ѧ��1960�꣬���ִ�ѧ������Ժ��Ϊ�����ص��ѧ��2000��6��12�գ��ϲ����ֹ�ҵ��ѧ�������ҽ�ƴ�ѧ�������Ƽ���ѧ��6��ԺУ�齨�µļ��ִ�ѧ��");
			setPositiveButton(builder10).create()
			.show();
			break;
		default:
			AlertDialog.Builder builder11 = new AlertDialog.Builder(this)
			.setTitle("��ѧ")
			.setIcon(R.drawable.ic_launcher)
			.setMessage("��δ��ӣ����ȷ����������Ա���ѧϰ��Ϣ");
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
