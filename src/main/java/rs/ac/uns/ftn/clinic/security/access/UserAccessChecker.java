package rs.ac.uns.ftn.clinic.security.access;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class UserAccessChecker extends ModelAccesChecker {

    @Override
    public boolean canAccess(Serializable payload, Long userId, Object permission) {
        switch (permission.toString()) {
            case "read":
                return canRead(payload, userId);
            default:
                return false;
        }
    }

    private boolean canRead(Serializable payload, Long userId) {
        Long requestedUserId = (Long) payload;

        return requestedUserId == userId;
    }

    @Override
    protected AccessCheckerType getType() {
        return AccessCheckerType.USER;
    }
}
