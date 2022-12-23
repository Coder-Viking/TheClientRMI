package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

import controller.GuiControlInterface;
import data.Contact;
import data.ContactsDatabaseFields;
import data.GuiDataInterface;
import data.HashMapKeys;

public class Gui {
	JFrame mainFrame = new JFrame();
	JPanel mainPanel = new JPanel();
	JPanel insertNewContactPanel = new JPanel();
	JPanel searchExistingPanel = new JPanel();
	JPanel listFilteredContactsPanel = new JPanel();
	JTabbedPane tabbedPane = new JTabbedPane();
	Dimension screenSize;
	int preferredHeight = 350;
	int preferredWidth = 600;
	GuiDataInterface guiData;
	GuiControlInterface guiController;
	boolean isEveryThingResizable = false;
	private boolean isCloseRequested = false;
	Dimension maxTextboxSize = new Dimension(150, 30);

	public Gui(GuiDataInterface guiDataInterface, GuiControlInterface guiControlInterface) {
		this.guiController = guiControlInterface;
		this.guiData = guiDataInterface;
	
}

public void initializeGUI() {
	FlatCarbonIJTheme.setup();
		screenSize = new Dimension(preferredWidth, preferredHeight);
		tabbedPane.add(createInsertPanel(), guiData.getNameForLabel("tabs_create_new_tab"));
		tabbedPane.add(createSearchExistingContactsPanel(), guiData.getNameForLabel("tabs_search_contact"));
		tabbedPane.add(createListAllContactsPanel(), guiData.getNameForLabel("tabs_list_all"));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(tabbedPane, BorderLayout.CENTER);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(screenSize);
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

	public JPanel createInsertPanel() {
		JPanel workingPanel = createPanelWithBorderLayout();
		JPanel gridPanelForForms = createPanelWithGridLayout(0, 2);
		JPanel textFieldSurName = createLabelAndTextBox(guiData.getNameForLabel("form_surname"));
		final JTextField insertSurNameTextFieldHandle = (JTextField) textFieldSurName.getComponent(1);
		JPanel textFieldName = createLabelAndTextBox(guiData.getNameForLabel("form_name"));
		final JTextField insertNameTextFieldHandle = (JTextField) textFieldName.getComponent(1);
		JPanel textFieldAddress = createLabelAndTextBox(guiData.getNameForLabel("form_address"));
		final JTextField insertAddresTextFieldHandle = (JTextField) textFieldAddress.getComponent(1);
		JPanel textFieldPostalCode = createLabelAndTextBox(guiData.getNameForLabel("form_postal_code"));
		final JTextField insertPostalTextFieldHandle = (JTextField) textFieldPostalCode.getComponent(1);
		JPanel textFieldCity = createLabelAndTextBox(guiData.getNameForLabel("form_city"));
		final JTextField insertCityTextFieldHandle = (JTextField) textFieldCity.getComponent(1);
		JPanel textFieldTelephone = createLabelAndTextBox(guiData.getNameForLabel("form_telephone"));
		final JTextField insertTelephoneTextFieldHandle = (JTextField) textFieldTelephone.getComponent(1);
		JPanel textFieldEmail = createLabelAndTextBox(guiData.getNameForLabel("form_email"));
		final JTextField insertEmailTextFieldHandle = (JTextField) textFieldEmail.getComponent(1);

		gridPanelForForms.add(textFieldSurName);
		gridPanelForForms.add(textFieldName);
		gridPanelForForms.add(textFieldAddress);
		gridPanelForForms.add(textFieldPostalCode);
		gridPanelForForms.add(textFieldCity);
		gridPanelForForms.add(textFieldTelephone);
		gridPanelForForms.add(textFieldEmail);

		JPanel panelForSubmitButton = createPanelWithFlowLayout();
		JButton cancelButton = createButtonWithMargin(guiData.getNameForLabel("button_cancel"), 5);
		cancelButton.addActionListener(e -> {
			traversePanelAndClearTextFields(gridPanelForForms);
		});
		JButton submitButton = createButtonWithMargin(guiData.getNameForLabel("button_submit"), 5);
		submitButton.addActionListener(e -> {
			if (insertSurNameTextFieldHandle.getText().isBlank() || insertNameTextFieldHandle.getText().isBlank()
					|| insertTelephoneTextFieldHandle.getText().isBlank()) {
				createJDialogPopupConfirmation(false);
				return;
			}
			HashMap<String, String> contentsOfFormTexFields = new HashMap<String, String>();
			contentsOfFormTexFields.put("surname", insertSurNameTextFieldHandle.getText());
			contentsOfFormTexFields.put("name", insertNameTextFieldHandle.getText());
			contentsOfFormTexFields.put("address", insertAddresTextFieldHandle.getText());
			contentsOfFormTexFields.put("postalcode", insertPostalTextFieldHandle.getText());
			contentsOfFormTexFields.put("city", insertCityTextFieldHandle.getText());
			contentsOfFormTexFields.put("telephone", insertTelephoneTextFieldHandle.getText());
			contentsOfFormTexFields.put("email", insertEmailTextFieldHandle.getText());
			guiData.setFormData(contentsOfFormTexFields);
			boolean successfulCreatedItemInDatabase = guiController.executeActionForSubmitButton(guiData);
			createJDialogPopupConfirmation(successfulCreatedItemInDatabase);

		});
		panelForSubmitButton.add(cancelButton);
		panelForSubmitButton.add(submitButton);
		workingPanel.add(gridPanelForForms, BorderLayout.CENTER);
		workingPanel.add(panelForSubmitButton, BorderLayout.PAGE_END);

		return workingPanel;
	}

	private void createJDialogPopupConfirmation(boolean successfulCreatedItemInDatabase) {
		if (successfulCreatedItemInDatabase) {
			JOptionPane.showMessageDialog(mainFrame, guiData.getNameForLabel("dialog_text_success"));
		} else {
			JOptionPane.showMessageDialog(mainFrame, guiData.getNameForLabel("dialog_text_fail"));
		}
	}

	public JPanel createSearchExistingContactsPanel() {
		JPanel workingPanel = createPanelWithBorderLayout();
		ArrayList<ContactsDatabaseFields> arrayList = new ArrayList<>();
		arrayList.addAll(Arrays.asList(ContactsDatabaseFields.values()));
		JPanel panelForComboboxAndTextField = createSearchPanelWithComboBoxAndTextField(arrayList);
		System.out.println(arrayList.toString());

		JPanel gridPanelForForms = createPanelWithGridLayout(0, 2);
		JPanel textFieldSurName = createLabelAndTextBox(guiData.getNameForLabel("form_surname"));
		JPanel textFieldName = createLabelAndTextBox(guiData.getNameForLabel("form_name"));
		JPanel textFieldAddress = createLabelAndTextBox(guiData.getNameForLabel("form_address"));
		JPanel textFieldPostalCode = createLabelAndTextBox(guiData.getNameForLabel("form_postal_code"));
		JPanel textFieldCity = createLabelAndTextBox(guiData.getNameForLabel("form_city"));
		JPanel textFieldTelephone = createLabelAndTextBox(guiData.getNameForLabel("form_telephone"));
		JPanel textFieldEmail = createLabelAndTextBox(guiData.getNameForLabel("form_email"));
		JButton searchButtonHandle = (JButton) panelForComboboxAndTextField.getComponent(2);

		final JTextField searchSurNameTextFieldHandle = (JTextField) textFieldSurName.getComponent(1);
		final JTextField searchNameTextFieldHandle = (JTextField) textFieldName.getComponent(1);
		final JTextField searchAddresTextFieldHandle = (JTextField) textFieldAddress.getComponent(1);
		final JTextField searchPostalTextFieldHandle = (JTextField) textFieldPostalCode.getComponent(1);
		final JTextField searchCityTextFieldHandle = (JTextField) textFieldCity.getComponent(1);
		final JTextField searchTelephoneTextFieldHandle = (JTextField) textFieldTelephone.getComponent(1);
		final JTextField searchEmailTextFieldHandle = (JTextField) textFieldEmail.getComponent(1);

		searchSurNameTextFieldHandle.setEditable(false);
		searchNameTextFieldHandle.setEditable(false);
		searchAddresTextFieldHandle.setEditable(false);
		searchPostalTextFieldHandle.setEditable(false);
		searchCityTextFieldHandle.setEditable(false);
		searchTelephoneTextFieldHandle.setEditable(false);
		searchEmailTextFieldHandle.setEditable(false);

		searchButtonHandle.addActionListener(e -> {
			JComboBox comboBox = ((JComboBox<ContactsDatabaseFields>) panelForComboboxAndTextField.getComponent(0));
			String searchKey = ((ContactsDatabaseFields) comboBox.getSelectedItem()).getDBField();
			String searchValue = ((JTextField) panelForComboboxAndTextField.getComponent(1)).getText();
 
			HashMap<String, String> dbResultHolderMap = guiController.executeActionForSingleSearchButton(searchKey,
					searchValue);
			setTextForTextFieldFromQueryResult(searchNameTextFieldHandle,
					dbResultHolderMap.get(HashMapKeys.NAME.getValue()));
			setTextForTextFieldFromQueryResult(searchSurNameTextFieldHandle,
					dbResultHolderMap.get(HashMapKeys.SURNAME.getValue()));
			setTextForTextFieldFromQueryResult(searchAddresTextFieldHandle,
					dbResultHolderMap.get(HashMapKeys.ADDRESS.getValue()));
			setTextForTextFieldFromQueryResult(searchPostalTextFieldHandle,
					dbResultHolderMap.get(HashMapKeys.POSTAL_CODE.getValue()));
			setTextForTextFieldFromQueryResult(searchCityTextFieldHandle,
					dbResultHolderMap.get(HashMapKeys.CITY.getValue()));
			setTextForTextFieldFromQueryResult(searchTelephoneTextFieldHandle,
					dbResultHolderMap.get(HashMapKeys.TELEPHONE.getValue()));
			setTextForTextFieldFromQueryResult(searchEmailTextFieldHandle,
					dbResultHolderMap.get(HashMapKeys.EMAIL.getValue()));
		});
		gridPanelForForms.add(textFieldSurName);
		gridPanelForForms.add(textFieldName);
		gridPanelForForms.add(textFieldAddress);
		gridPanelForForms.add(textFieldPostalCode);
		gridPanelForForms.add(textFieldCity);
		gridPanelForForms.add(textFieldTelephone);
		gridPanelForForms.add(textFieldEmail);
		workingPanel.add(panelForComboboxAndTextField, BorderLayout.PAGE_START);
		workingPanel.add(gridPanelForForms, BorderLayout.CENTER);
		return workingPanel;
	}

	public JPanel createListAllContactsPanel() {
		JPanel workingPanel = createPanelWithBorderLayout();
		ArrayList<ContactsDatabaseFields> arrayList = new ArrayList<>();
		arrayList.addAll(Arrays.asList(ContactsDatabaseFields.values()));
		JPanel panelForComboboxAndTextField = createSearchPanelWithComboBoxAndTextField(arrayList);
		
		JButton searchButton = ((JButton) panelForComboboxAndTextField.getComponent(2));
		JComboBox<ContactsDatabaseFields> filterComboBox = ((JComboBox<ContactsDatabaseFields>) panelForComboboxAndTextField
				.getComponent(0));
		JTextField filterValue = ((JTextField) panelForComboboxAndTextField.getComponent(1));
		JList<Contact> listOfResults = configureAndCreateJList();
		DefaultListModel<Contact> listModel = new DefaultListModel<>();

		JPopupMenu contextPopup  =new JPopupMenu();
		
		JMenuItem deleteItem = new JMenuItem(guiData.getNameForLabel("list_context_delete"));
		contextPopup.add(deleteItem);
		listOfResults.setComponentPopupMenu(contextPopup);


		JScrollPane scrollPaneForList = new JScrollPane(listOfResults);
		scrollPaneForList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneForList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		searchButton.addActionListener(e -> {
			listModel.clear();
			listModel.addAll(guiController.executeActionForListAllFilteredButton(
					((ContactsDatabaseFields) filterComboBox.getSelectedItem()).getDBField(), filterValue.getText()));
			listOfResults.setModel(listModel);
			listOfResults.updateUI();
		});

		deleteItem.addActionListener(action -> {
			guiController.deleteSelectedItemFromList(listOfResults.getSelectedValue().getId());
			((DefaultListModel<Contact>) listOfResults.getModel()).remove(listOfResults.getSelectedIndex());
			listOfResults.updateUI();

		});

		workingPanel.add(scrollPaneForList, BorderLayout.CENTER);
		workingPanel.add(panelForComboboxAndTextField, BorderLayout.PAGE_START);
		return workingPanel;
	}

	private JList<Contact> configureAndCreateJList() {
		JList<Contact> jlist = new JList<>();
		jlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		jlist.setLayoutOrientation(JList.VERTICAL);
		jlist.setVisibleRowCount(-1);
		return jlist;
	}

	private JPanel createSearchPanelWithComboBoxAndTextField(ArrayList<ContactsDatabaseFields> itemsForComboBox) {
		JPanel workingPanel = createPanelWithFlowLayout();
		JComboBox<ContactsDatabaseFields> comboBox = new JComboBox<ContactsDatabaseFields>();
		itemsForComboBox.forEach((entry -> {
			if (!entry.equals(ContactsDatabaseFields.ID))
				comboBox.addItem(entry);
		}));

		JTextField textFieldForSearch = new JTextField();
		textFieldForSearch.setPreferredSize(new Dimension(100, 30));
		JButton searchButton = createButtonWithMargin(guiData.getNameForLabel("search_button"), 5);

		workingPanel.add(comboBox);
		workingPanel.add(textFieldForSearch);
		workingPanel.add(searchButton);
		return workingPanel;
	}

	private JPanel createLabelAndTextBox(String labelName) {
		JPanel labelAndTextBoxPanel = createPanelWithBorderLayout();
		labelAndTextBoxPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel label = new JLabel(labelName);
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(150, 25));
		textField.setMaximumSize(maxTextboxSize);
		labelAndTextBoxPanel.add(label, BorderLayout.CENTER);
		labelAndTextBoxPanel.add(textField, BorderLayout.LINE_END);
		return labelAndTextBoxPanel;
	}

