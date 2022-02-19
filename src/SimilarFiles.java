import java.io.File;
import java.util.ArrayList;

public class SimilarFiles{

	private ArrayList<String> columnAttributes;
	private ArrayList<File> columnFiles; 
	private ArrayList<String> fileNames; 
	
	public SimilarFiles() {
		columnAttributes = new ArrayList<>();
		columnFiles = new ArrayList<>();
		fileNames = new ArrayList<>();
	}
	
	public void setColumnAttributes(ArrayList<String> columnAttributes) {
		this.columnAttributes = columnAttributes;
	}
	
	public void addFile(File file) {
		columnFiles.add(file);
	}
	
	public void addFileNames(String name) {
		fileNames.add(name);
	}
	
	public boolean checkSameFile(ArrayList<String> list) {
		if(list.size()!= columnAttributes.size())return false;
		return columnAttributes.equals(list);
	}
	
	public ArrayList<String> getColumnAttributes() {
		return columnAttributes;
	}
	
	public ArrayList<File> getColumnFiles() {
		return columnFiles;
	}
	public ArrayList<String> getFileNames() {
		return fileNames;
	}
	
}
