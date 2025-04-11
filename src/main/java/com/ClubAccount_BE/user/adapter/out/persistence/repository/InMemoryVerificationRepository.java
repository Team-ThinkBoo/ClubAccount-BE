package com.ClubAccount_BE.user.adapter.out.persistence.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryVerificationRepository implements VerificationRepository {
    private final Map<String, String> store = new ConcurrentHashMap<>();

    @Override
    public void saveCode(String email, String code) {
        store.put(email, code);
    }

    @Override
    public String getCode(String email) {
        return store.get(email);
    }
}
