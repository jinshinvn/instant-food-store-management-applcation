package gui;

import bus.Tool;

public class mainGUI {
	public static void main(String args[]) {
		try {
			Tool.docDB();
			new LoginGUI().setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
