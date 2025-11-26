package com.tuwaiq.lab11_blogsystem.Repository;

import com.tuwaiq.lab11_blogsystem.Model.Comment;
import com.tuwaiq.lab11_blogsystem.Model.Post;
import com.tuwaiq.lab11_blogsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(Integer id);

    @Query("select users from User users where users.email like %?1%")
    List<User> getUsersWithSpecificEmail(String emailDomain);

    List<User> findUserByUsernameContaining(String username);

    @Query("select users from User users where users.id not in ?1 and users.id not in ?2")
    List<User> getUnactiveUsers(List<Post> posts, List<Comment> comments);       /* for later */
}
