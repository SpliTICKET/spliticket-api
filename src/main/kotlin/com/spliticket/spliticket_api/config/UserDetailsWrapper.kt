import com.spliticket.spliticket_api.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsWrapper(private val user: User) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        user.permissions.forEach { permission -> authorities.add(permission) }
        return authorities
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return user.active
    }

    override fun isAccountNonLocked(): Boolean {
        return user.active
    }

    override fun isCredentialsNonExpired(): Boolean {
        return user.active
    }

    override fun isEnabled(): Boolean {
        return user.active
    }
}
