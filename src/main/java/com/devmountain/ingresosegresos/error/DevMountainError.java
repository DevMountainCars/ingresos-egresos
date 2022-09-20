package com.devmountain.ingresosegresos.error;

public class DevMountainError extends RuntimeException {

    public DevMountainError(String mensaje) {
        super(mensaje);
    }

    public DevMountainError(String mensaje, Throwable error) {
        super(mensaje, error);
    }
}
