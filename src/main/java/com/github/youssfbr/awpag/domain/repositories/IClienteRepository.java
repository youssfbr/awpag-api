package com.github.youssfbr.awpag.domain.repositories;

import com.github.youssfbr.awpag.domain.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
