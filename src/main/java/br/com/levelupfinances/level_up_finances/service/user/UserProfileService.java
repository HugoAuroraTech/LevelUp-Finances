package br.com.levelupfinances.level_up_finances.service.user;

import br.com.levelupfinances.level_up_finances.domain.user.User;
import br.com.levelupfinances.level_up_finances.domain.user.UserProfile;
import br.com.levelupfinances.level_up_finances.repository.UserProfileRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRespository userProfileRespository;

    public void createUserProfile(User user){
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setXp(0);
        userProfile.setLevel(0);
        userProfile.setCoins(0);
        userProfileRespository.save(userProfile);
    }
}
