package ru.baster.study.market.core.service;

import lombok.RequiredArgsConstructor;
import ru.baster.study.market.core.repository.RoleRepository;
import org.springframework.stereotype.Service;
import ru.baster.study.market.core.model.Role;


@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }

}