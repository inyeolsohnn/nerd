class Car extends Thread {
	private TrafficLight[] trafficLights;
	private int roadPosition;
	private String name;
	private String status = "Moving";

	public Car(String name) {
		this.name = name;
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

}
