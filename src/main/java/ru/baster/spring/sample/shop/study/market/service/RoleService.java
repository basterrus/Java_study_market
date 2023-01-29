package ru.baster.spring.sample.shop.study.market.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.baster.spring.sample.shop.study.market.model.Role;
import ru.baster.spring.sample.shop.study.market.repository.RoleRepository;


@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_CUSTOMER").get();
    }

}