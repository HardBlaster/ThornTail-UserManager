package hu.innovitech.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String hashedPass;
    private String name;
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();
}
