package Commonality;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;

@SuppressWarnings("serial")
public class CFile implements java.io.Serializable{
	private String SourceName;
	private String Uploader;
	private int UploaderId;
	private String SourcePath;
	private int SourceId;
	private String UploadTime;
	private int SourceSize;
	private String SubjectName;
	private byte[] bytefile;
	public CFile(String Uploader,String SourceName,String SourcePath,int SourceId,String UploadTime,int SourceSize,String SubjectName)
	{
		this.Uploader=Uploader;
		this.SourceName=SourceName;
		this.SourcePath=SourcePath;
		this.SourceId=SourceId;
		this.UploadTime=UploadTime;
		this.SourceSize=SourceSize;
		this.SubjectName=SubjectName;
	}
	public CFile(int UploaderId,String SourceName,String SubjectName,File file)
	{
		this.UploaderId=UploaderId;
		this.SourceName=SourceName;
		this.SubjectName=SubjectName;
		this.setFile(file);
	}
	public CFile() {}
	public int getUploaderId() {
		return UploaderId;
	}
	public void setUploaderId(int uploaderId) {
		UploaderId = uploaderId;
	}

	public int getSourceSize() {
		return SourceSize;
	}
	public void setSourceSize(int sourceSize) {
		SourceSize = sourceSize;
	}
	public String getSourceName() {
		return SourceName;
	}
	public void setSourceName(String sourceName) {
		SourceName = sourceName;
	}
	public String getUploader() {
		return Uploader;
	}
	public void setUploader(String uploader) {
		Uploader = uploader;
	}
	public String getSourcePath() {
		return SourcePath;
	}
	public void setSourcePath(String sourcePath) {
		SourcePath = sourcePath;
	}
	public int getSourceId() {
		return SourceId;
	}
	public void setSourceId(int sourceId) {
		SourceId = sourceId;
	}
	public String getUploadTime() {
		return UploadTime;
	}
	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}
	
	public byte[] getFile() {
		return bytefile;
	}

	public void setFile(File file) {
		byte[] buffer = null;  
        try {    
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            int size = 0;
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n); 
                size+=n;
            }  
            bos.flush();
            fis.close();  
            bos.close(); 
            this.SourceSize=size;
            buffer = bos.toByteArray();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		bytefile=buffer;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}
};