	private JPanel createPanelWithGridLayout(int rows, int columns) {
		JPanel panelWithGridLayout = new JPanel();
		GridLayout gridLayout = new GridLayout(rows, columns);
		panelWithGridLayout.setLayout(gridLayout);
		return panelWithGridLayout;
	}

	private JPanel createPanelWithBorderLayout() {
		JPanel panelWithBorderLayout = new JPanel();
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(10);
		borderLayout.setVgap(5);
		panelWithBorderLayout.setLayout(borderLayout);
		return panelWithBorderLayout;
	}

	private JPanel createPanelWithFlowLayout() {
		JPanel panelWithFlowLayout = new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		panelWithFlowLayout.setLayout(flowLayout);
		return panelWithFlowLayout;
	}

	private JPanel createPanelWithBoxLayout() {
		JPanel panelWithBoxLayout = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panelWithBoxLayout, BoxLayout.Y_AXIS);
		panelWithBoxLayout.setLayout(boxLayout);
		return panelWithBoxLayout;
	}

	private JButton createButtonWithMargin(String buttonName, int buttonMargin) {
		JButton button = new JButton(buttonName);
		button.setMargin(new Insets(buttonMargin, buttonMargin, buttonMargin, buttonMargin));
		return button;
	}

	private void traversePanelAndClearTextFields(JPanel gridPanelForForms) {
		System.out.println("clearing textFields ");
		Arrays.asList(gridPanelForForms.getComponents()).forEach((component -> {
			((JTextField) ((JPanel) component).getComponent(1)).setText("");
		}));
	}

	private void setTextForTextFieldFromQueryResult(JTextField nameTextFieldHandle, String string) {
		nameTextFieldHandle.setText(string);
	}

}


