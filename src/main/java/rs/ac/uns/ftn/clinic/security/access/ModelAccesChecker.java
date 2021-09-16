package rs.ac.uns.ftn.clinic.security.access;

import java.io.Serializable;
import java.util.stream.Collectors;

import rs.ac.uns.ftn.clinic.model.User;

public abstract class ModelAccesChecker {
    protected abstract AccessCheckerType getType();

    public abstract boolean canAccess(Serializable payload, Long userId, Object permission);

    protected boolean hasRole(User user, String roleName) {
        return user.getRoles().stream().filter(role -> role.getName().equals(roleName)).collect(Collectors.toSet())
                .size() > 0;

    }
}
