package com.orderplatform.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserProfile {

    @Id
    private String id;

    private String email;
    private String name;
}
