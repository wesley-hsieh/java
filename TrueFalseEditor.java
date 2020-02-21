import java.beans.PropertyEditorSupport;

public class TrueFalseEditor extends PropertyEditorSupport {
	private boolean propertyValue; 
	private String tags[] = {"true", "false"};
	
	public TrueFalseEditor()
	{
		
	}
	
	public String[] getTags()
	{
		return tags;
	}
	
	public void setAsText(String value)
	{
		boolean val = new Boolean(value).booleanValue();
		propertyValue = val;
	}
	
	public String getAsText()
	{
		return new Boolean(propertyValue).toString();
	}
}
