package ubbl.query_service.repository;

import org.springframework.data.repository.CrudRepository;

import ubbl.query_service.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}