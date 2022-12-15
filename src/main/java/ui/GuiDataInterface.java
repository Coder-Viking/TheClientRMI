package ui;

import java.util.Collection;
import java.util.HashMap;

import javax.swing.Action;

public interface GuiDataInterface {

	public String getNameForLabel(String labelName);

	public Action getActionForComponent(String componentName);

	public HashMap<String, String> getContentsOfForm();

	public void setFormData(HashMap<String, String> contentsOfFormTexFields);

	public HashMap<String, String> getFormData();

	public Collection<? extends String> getAllEntrysForSearchCombobox();
}
