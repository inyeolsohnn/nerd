package view;

public class LightRemoveButton extends CustomJButton {

	int lightId;

	public LightRemoveButton(String text, int id) {
		super(text);
		// TODO Auto-generated constructor stub
		this.lightId = id;
	}

	public int getId() {
		return this.lightId;
	}
}
