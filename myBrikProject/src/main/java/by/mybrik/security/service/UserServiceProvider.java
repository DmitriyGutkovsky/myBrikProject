package by.mybrik.security.service;

import by.mybrik.domain.Role;
import by.mybrik.domain.SystemRoles;
import by.mybrik.domain.Users;
import by.mybrik.repository.impl.RoleRepository;
import by.mybrik.repository.impl.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

//@Service
//@RequiredArgsConstructor
//public class UserServiceProvider implements UserDetailsService {
//
//    public final UsersRepository usersRepository;
//
//    private final RoleRepository roleRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        try {
//            Optional<Users> searchResult = usersRepository.findByLogin(username);
//            if (searchResult.isPresent()) {
//                Users user = searchResult.get();
//                return new org.springframework.security.core.userdetails.User(
//                        user.getLogin(),
//                        user.getPassword(),
////                      get array of roles that user have:  ["ROLE_USER", "ROLE_ADMIN"]
//                        AuthorityUtils.commaSeparatedStringToAuthorityList(roleRepository.findUserRoles(user.getId()).stream().map(Role::getRoleName).map(SystemRoles::name).collect(Collectors.joining(",")))
//                );
//            } else {
//                throw new UsernameNotFoundException(String.format("No user found with login '%s'.", username));
//            }
//        } catch (Exception e) {
//            throw new UsernameNotFoundException("User with this login not found");
//        }
//    }
//}
