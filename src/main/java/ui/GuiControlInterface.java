package ui;

import java.util.HashMap;

public interface GuiControlInterface {
	public void executeActionForSubmitButton(GuiDataInterface guiDataInterface);

	public HashMap<String, String> executeActionForSingleSearchButton(String searchKey, String searchValue);
}
