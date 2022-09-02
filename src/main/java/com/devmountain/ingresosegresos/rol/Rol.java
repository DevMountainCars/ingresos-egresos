package com.devmountain.ingresosegresos.rol;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Rol {
    ADMIN("Admin"),
    OPERARIO("Operario");

    @Getter
    private final String value;
}
