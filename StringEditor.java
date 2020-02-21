import java.beans.PropertyEditorSupport;

public class StringEditor extends PropertyEditorSupport{
	private String propertyValue= "";
	
	public StringEditor()
	{
		
	}
	
	public String[] getTags()
	{
		return null;
	}
	
	public void setAsText(String value)
	{
		propertyValue = value;
	}

	public String getAsText()
	{
		return propertyValue;
	}
}
