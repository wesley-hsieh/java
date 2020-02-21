import java.beans.PropertyEditorSupport;

public class ZeroToHundredEditor extends PropertyEditorSupport{

	private int propertyValue = 0; 
	public ZeroToHundredEditor()
	{
		
	}
	
	public String[] getTags()
	{
		return null;
	}
	
	public void setAsText(String value)
	{
		int val = new Integer(value).intValue();
		if((val >= 0) && (val <= 100))
		{
			propertyValue = val;
		}
		else
		{
			throw new IllegalArgumentException(value);
		}
	}
	
	public String getAsText()
	{
		return new Integer(propertyValue).toString();
	}
	
	
}

