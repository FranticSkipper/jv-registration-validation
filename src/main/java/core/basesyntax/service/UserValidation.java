package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class UserValidation implements IUserValidation {
    private static final int MIN_LOGIN_LEN = 6;
    private static final int MIN_PASSWORD_LEN = 6;
    private static final int MIN_AGE = 18;
    private static final StorageDao storageDaoImpl = new StorageDaoImpl();

    @Override
    public User validate(User user) {
        if (user == null) {
            throw new InvalidateUserData("User cannot be null!");
        }

        if (user.getLogin() == null) {
            throw new InvalidateUserData("Login cannot be null!");
        }

        if (storageDaoImpl.get(user.getLogin()) == null) {
            throw new InvalidateUserData("User is not found!");
        }

        if (user.getLogin().length() < MIN_LOGIN_LEN) {
            throw new InvalidateUserData("Login must have a least" + MIN_LOGIN_LEN + "letters!");
        }

        if (user.getPassword() == null) {
            throw new InvalidateUserData("Password cannot be null!");
        }

        if (user.getPassword().length() < MIN_PASSWORD_LEN) {
            throw new InvalidateUserData("Password must have a least " + MIN_PASSWORD_LEN
                    + " letters!");
        }

        if (user.getAge() < MIN_AGE) {
            throw new InvalidateUserData("Not valid age: " + user.getAge()
                    + ". Min allowed age is: " + MIN_AGE);
        }

        return user;
    }
}
