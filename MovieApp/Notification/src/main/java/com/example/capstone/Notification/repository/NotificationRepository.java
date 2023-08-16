package com.example.capstone.Notification.repository;

import com.example.capstone.Notification.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<User, String> {
    User findByEmail (String email);
}
