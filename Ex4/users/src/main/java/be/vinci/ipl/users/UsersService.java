package be.vinci.ipl.users;

import be.vinci.ipl.users.data.AuthenticationProxy;
import be.vinci.ipl.users.dto.UnsafeCredentialsDTO;
import be.vinci.ipl.users.dto.UserWithUnsafeCredentialsDTO;
import feign.FeignException;
import feign.FeignException.FeignClientException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsersService {

    private final UsersRepository repository;
    private final AuthenticationProxy authenticationProxy;

    public UsersService(UsersRepository repository,
        AuthenticationProxy authenticationProxy) {
        this.repository = repository;
        this.authenticationProxy = authenticationProxy;
    }

    /**
     * Creates a new user in repository
     * @param user the information of the user
     * @return true if the user was created, false if another user exists with the same pseudo
     */
    public boolean createOne(UserWithUnsafeCredentialsDTO user) {
        String pseudo = user.getPseudo();
        if (repository.existsById(pseudo)) return false;
        /*UnsafeCredentialsDTO unsafeCredentialsDTO = new UnsafeCredentialsDTO();
        unsafeCredentialsDTO.setPseudo(user.getPseudo());
        unsafeCredentialsDTO.setPassword(user.getPassword());*/

        //boolean credentialsCreated = true;
        try {
            authenticationProxy.createOne(pseudo, user.toCredentials());
            repository.save(user.toUser());
            return true;
        } catch (FeignException.FeignClientException e) {
            //credentialsCreated = false;
            throw e;
        }

        /*if (credentialsCreated) {
            *//*User userWithoutCredentials = new User();
            userWithoutCredentials.setPseudo(user.getPseudo());
            userWithoutCredentials.setFirstname(user.getFirstname());
            userWithoutCredentials.setLastname(user.getLastname());
            repository.save(userWithoutCredentials);*//*
            repository.save(user.getUser());
            return true;
        }*/
        //return false;
    }

    /**
     * Reads a user in repository
     * @param pseudo the pseudo of the user
     * @return the user, or null if the user couldn't be found
     */
    public User readOne(String pseudo) {
        return repository.findById(pseudo).orElse(null);
    }

    /**
     * Updates a user in repository
     * @param user New values of the user
     * @return true if the user was updated, or false if the user couldn't be found
     */
    public boolean updateOne(UserWithUnsafeCredentialsDTO user) {
        String pseudo = user.getPseudo();
        if (!repository.existsById(pseudo)) return false;
        /*UnsafeCredentialsDTO unsafeCredentialsDTO = new UnsafeCredentialsDTO();
        unsafeCredentialsDTO.setPseudo(user.getPseudo());
        unsafeCredentialsDTO.setPassword(user.getPassword());*/

        //boolean credentialsUpdated = true;
        try {
            authenticationProxy.updateOne(pseudo, user.toCredentials());
            repository.save(user.toUser());
            return true;
        } catch (ResponseStatusException e) {
            //credentialsUpdated = false;
            throw e;
        }

        /*if (credentialsUpdated) {
            *//*User userWithoutCredentials = new User();
            userWithoutCredentials.setPseudo(user.getPseudo());
            userWithoutCredentials.setFirstname(user.getFirstname());
            userWithoutCredentials.setLastname(user.getLastname());*//*
            repository.save(user.toUser());
            return true;
        }
        return false;*/
    }

    /**
     * Deletes a user from repository and all reviews associated with it
     * @param pseudo the pseudo of the user
     * @return true if the user was deleted, or false if the user couldn't be found
     */
    public boolean deleteOne(String pseudo) {
        if (!repository.existsById(pseudo)) return false;
        try {
            authenticationProxy.deleteOne(pseudo);
        } catch (ResponseStatusException e) {
            return false;
        }
        repository.deleteById(pseudo);
        return true;
    }

}
