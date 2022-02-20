package com.bilgeadam.dto.request;

import com.bilgeadam.repository.entity.Interest;
import com.bilgeadam.repository.entity.Profile;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequestDto implements Serializable {
    private long authid;
    private String firstname;
    private String lastname;
    private String email;
    private String birthDate;
    private String country;
    private String city;
    private String gender;
    private String about;
    List<Interest> interests;
    Profile.Education education;
    Profile.Work work;
}
