package com.example.college;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import com.example.college.Internetconnection.InternetConnection;
import com.example.college.datatransport.DatabaseQuery;
import Commonality.CFile;
import Commonality.UserComment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FinformationActivity extends Activity {
	private ListView commentlist;
	private CFile file;
	private TextView filedata;
	private EditText et_comment;
	public static ArrayList<UserComment> commentdatalist=null;
	int commentdatanum;
	String[] commentdata=null;
	String CommentContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finformation);
		Bundle bundle=getIntent().getExtras();
		file=(CFile)bundle.get("filedata");
		init();
	}
	public void init()
	{
		et_comment=(EditText)findViewById(R.id.et_comment);
		filedata = (TextView)findViewById(R.id.filedata);
		String data ="文件名:\t"+file.getSourceName()+"\n大小:\t"+file.getSourceSize()+"\n上传者:\t"+file.getUploader()+"\n上传时间：\t"+file.getUploadTime();
		filedata.setText(data);
		commentlist=(ListView)findViewById(R.id.commentlist);
		commentdatanum=commentdatalist.size();
		commentdata=new String[commentdatanum];
		for(int i=0;i<commentdatanum;i++)
		{
			commentdata[i]="用户:"+commentdatalist.get(i).getUserNickname()+"\t于\t"+commentdatalist.get(i).getCommentTime()+"\t评论了:\n"+commentdatalist.get(i).getCommentContent();
		}
		ArrayAdapter<String> commentadapter = new ArrayAdapter<String>(this,R.layout.downloadlist,commentdata);
		commentlist.setAdapter(commentadapter);
	}
	@SuppressWarnings("unused")
	public void OnClickedComment(View view)
	{
		CommentContent=et_comment.getText().toString().trim();
		CommentThread commentThread=new CommentThread();
		commentThread.start();
	}
	@Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
		commentdatalist=null;
		commentdata=null;
    	super.onDestroy();
    }
	public void onClickDownload(View v)
	{
		CalThread calThread=new CalThread();
		calThread.start();
	}
	class CommentListThread extends Thread
    {
    	public void run()
    	{
    		Looper.prepare();
    		FinformationActivity.commentdatalist=InternetConnection.UserCommentTransmit(file.getSourceId());
    		Intent intent= new Intent(FinformationActivity.this, FinformationActivity.class);
	     	intent.putExtra("filedata", file);
	     	startActivity(intent);
	     	finish();
    	   	Looper.loop();
    	}
    };
	class CommentThread extends Thread
	{
		public void run()
    	{
    		Looper.prepare();
    		boolean succeed=InternetConnection.AddComment(new UserComment(file.getSourceId(),LoginActivity.login_user.getUserId(),CommentContent));
    		if(succeed)
    		{
    			Toast.makeText(FinformationActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
    			CommentListThread commentListThread=new CommentListThread();
    			commentListThread.start();
    		}
    	   	Looper.loop();
    	}
	}
	class CalThread extends Thread
    {
    	@SuppressLint({ "WorldReadableFiles", "WorldWriteableFiles" })
		public void run()
    	{
    		Looper.prepare();
    		byte[] buffer=InternetConnection.FileDownload(file.getSourcePath());
    		if(buffer!=null)
    		{
    			String path=DatabaseQuery.getSDPath()+"/College";
    	        //光标移到原始文件最后，再执行写入
    	        try {
    	               File targetFile = new File(path+"/"+file.getSourceName());
        	           //使用RandomAccessFile是在原有的文件基础之上追加内容，
        	           //而使用outputstream则是要先清空内容再写入
        	           RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
        	           raf.seek(targetFile.length());
        	           raf.write(buffer);
        	           Toast.makeText(FinformationActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
	    	           raf.close();
    	        } catch (IOException e) {
    	        	// TODO Auto-generated catch block
					e.printStackTrace();
				} 
    	    }
    		else
    		{
    			Toast.makeText(FinformationActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
    		}
    	   	Looper.loop();
    	}
    };
    
}
