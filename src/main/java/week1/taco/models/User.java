package week1.taco.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // simple comma-separated roles, or a separate authorities table if desired
    private String roles;

    public Set<String> getRoleSet() {
        Set<String> set = new HashSet<>();
        if (roles != null && !roles.isEmpty()) {
            for (String r : roles.split(",")) {
                set.add(r.trim());
            }
        }
        return set;
    }
}