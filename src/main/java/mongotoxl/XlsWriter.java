package mongotoxl;
import java.io.File;
import java.util.ArrayList;
import java.util.Date; 

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class XlsWriter {
	private MongoReader reader;
	private String outputfile;

	public XlsWriter(String file){
		this.outputfile = file;
	}
	
	public MongoReader getReader() {
		return reader;
	}

	public void setReader(MongoReader reader) {
		this.reader = reader;
	}
	
	public void writeData() throws Exception{
		/*Write Header*/
		WritableWorkbook workbook = Workbook.createWorkbook(new File(this.outputfile)); 
		WritableSheet sheet = workbook.createSheet("Sheet1",0);
		
		for (int i=0;i<reader.getKeySet().toArray().length;i++){
			Label label = new Label(i,0,reader.getKeySet().toArray()[i].toString());
			sheet.addCell(label);
		}
		
		/*Write Content*/
		for(int i=0; i< reader.getList().size();i++){
			ArrayList l = (ArrayList)(reader.getList().get(i));
			for(int j=0;j<l.size();j++){
				Label label = new Label(j,i+1,l.get(j).toString());
				sheet.addCell(label);
			}
		}
		workbook.write();
		workbook.close(); 
	}
	
}
