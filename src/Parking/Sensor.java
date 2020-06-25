package Parking;

public class Sensor {
	Pasarela pasarela;

	public Sensor(Pasarela pasarela_) {
		pasarela = pasarela_;
	}

	public void activar() {
		pasarela.bajarBarrera();
	}

}
