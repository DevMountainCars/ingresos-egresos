package com.devmountain.ingresosegresos.empleado;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado empleado = usuarioRepository.findByEmail(username);

        if (StringUtils.isBlank(username)) {
            log.error(String.format("Error login: no existe el usuario con email %s", username));
            throw new UsernameNotFoundException(String.format("Email %s no registrado", username));
        }

        if (Objects.isNull(empleado.getRol())) {
            log.error(String.format("Error login: el usuario con email %s no tiene roles asignados", username));
            throw new UsernameNotFoundException(String.format("No hay roles asignados para el usuarios con email %s", username));
        }

        return new User(empleado.getEmail(), empleado.getPassword(), empleado.getEstado(),
                true, true, true,
                List.of(new SimpleGrantedAuthority("ROLE_".concat(empleado.getRol().name()))));
    }
}
