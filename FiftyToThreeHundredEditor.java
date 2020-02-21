import java.beans.PropertyEditorSupport;

public class FiftyToThreeHundredEditor extends PropertyEditorSupport{
	private int propertyValue = 0; 
	public FiftyToThreeHundredEditor()
	{
		
	}
	
	public String[] getTags()
	{
		return null;
	}
	
	public void setAsText(String value)
	{
		int val = new Integer(value).intValue();
		if((val >= 50) && (val <= 300))
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
