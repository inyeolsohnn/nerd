public class TrafficLightSimulation {

	public static void main(String[] args) {
		TrafficLight[] trafficLights = new TrafficLight[2]; 
		Car[] CarList = new Car[2];
		
		
		TrafficLight trafficLight1 = new TrafficLight();
		TrafficLight trafficLight2 = new TrafficLight();
		trafficLights[0] = trafficLight1;
		trafficLights[1] = trafficLight2;
		
		Car c1 = new Car("Honda",trafficLights);
		CarList[0] = c1;
		

		TrafficLight tmp = trafficLights[0];
		tmp.Red();
		c1.setStatus(tmp.trafficLightStatus());
		System.out.println(c1.getCarName()+" "+c1.getStatus());
		tmp.Green();
		c1.setStatus(tmp.trafficLightStatus());
		System.out.println(c1.getCarName()+" "+c1.getStatus());
			
		
	} // main
}