package es.ubu.gii.ISOAssetManager.config.service;

import es.ubu.gii.ISOAssetManager.config.details.SuperCustomerUserDetails;
import es.ubu.gii.ISOAssetManager.model.Rol;
import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public SuperCustomerUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario user = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        Collection<? extends GrantedAuthority> authorities =
                mapRolesToAuthorities(user.getRoles());

        SuperCustomerUserDetails details = new SuperCustomerUserDetails();
        details.setUsername(user.getEmail());
        details.setPassword(user.getPassword());
        details.setUserID(user.getId());
        details.setNombre(user.getNombre());
        details.setUsuario(user);
        details.setAuthorities(authorities);

        // ==============
        // CLAVES RSA
        // ==============
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            if (user.getPublicKey() == null || user.getPrivateKey() == null) {
                // Generamos par nuevo y lo persistimos
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                keyPairGenerator.initialize(2048);
                KeyPair keyPair = keyPairGenerator.generateKeyPair();

                user.setPublicKey(keyPair.getPublic().getEncoded());
                user.setPrivateKey(keyPair.getPrivate().getEncoded());
                usuarioRepository.save(user);

                details.setPublicKey(keyPair.getPublic());
                details.setPrivateKey(keyPair.getPrivate());
            } else {
                // Reconstruimos desde BD
                X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(user.getPublicKey());
                PublicKey publicKey = keyFactory.generatePublic(pubSpec);

                PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(user.getPrivateKey());
                PrivateKey privateKey = keyFactory.generatePrivate(privSpec);

                details.setPublicKey(publicKey);
                details.setPrivateKey(privateKey);
            }

        } catch (Exception e) {
            // Si algo falla, dejamos las claves a null y lo registramos
            System.err.println("Error gestionando claves RSA para " + email);
            e.printStackTrace();
        }

        return details;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Rol> roles) {
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getNombre().toUpperCase()))
                .collect(Collectors.toList());
    }
}