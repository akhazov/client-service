package com.akhazov.clientservice.repository;

import com.akhazov.clientservice.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}