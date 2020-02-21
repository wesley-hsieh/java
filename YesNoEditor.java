import java.beans.PropertyEditorSupport;

public class YesNoEditor extends PropertyEditorSupport {
	private String propertyValue; 
	private String tags[] = {"yes", "no"};
	
	public YesNoEditor()
	{
		
	}
	
	public String[] getTags()
	{
		return tags;
	}
	
	public void setAsText(String value) {
		propertyValue = value;
	}
	
	public String getAsText()
	{
		return new Boolean(propertyValue).toString();
	}
}
