package rs.ac.uns.ftn.clinic.security.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelAccessCheckerFactory {

    @Autowired
    private List<ModelAccesChecker> checkers;

    private static final Map<AccessCheckerType, ModelAccesChecker> checkerCache = new HashMap<>();

    @PostConstruct
    public void initMyServiceCache() {
        for (ModelAccesChecker checker : checkers) {
            checkerCache.put(checker.getType(), checker);
        }
    }

    public static ModelAccesChecker getChecker(String modelName) {
        ModelAccesChecker checker = checkerCache.get(AccessCheckerType.valueOf(modelName.toUpperCase()));

        if (checker == null) {
            throw new RuntimeException("Unknown service type: " + modelName);
        }

        return checker;
    }
}
