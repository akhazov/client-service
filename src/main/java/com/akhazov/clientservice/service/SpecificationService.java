package com.akhazov.clientservice.service;

import com.akhazov.clientservice.model.dto.FindClientRequest;
import com.akhazov.clientservice.model.entity.Client;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SpecificationService {

    public Specification<Client> getSearchClientSpec(FindClientRequest request) {
        return firstNameLike(request.firstName())
                .and(middleNameLike(request.middleName()))
                .and(lastNameLike(request.lastName()))
                .and(emailLike(request.email()))
                .and(phoneNumberLike(request.phoneNumber()));
    }

    private Specification<Client> firstNameLike(String firstName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
    }

    private Specification<Client> middleNameLike(String middleName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("middleName")), "%" + middleName.toLowerCase() + "%");
    }

    private Specification<Client> lastNameLike(String lastName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    private Specification<Client> emailLike(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }

    private Specification<Client> phoneNumberLike(String phoneNumber) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("phoneNumber")), "%" + phoneNumber + "%");
    }

}
