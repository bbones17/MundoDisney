package com.disney.mundo.Model.Repositories.Security;


import com.disney.mundo.Model.Entities.Security.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("confirmationTokenRepository")
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
        ConfirmationToken findByConfirmationToken(String confirmationToken);
}
