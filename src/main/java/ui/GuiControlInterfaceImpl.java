package ui;

import java.util.HashMap;

import data.Contact;

public class GuiControlInterfaceImpl implements GuiControlInterface {

	@Override
	public void executeActionForSubmitButton(GuiDataInterface guiDataInterface) {
		HashMap<String, String> formData = guiDataInterface.getFormData();
		Contact contact = new Contact();
		contact.setSurName(formData.get("surname"));
		contact.setName(formData.get("name"));

		contact.setAddress(formData.get("address"));
		contact.setPostalCode(formData.get("postalcode"));
		contact.setCity(formData.get("city"));
		contact.setTelephone(formData.get("telephone"));
		contact.setEmail(formData.get("email"));

		System.out.println(contact.toString());
		// TODO submit sends contact object to the Server
	}

	@Override
	public HashMap<String, String> executeActionForSingleSearchButton(String searchKey, String searchValue) {
		// TODO Search database for entry and return the first
		HashMap<String, String> dataHolderForDBResult = new HashMap<>();
		dataHolderForDBResult.put("name", "Patrick");
		return dataHolderForDBResult;

	}

}
