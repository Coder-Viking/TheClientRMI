package ui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.Action;

public class GuiDataInterfaceImpl implements GuiDataInterface {

	HashMap<String, String> formData = new HashMap<String, String>();
	Bundle localeBundle;

	public GuiDataInterfaceImpl() {
		localeBundle = new Bundle(Locale.GERMANY);
	}

	@Override
	public String getNameForLabel(String labelName) {
		return localeBundle.getKeyFromLocale(labelName);
	}

	@Override
	public Action getActionForComponent(String componentName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getContentsOfForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFormData(HashMap<String, String> contentsOfFormTexFields) {
		this.formData = contentsOfFormTexFields;
	}

	@Override
	public HashMap<String, String> getFormData() {
		return this.formData;

	}

	@Override
	public Collection<? extends String> getAllEntrysForSearchCombobox() {
		return localeBundle.getAllKeysWithPrefix("search_combobox");
	}

}
