import gui.Window;

public class Watch {
	private static final int SIZE = 400;

	public static void main(String[] args) {
		Window window = new Window("Watch", SIZE, SIZE);
		window.open();
		double radius = SIZE / 4;

		while (window.isOpen()) {
			long milliseconds = System.currentTimeMillis();

			int seconds = (int) ((milliseconds / 1000) % 60);
			int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
			int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24) + 1;     // UTC +01:00

			String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			System.out.println(time);

			hours = hours > 12 ? hours - 12 : hours;    	// Set hours to be between 0 and 12 for the analog clock 
			
			// Counting radians 
			double radHour = hours * Math.PI / 6 - Math.PI / 2;
			double radMin = minutes * Math.PI / 30 - Math.PI / 2;
			double radSec = seconds * Math.PI / 30 - Math.PI / 2;

			window.setColor(127, 127, 127);
			window.fillCircle(SIZE / 2, SIZE / 2, radius);

			window.setColor(0, 0, 0);

			window.setFontSize(15);    						// Let font size of the displayed string be 15
			window.setBold(true);      						// Let string be bold
			window.drawStringCentered(time, 200, 75);    	// Display time

			window.setStrokeWidth(5);
			window.drawLine(SIZE / 2, SIZE / 2, Math.cos(radHour) * radius * 0.6 + SIZE / 2,
					Math.sin(radHour) * radius * 0.6 + SIZE / 2); // Hour hand

			window.setStrokeWidth(3);
			window.drawLine(SIZE / 2, SIZE / 2, Math.cos(radMin) * radius * 0.8 + SIZE / 2,
					Math.sin(radMin) * radius * 0.8 + SIZE / 2); // Minute hand

			window.setColor(220, 0, 0);
			window.setStrokeWidth(1);
			window.drawLine(SIZE / 2, SIZE / 2, Math.cos(radSec) * radius * 0.9 + SIZE / 2,
					Math.sin(radSec) * radius * 0.9 + SIZE / 2); // Seconds hand

			// display everything and then fill the canvas with white:
			window.refreshAndClear(20);
		}
	}
}

