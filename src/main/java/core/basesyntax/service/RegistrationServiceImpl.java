package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();
    private static final IUserValidation userValidation = new UserValidation();

    @Override
    public User register(User user) {
        User validUser =  userValidation.validate(user);
        storageDao.add(validUser);
        return validUser;
    }
}
