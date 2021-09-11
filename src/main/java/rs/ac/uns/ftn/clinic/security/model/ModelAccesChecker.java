package rs.ac.uns.ftn.clinic.security.model;

public interface ModelAccesChecker {
    public AccessCheckerType getType();

    public boolean canAccess(Long modelId, Long userId, Object permission);
}
