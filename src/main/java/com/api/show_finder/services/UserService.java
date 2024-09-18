package com.api.show_finder.services;

import com.api.show_finder.domain.model.Artist;
import com.api.show_finder.domain.model.User;
import com.api.show_finder.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String password, String displayName) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setDisplayName(displayName);
        return userRepository.save(user);
    }

    public User connectSpotify(String userId, List<Artist> favoriteArtists, String spotifyToken) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFavoriteArtists(favoriteArtists.stream().map(Artist::getName).collect(Collectors.toList()));
            user.setSpotifyToken(spotifyToken);
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
