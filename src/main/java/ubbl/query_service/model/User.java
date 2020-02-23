package ubbl.query_service.model;

import java.util.Arrays;
import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data @NoArgsConstructor @RequiredArgsConstructor
@Document(collection="user")
public class User implements UserDetails {
    @Id
    @NonNull
    @NotBlank(message="Provide a username atleast 5 characters long")
    @Size(min=5, message="Must be atleast five characters long")
    private String username;
    
    @NonNull
    @NotBlank(message="Provide a password atleast 5 characters long")
    @Size(min=5, message="Must be atleast five characters long")
    private String password;
    
    private Collection<GrantedAuthority> authorities;
    
    @NonNull
    @NotBlank(message="Provide a name atleast 2 characters long")
    @Size(min=2, message="Must be atleast two characters long")
    private String firstName;
    
    @NonNull
    @NotBlank(message="Provide a name atleast 2 characters long")
    @Size(min=2, message="Must be atleast two characters long")
    private String lastName;
    
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}