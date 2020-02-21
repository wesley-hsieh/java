import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.beans.BeanInfo;
import java.beans.Beans;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JComboBox;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestPropertyEditor extends JFrame {
	private JLabel [] propNames = new JLabel[10];
	private JTextField[] propValues = new JTextField[10];
	
	private JPanel targetBean = null;
	private Class classObject = null;
	private PropertyDescriptor [] pd = null;
	private PropertyEditor[] pe = new PropertyEditor [10];
	private JComboBox[] propCombo = new JComboBox[10];
	private JPanel propValuesPanel;
	private JPanel propNamesPanel;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPropertyEditor frame = new TestPropertyEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestPropertyEditor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_L = new JPanel();
		contentPane.add(panel_L);
		
		JPanel panel_R = new JPanel();
		contentPane.add(panel_R);
		panel_R.setLayout(new BorderLayout(0, 0));
		
		JComboBox jpController = new JComboBox();
		jpController.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(pd != null)
				{
					Component[] componentList = propValuesPanel.getComponents();
					for(int p = 0 ; p < pd.length; p++)
					{
						propNames[p].setText("");
					}
					for(Component c: componentList)
					{
						propValuesPanel.remove(c);
					}
				}

				String className = (String)jpController.getSelectedItem();
				if(className == "")
				{
					return;
				}
				try
				{
					targetBean = (JPanel)Beans.instantiate ( null, className );
				}
				catch(ClassNotFoundException ex)
				{
					
				}
				catch(IOException ex)
				{
					
				}
				
				if(targetBean instanceof JPanel)
				{
					contentPane.remove(0);
					contentPane.add(targetBean,0);
					contentPane.validate();
				}
				
				
				BeanInfo bi = null; 
				try
				{
					classObject = Class.forName(className);
					//System.out.println(classObject);
					bi = Introspector.getBeanInfo(classObject, JPanel.class);
					//System.out.println(bi);
					
				}
				catch(ClassNotFoundException ex)
				{
					
				}
				catch(IntrospectionException ex)
				{
					
				}
				
				pd = bi.getPropertyDescriptors();
				String propText;
				//"delete" the other textfields
				
				for(int i =0 ; i<pd.length;i++)
				{
					propText= pd[i].getName();
					propNames[i].setText(propText);
					
					Class customEditorClass = pd[i].getPropertyEditorClass();
					//System.out.println(customEditorClass);
					PropertyEditor customEditor = null;
					if(customEditorClass!= null)
					{
						try
						{
							customEditor= (PropertyEditor)customEditorClass.newInstance();
							pe[i] = customEditor;
							//System.out.println(customEditor);
							
						}
						catch(IllegalAccessException ex) 
						{
							ex.printStackTrace();
						}
						catch(InstantiationException ex)
						{
							ex.printStackTrace();
						}
					}
				}
				
				//System.out.println(pd.length);			
				
				for(int z = 0 ; z < pd.length;z++)
				{
					if(pe[z].getTags() != null)
					{
						Method mget = pd[z].getReadMethod();
						Object robj = null;
						
						try
						{
							robj = mget.invoke(targetBean, null);
						}
					    catch (IllegalAccessException ex)
					    {
					    }
						catch (IllegalArgumentException ex)
					    {
					    }
						catch (InvocationTargetException ex)
					    {
					    }
						String sobj = robj.toString();
						
						//System.out.println(sobj);
						//System.out.println();
						propValues[z] = null;
						propCombo[z] = new JComboBox(pe[z].getTags());
						pe[z].setAsText(sobj);
						int g;
						for(g =0; g < pe[z].getTags().length; g++)
						{
							//System.out.println(pe[z].getTags()[g]);
							if (sobj.equalsIgnoreCase(pe[z].getTags()[g]))
							{
								break;
							}
						}
						//System.out.println(g);
						propCombo[z].setSelectedIndex(g);
						propCombo[z].addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								int j; 
								String propText = "", propValueChanged = "";				
								for(j = 0; j < propCombo.length;j++)
								{
									if(e.getSource() == propCombo[j])
									{
										break;
									}
								}
								
								propText = propNames[j].getName();
								propValueChanged = (String) propCombo[j].getSelectedItem();
								try
								{
									pe[j].setAsText(propValueChanged);
									Class propType = pd[j].getPropertyType();
									String propTypeName = propType.getName();
									
									Object params[]= new Object[1];
									if(propTypeName.equals("int"))
									{
										params[0] = new Integer(Integer.parseInt(propValueChanged));
									}
								    else if (propTypeName.equals ("double"))
								    {
								        params [0] = new Double (Double.parseDouble ( propValueChanged ));
								    }
								    else if (propTypeName.equals ("boolean"))
								    {
								        params [0] = new Boolean (propValueChanged );
								    }
								    else if (propTypeName.equals ("java.lang.String" ) )
								    {
								        params [0] = propValueChanged;
								    }
									
									Method mset = pd[j].getWriteMethod();
									try
									{
										mset.invoke(targetBean, params);
									}
								    catch (IllegalAccessException ex)
								    {
								    }
								    catch (IllegalArgumentException ex)
								    {
								    }
								    catch (InvocationTargetException ex)
								    {
								    }								
								}//try
								catch(IllegalArgumentException ex)
								{
									int g;
									for(g =0; g < pe[j].getTags().length; g++)
									{
										//System.out.println(pe[z].getTags()[g]);
										if (pe[j].getAsText().equalsIgnoreCase(pe[j].getTags()[g]))
										{
											break;
										}
									}
									propCombo[j].setSelectedIndex(g);
								}//catch
							}
						});
						
						propValuesPanel.add(propCombo[z]);
					}
					else
					{
						Method mget = pd[z].getReadMethod();
						Object robj= null;
						
						try
						{
							robj = mget.invoke(targetBean, null);
						}
					    catch (IllegalAccessException ex)
					    {
					    }
						catch (IllegalArgumentException ex)
					    {
					    }
						catch (InvocationTargetException ex)
					    {
					    }
						
						String sobj = robj.toString();
						
						propValues[z] = new JTextField(sobj);
						pe[z].setAsText(sobj);
						propValues[z].addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								int i = 0; 
								String propText = "", propValueChanged = "";
								
								for(int j = 0;j <propValues.length;j++)
								{
									if(e.getSource()== propValues[j])
									{
										i = j;
										break;
									}
								}
								
								//get prop name and value from corresponding arrays
								propText = propNames[i].getName();
								propValueChanged = propValues[i].getText();
								try
								{
									pe[i].setAsText(propValueChanged);
												
									Class propType = pd[i].getPropertyType();
									String propTypeName = propType.getName();
								
									Object params[]= new Object[1];
									if(propTypeName.equals("int"))
									{
										params[0] = new Integer(Integer.parseInt(propValueChanged));
									}
									else if (propTypeName.equals ("double"))
									{
										params [0] = new Double (Double.parseDouble ( propValueChanged ));
									}
									else if (propTypeName.equals ("boolean"))
									{
										params [0] = new Boolean (propValueChanged );
									}
									else if (propTypeName.equals ("java.lang.String" ) )
									{
										params [0] = propValueChanged;
									}
								
									Method mset = pd[i].getWriteMethod();
									try
									{
										mset.invoke(targetBean, params);
									}
									catch (IllegalAccessException ex)
									{
									}
									catch (IllegalArgumentException ex)
									{
									}
									catch (InvocationTargetException ex)
									{
									}
								}	
								catch(IllegalArgumentException ex)
								{
									propValues[i].setText(pe[i].getAsText());
								}
								
							}
						
						});
						
						propValuesPanel.add(propValues[z]);
					}
				}
			}//actionperformed
			
		});
		jpController.addItem("");
	    jpController.addItem("Rect");
	    jpController.addItem("Circ");
	    jpController.addItem("Ticker");
	    jpController.setEditable(false);
		panel_R.add(jpController, BorderLayout.NORTH);
		
		JPanel jpInspector = new JPanel();
		panel_R.add(jpInspector, BorderLayout.CENTER);
		jpInspector.setLayout(new GridLayout(1, 2, 0, 0));
		
		propNamesPanel = new JPanel();
		jpInspector.add(propNamesPanel);
		propNamesPanel.setLayout(new GridLayout(10, 0, 0, 0));
		
		propValuesPanel = new JPanel();
		jpInspector.add(propValuesPanel);
		propValuesPanel.setLayout(new GridLayout(10, 0, 0, 0));
		
		
		for(int i = 0; i < 10; i++)
		{
			propNames[i] = new JLabel("");
			propNamesPanel.add(propNames[i]);
		}
	}
}
