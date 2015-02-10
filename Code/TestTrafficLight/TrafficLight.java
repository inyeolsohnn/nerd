class TrafficLight {
	/*
	 * A TrafficLight can either be RED or GREEN -- the boolean red determines this, 
	 * therefore determining what the CAR can do! 
	 */
	private boolean red; //Check whether the traffic light is RED or not
	
	public TrafficLight() {
		red = true; //initially traffic light is RED!
	}
	
	public Boolean trafficLightStatus(){
		return red;
	}
	
	public synchronized void Stop() {  //  if red is true cars at the traffic light have to stop
		while(red)  {
			try {
				System.out.println("CAR AT TRAFFIC LIGHT");
				wait(); //Car is a thread. Therefore car should wait when reaching red traffic light
			} catch(InterruptedException e) {}
		}
	}
	
	public synchronized void Red() 	{ //Traffic light is red
		red = true;
		System.out.println("Traffic Light: RED (STOP)");
	}
	
	public synchronized void Green() { //Traffic light has turned green
		red = false; //car can move because the traffic light it is at is GREEN!
		System.out.println("Traffic Light: GREEN (GO)");
	}
}
