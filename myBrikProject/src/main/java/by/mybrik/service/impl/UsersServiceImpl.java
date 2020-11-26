package by.mybrik.service.impl;

import by.mybrik.domain.Users;
import by.mybrik.repository.UsersRepository;
import by.mybrik.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public Users save(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public Optional<Users> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Users update(Users user) {
        return usersRepository.update(user);
    }

    @Override
    public Long delete(Users user) {
        return usersRepository.delete(user);
    }
}
