package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Gui {
	JFrame mainFrame = new JFrame();
	JPanel mainPanel = new JPanel();
	JPanel insertNewContactPanel = new JPanel();
	JPanel searchExistingPanel = new JPanel();
	JPanel listFilteredContactsPanel = new JPanel();
	JTabbedPane tabbedPane = new JTabbedPane();
	Dimension screenSize;
	int preferredHeight = 400;
	int preferredWidth = 500;
	GuiDataInterface guiData;
	GuiControlInterface guiController;

	public Gui(GuiDataInterface guiDataInterface, GuiControlInterface guiControlInterface) {
		this.guiController = guiControlInterface;
		this.guiData = guiDataInterface;
	
}

	public void initializeGUI() {
		screenSize = new Dimension(preferredWidth, preferredHeight);
		tabbedPane.add(createInsertPanel(), guiData.getNameForLabel("tabs_create_new_tab"));
		tabbedPane.add(createSearchExistingContactsPanel(), guiData.getNameForLabel("tabs_search_contact"));
		tabbedPane.add(createListFilteredCpntactsPanel(), guiData.getNameForLabel("tabs_list_all"));
		mainPanel.add(tabbedPane);
		mainFrame.setPreferredSize(screenSize);
		mainFrame.setSize(screenSize);
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
	}

	public JPanel createInsertPanel() {
		JPanel workingPanel = createPanelWithBorderLayout();
		JPanel gridPanelForForms = createPanelWithGridLayout(0, 2);
		JPanel textFieldSurName = createLabelAndTextBox(guiData.getNameForLabel("form_surname"));
		final JTextField surNameTextFieldHandle = (JTextField) textFieldSurName.getComponent(1);
		JPanel textFieldName = createLabelAndTextBox(guiData.getNameForLabel("form_name"));
		final JTextField nameTextFieldHandle = (JTextField) textFieldName.getComponent(1);
		JPanel textFieldAddress = createLabelAndTextBox(guiData.getNameForLabel("form_address"));
		final JTextField addresTextFieldHandle = (JTextField) textFieldAddress.getComponent(1);
		JPanel textFieldPostalCode = createLabelAndTextBox(guiData.getNameForLabel("form_postal_code"));
		final JTextField postalTextFieldHandle = (JTextField) textFieldPostalCode.getComponent(1);
		JPanel textFieldCity = createLabelAndTextBox(guiData.getNameForLabel("form_city"));
		final JTextField cityTextFieldHandle = (JTextField) textFieldCity.getComponent(1);
		JPanel textFieldTelephone = createLabelAndTextBox(guiData.getNameForLabel("form_telephone"));
		final JTextField telephoneTextFieldHandle = (JTextField) textFieldTelephone.getComponent(1);
		JPanel textFieldEmail = createLabelAndTextBox(guiData.getNameForLabel("form_email"));
		final JTextField emailTextFieldHandle = (JTextField) textFieldEmail.getComponent(1);

		gridPanelForForms.add(textFieldSurName);
		gridPanelForForms.add(textFieldName);
		gridPanelForForms.add(textFieldAddress);
		gridPanelForForms.add(textFieldPostalCode);
		gridPanelForForms.add(textFieldCity);
		gridPanelForForms.add(textFieldTelephone);
		gridPanelForForms.add(textFieldEmail);

		JPanel panelForSubmitButton = createPanelWithFlowLayout();
		JButton cancelButton = createButtonWithMargin(guiData.getNameForLabel("button_cancel"), 5);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				traversePanelAndClearTextFields(gridPanelForForms);
			}

		});
		JButton submitButton = createButtonWithMargin(guiData.getNameForLabel("button_submit"), 5);
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> contentsOfFormTexFields = new HashMap<String, String>();
				contentsOfFormTexFields.put("surname", surNameTextFieldHandle.getText());
				contentsOfFormTexFields.put("name", nameTextFieldHandle.getText());
				contentsOfFormTexFields.put("address", addresTextFieldHandle.getText());
				contentsOfFormTexFields.put("postalcode", postalTextFieldHandle.getText());
				contentsOfFormTexFields.put("city", cityTextFieldHandle.getText());
				contentsOfFormTexFields.put("telephone", telephoneTextFieldHandle.getText());
				contentsOfFormTexFields.put("email", emailTextFieldHandle.getText());
				guiData.setFormData(contentsOfFormTexFields);
				guiController.executeActionForSubmitButton(guiData);
				
			}
		});
		panelForSubmitButton.add(cancelButton);
		panelForSubmitButton.add(submitButton);
		workingPanel.add(gridPanelForForms, BorderLayout.CENTER);
		workingPanel.add(panelForSubmitButton, BorderLayout.PAGE_END);

		return workingPanel;
	}

	public JPanel createSearchExistingContactsPanel() {
		JPanel workingPanel = createPanelWithBorderLayout();
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.addAll(guiData.getAllEntrysForSearchCombobox());
		JPanel panelForComboboxAndTextField = createSearchPanelWithComboBoxAndTextField(arrayList);
		System.out.println(arrayList.toString());

		JPanel gridPanelForForms = createPanelWithGridLayout(0, 2);
		JPanel textFieldSurName = createLabelAndTextBox(guiData.getNameForLabel("form_surname"));
		final JTextField surNameTextFieldHandle = (JTextField) textFieldSurName.getComponent(1);
		JPanel textFieldName = createLabelAndTextBox(guiData.getNameForLabel("form_name"));
		final JTextField nameTextFieldHandle = (JTextField) textFieldName.getComponent(1);
		JPanel textFieldAddress = createLabelAndTextBox(guiData.getNameForLabel("form_address"));
		final JTextField addresTextFieldHandle = (JTextField) textFieldAddress.getComponent(1);
		JPanel textFieldPostalCode = createLabelAndTextBox(guiData.getNameForLabel("form_postal_code"));
		final JTextField postalTextFieldHandle = (JTextField) textFieldPostalCode.getComponent(1);
		JPanel textFieldCity = createLabelAndTextBox(guiData.getNameForLabel("form_city"));
		final JTextField cityTextFieldHandle = (JTextField) textFieldCity.getComponent(1);
		JPanel textFieldTelephone = createLabelAndTextBox(guiData.getNameForLabel("form_telephone"));
		final JTextField telephoneTextFieldHandle = (JTextField) textFieldTelephone.getComponent(1);
		JPanel textFieldEmail = createLabelAndTextBox(guiData.getNameForLabel("form_email"));
		final JTextField emailTextFieldHandle = (JTextField) textFieldEmail.getComponent(1);
		JButton searchButtonHandle = (JButton) panelForComboboxAndTextField.getComponent(2);
		searchButtonHandle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchKey = ((JComboBox) panelForComboboxAndTextField.getComponent(0)).getSelectedItem()
						.toString();
				String searchValue = ((JTextField) panelForComboboxAndTextField.getComponent(1)).getText();
				
				HashMap<String, String> dbResultHolderMap = guiController.executeActionForSingleSearchButton(searchKey,
						searchValue);
				System.out.println(dbResultHolderMap.get("name"));
					
				
				
			}
		});
		gridPanelForForms.add(textFieldSurName);
		gridPanelForForms.add(textFieldName);
		gridPanelForForms.add(textFieldAddress);
		gridPanelForForms.add(textFieldPostalCode);
		gridPanelForForms.add(textFieldCity);
		gridPanelForForms.add(textFieldTelephone);
		gridPanelForForms.add(textFieldEmail);
		workingPanel.add(panelForComboboxAndTextField, BorderLayout.PAGE_START);
		workingPanel.add(gridPanelForForms, BorderLayout.LINE_END);
		return workingPanel;
	}

	private JPanel createSearchPanelWithComboBoxAndTextField(ArrayList<String> itemsForComboBox) {
		JPanel workingPanel = createPanelWithFlowLayout();
		JComboBox<String> comboBox = new JComboBox<String>();
		itemsForComboBox.forEach((entry -> {
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
		textField.setPreferredSize(new Dimension(100, 30));
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

	private JPanel createListFilteredCpntactsPanel() {
		JPanel workingPanel = new JPanel();
		return workingPanel;

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
}
