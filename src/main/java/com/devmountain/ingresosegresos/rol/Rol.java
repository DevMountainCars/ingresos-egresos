package com.devmountain.ingresosegresos.rol;

public enum Rol {
    ADMIN("ROLE_ADMIN"),
    OPERARIO("ROLE_OPERARIO");

    private final String value;

    Rol(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
