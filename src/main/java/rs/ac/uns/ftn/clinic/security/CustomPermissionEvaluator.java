package rs.ac.uns.ftn.clinic.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import rs.ac.uns.ftn.clinic.security.model.ModelAccesChecker;
import rs.ac.uns.ftn.clinic.security.model.ModelAccessCheckerFactory;

/**
 * @see https://www.baeldung.com/spring-security-create-new-custom-security-expression
 * 
 *      Examples of usage within controlles:
 * 
 *      @PreAuthorize("hasAuthority('PRIVILEGE_NAME')") - Only evaluate
 *      privilege name
 * 
 *      @PostAuthorize("hasPermission(returnObject, 'read')") - User has to have
 *      a permission in format:
 *      whateverTheMethodReturns.getClass().getSimpleName().toUpperCase()_READ
 * 
 *      Custom evaluator - triggers per model class from
 *      ModelAccessCheckerFactory @PreAuthorize("hasPermission(#appoinmentId, 'Appointment', 'read')") @GetMapping("/appoinments/{appoinmentId}")
 * 
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
        if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)) {
            return false;
        }
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();

        return hasPrivilege(auth, targetType, permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
        for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
            if (grantedAuth.getAuthority().startsWith(targetType)) {
                if (grantedAuth.getAuthority().contains(permission)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetId == null)) {
            return false;
        }

        Long modelId = (Long) targetId;
        Long userId = ((UserPrincipal) auth.getPrincipal()).getId();

        return canAccessModel(targetType, modelId, userId, permission);
    }

    public boolean canAccessModel(String modelName, Long modelId, Long userId, Object permission) {
        ModelAccesChecker modelAccessChecker = ModelAccessCheckerFactory.getChecker(modelName);

        if (modelAccessChecker == null) {
            return false;
        }

        return modelAccessChecker.canAccess(modelId, userId, permission);
    }
}
