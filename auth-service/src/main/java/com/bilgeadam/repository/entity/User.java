package com.bilgeadam.repository.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "tbluser")
public class User {
    @Id
    @SequenceGenerator(name = "sq_tbluser_id", sequenceName = "sq_tbluser_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "sq_tbluser_id")
    long id;
    String username;
    String password;

    /**
     * status : is user active ?
     * 0 -> not active
     * 1 -> active
     * 2 -> ignored
     */
    int status;
    long createDate;
    long updateDate;
}
