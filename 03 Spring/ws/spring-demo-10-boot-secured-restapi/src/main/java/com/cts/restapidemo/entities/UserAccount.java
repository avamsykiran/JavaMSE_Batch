package com.cts.restapidemo.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class UserAccount {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    @NotNull(message = "user name can not be empty")
    @NotBlank(message = "user name can not be empty")
    private String username;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotNull(message = "password can not be empty")
    @NotBlank(message = "password can not be empty")
    private String password;
    
    @Pattern(regexp = "ROLE_ADMIN|ROLE_USER",message = "role must be any of these two ROLE_ADMIN|ROLE_USER")
    private String roles; // e.g., "ROLE_USER,ROLE_ADMIN"

    public UserAccount() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, password, roles, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(roles, other.roles) && Objects.equals(username, other.username);
	}
    
    
}