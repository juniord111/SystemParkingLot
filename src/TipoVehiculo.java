public enum TipoVehiculo {
    CARRO(5000.0),
    MOTO(2000.0),
    CAMION(10000.0),
    BUS(15000.0),
    BICICLETA(1000.0);

    private final double tarifaPorHora;

    TipoVehiculo(double tarifaPorHora){
        this.tarifaPorHora = tarifaPorHora;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }
}