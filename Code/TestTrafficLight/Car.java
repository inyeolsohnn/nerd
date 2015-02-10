class Car extends Thread {
	private TrafficLight[] trafficLights;
	private int roadPosition;
	private String name;
	private String status = "Moving";

	public Car(String name, TrafficLight[] trafficLights) {
		this.name = name;
		this.trafficLights = trafficLights;
		roadPosition = 0;
		start();
	}

	public String getCarName() {
		return name;
	}
	
	public void setStatus(boolean flag){
		if(flag){
			status = "Car Stopped";
		}else{
			status = "Car Moving";
		}
	}
	
	public String getStatus(){
		
		return status;
	}

	private synchronized void carCanDrive() {
		try {
			Thread.sleep((int) (Math.random() * 100));
		} catch (InterruptedException e) {
		}
		roadPosition++;
		if (roadPosition == trafficLights.length) // Car has gone down the road
			roadPosition = 0;//Reset the cars position.
	}

	public synchronized int position() {
		return roadPosition;
	}

	public void run() {
		while (true) {
			try {
				sleep((int) (Math.random() * 100)); // cars are moving.
			} catch (InterruptedException e) {
			}

			trafficLights[roadPosition].Stop(); // wait before a traffic light
			carCanDrive(); // go on driving to next traffic light
		}
	}

}