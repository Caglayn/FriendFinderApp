package com.bilgeadam.service;

import com.bilgeadam.config.security.JwtTokenManager;
import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.FindByAuthIdDto;
import com.bilgeadam.dto.request.ProfileRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import com.bilgeadam.manager.ProfileManager;
import com.bilgeadam.mapper.UserMapper;
import com.bilgeadam.repository.IUserRepository;
import com.bilgeadam.repository.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileManager profileManager;
    private final JwtTokenManager jwtTokenManager;

    public UserService(IUserRepository userRepository, UserMapper userMapper, ProfileManager profileManager, JwtTokenManager jwtTokenManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.profileManager = profileManager;
        this.jwtTokenManager = jwtTokenManager;
    }

    public User saveRequest(RegisterRequestDto dto){
        User user = userMapper.toUser(dto);
        return userRepository.save(user);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void update(User user){
        userRepository.save(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public DoLoginResponseDto findByUsernameAndPassword(DoLoginRequestDto dto){
        Optional<User> user = userRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user.isPresent())
            return userMapper.toDoLoginResponseDto(user.get());
        return new DoLoginResponseDto();
    }

    public String register(RegisterRequestDto dto){
        User user = this.saveRequest(dto);

        return profileManager.save(ProfileRequestDto.builder()
                .authid(user.getId())
                .email(dto.getEmail())
                .firstname(dto.getName())
                .lastname(dto.getLastname())
                .country(dto.getCountry())
                .city(dto.getCity())
                .gender(dto.getSex())
                .build()).getBody();
    }

    public boolean isUser(String username, String password){
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isPresent())
            return true;
        return false;
    }

    @Cacheable(value = "merhaba_cache")
    public String merhaba(String mesaj){
        try {
            Thread.sleep(3500);
        }catch (Exception e){
        }
        return mesaj;
    }

    public Optional<User> getUser(DoLoginRequestDto dto){
        return userRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
    }

    public DoLoginResponseDto getProfile(DoLoginRequestDto dto){
        Optional<User> user = userRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user.isPresent()){
            long authId = user.get().getId();
            String profileId = profileManager.findByAuthId(FindByAuthIdDto.builder().authId(authId).build()).getBody();
            Optional<String> token = jwtTokenManager.createToken(profileId);
            if (profileId.equals("")){
                return DoLoginResponseDto.builder().error(500).build();
            }
            else {
                if (token.isPresent())
                    return DoLoginResponseDto.builder().profileId(profileId).token(token.get()).error(200).build();
                else
                    return DoLoginResponseDto.builder().error(411).build();
            }
        }
        return DoLoginResponseDto.builder().error(410).build();
    }

    public boolean verifyToken(String token) {
        return jwtTokenManager.validateToken(token);
    }
}
