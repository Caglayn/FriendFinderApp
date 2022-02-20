package com.bilgeadam.manager;
import static com.bilgeadam.constant.RestApiUrls.*;

import com.bilgeadam.dto.request.FindByAuthIdDto;
import com.bilgeadam.dto.request.IsProfileExistsDto;
import com.bilgeadam.dto.request.ProfileRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(url = "${ffapp.userservice}"+VERSION+PROFILE, name = "profileFeignClient", decode404 = true)
public interface ProfileManager {

    @PostMapping(SAVE)
    public ResponseEntity<String> save(@RequestBody @Valid ProfileRequestDto dto);

    @PostMapping(FINDBYAUTHID)
    public ResponseEntity<String> findByAuthId(FindByAuthIdDto dto);

    @PostMapping(ISPROFILEEXISTBYID)
    public ResponseEntity<Boolean> isProfileExistById(@RequestBody IsProfileExistsDto dto);
}
