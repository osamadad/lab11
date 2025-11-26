package com.tuwaiq.lab11_blogsystem.Service;

import com.tuwaiq.lab11_blogsystem.Model.Comment;
import com.tuwaiq.lab11_blogsystem.Model.Post;
import com.tuwaiq.lab11_blogsystem.Model.User;
import com.tuwaiq.lab11_blogsystem.Repository.CommentRepository;
import com.tuwaiq.lab11_blogsystem.Repository.PostRepository;
import com.tuwaiq.lab11_blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public void addUser(User user){
        user.setRegistrationDate(LocalDateTime.now());
        userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Boolean updateUser(Integer id, User user){
        User oldUser=userRepository.findUserById(id);
        if (oldUser==null){
            return false;
        }else {
            oldUser.setUsername(user.getUsername());
            oldUser.setPassword(user.getPassword());
            oldUser.setEmail(user.getEmail());
            userRepository.save(oldUser);
            return true;
        }
    }

    public Boolean deleteUser(Integer id){
        User user=userRepository.findUserById(id);
        if (user==null){
            return false;
        }else {
            userRepository.delete(user);
            return true;
        }
    }

    public List<User> getUsersWithSpecificDomain(String emailDomain){
        return userRepository.getUsersWithSpecificEmail(emailDomain);
    }

//    public List<User> getUnactiveUser(){
//        List<Post> posts=postRepository.findAll();
//        List<Comment> comments=commentRepository.findAll();
//    }

    public List<User> getUserByUsernameContaining(String name){
        return userRepository.findUserByUsernameContaining(name);
    }
}
