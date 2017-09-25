/*
 */
package hu.javagladiators.app.hero.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author krisztian
 */
public class AdministrationType extends ResourceSupport{
        private byte prioritization;

    public AdministrationType() {
    }

    @JsonCreator
    public AdministrationType(@JsonProperty("prioritization") byte prioritization) {
        this.prioritization = prioritization;
    }

    public byte getPrioritization() {
        return prioritization;
    }

    public void setPrioritization(byte prioritization) {
        this.prioritization = prioritization;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.prioritization;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AdministrationType other = (AdministrationType) obj;
        if (this.prioritization != other.prioritization) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdministrationType{" + "prioritization=" + prioritization + '}';
    }
        
        
}
