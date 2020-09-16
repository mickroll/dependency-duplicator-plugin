package com.github.mickroll.maven.dependency_duplicator_plugin;

import org.apache.maven.model.Dependency;

/**
 * Clones dependencies and applies changes according to configuration.
 *
 * @author mickroll
 * @see DuplicatorConfig
 */
public class DependencyCloner {

    private final DuplicatorConfig config;

    public DependencyCloner(final DuplicatorConfig config) {
        this.config = config;
    }

    public Dependency clone(final Dependency source) {
        final Dependency clone = source.clone();
        config.getTargetClassifier().ifPresent(clone::setClassifier);
        config.getTargetScope().ifPresent(clone::setScope);
        config.getTargetType().ifPresent(clone::setType);
        clone.clearManagementKey(); // value is cached, beyond changes via setters
        return clone;
    }
}