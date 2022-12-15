package ui;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Bundle {
	ResourceBundle resourceBundle;
	public Bundle(Locale locale) {
		resourceBundle = ResourceBundle.getBundle("Entry.bundle_de_DE", locale);
	}

	public String getKeyFromLocale(String key) {
		return resourceBundle.getString(key);
	}

	public ArrayList<String> getAllKeysWithPrefix(String prefix) {
		ArrayList<String> arrayListForAllKeys = new ArrayList<>();
		resourceBundle.getKeys().asIterator().forEachRemaining((key -> {
			if (key.startsWith(prefix)) {
				arrayListForAllKeys.add(resourceBundle.getString(key));
			}
		}));
		return arrayListForAllKeys;
	}
}
