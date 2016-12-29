package org.appsquad.research;

import java.io.File;
import java.io.FilePermission;
import java.util.Calendar;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.io.Files;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Fileupload;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Messagebox;

public class FileUploadDownload {
	
	private String filePath;
    private boolean fileuploaded = false;
    AMedia fileContent;
	
	public void onupLoadFile(@ContextParam(ContextType.BIND_CONTEXT) BindContext bindContext) throws Exception{
		
		UploadEvent uploadEvent = null;
		Object objUpEvent = bindContext.getTriggerEvent();
		
		if (objUpEvent != null && (objUpEvent instanceof UploadEvent)) {
			 uploadEvent = (UploadEvent) objUpEvent;
			 
		 }
		if(uploadEvent != null){
			Media media = uploadEvent.getMedia();
		
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH); // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
		
        filePath = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
        
        String yearPath = "\\" + "PDFs" + "\\" + year + "\\" + month + "\\" + day + "\\";
        filePath = filePath + yearPath;
        
        File baseDir = new File(filePath);
        
        if (!baseDir.exists()) {
               baseDir.mkdirs();
          }
        
        Files.copy(new File(filePath + media.getName()), media.getStreamData());
        Messagebox.show("Uploaded Successfully", "Information", Messagebox.OK, Messagebox.INFORMATION);
        fileuploaded = true;
        filePath = filePath + media.getName();
		}
		
	
	}
	
	

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isFileuploaded() {
		return fileuploaded;
	}

	public void setFileuploaded(boolean fileuploaded) {
		this.fileuploaded = fileuploaded;
	}

	public AMedia getFileContent() {
		return fileContent;
	}

	public void setFileContent(AMedia fileContent) {
		this.fileContent = fileContent;
	}
	
	class Test extends Fileupload{
		
	}
}
