package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceImplTest {
    private static final RegistrationService registrationServiceImpl = new RegistrationServiceImpl();;
    private static final User user = new User();

    @BeforeAll
    public static void addUser() {
        User user_1 = new User();
        user_1.setLogin("Benson");
        user_1.setId(1234567890L);
        user_1.setAge(20);
        user_1.setPassword("123456qwe");
        User user_2 = new User();
        user_2.setLogin("Alessandro");
        user_2.setId(1234567890L);
        user_2.setAge(20);
        user_2.setPassword("123456qwe");
        Storage.people.add(user_1);
        Storage.people.add(user_2);
    }

    @BeforeEach
    public void setUp() {
        user.setLogin("Benson");
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword("123456qwe");
    }

    @Test
    public void register_nullUser_NotOk() {
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(null);
        });
    }

    @Test void register_userNotFound_NotOk() {
        user.setLogin("qwertyuu");
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_nullLogin_NotOk() {
        user.setLogin(null);
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_emptyLogin_NotOk() {
        user.setLogin("");
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_5lengthLogin_NotOk() {
        user.setLogin("qwert");
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_6lengthLogin_Ok() {
        assertEquals(user, registrationServiceImpl.register(user));
    }

    public void register_10lengthLogin_Ok() {
        user.setLogin("Alessandro");
        assertEquals(user, registrationServiceImpl.register(user));
    }

    @Test
    public void register_nullPassword_NotOk() {
        user.setPassword(null);
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_emptyPassword_NotOk() {
        user.setPassword("");
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_5lengthPassword_NotOk() {
        user.setPassword("q2w4t");
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_6lengthPassword_Ok() {
        user.setPassword("qwe123");
        assertEquals(user, registrationServiceImpl.register(user));
    }

    @Test
    public void register_10lengthPassword_Ok() {
        user.setPassword("qwe123f52z");
        assertEquals(user, registrationServiceImpl.register(user));
    }


    @Test
    public void register_negativeAge_NoOk() {
        user.setAge(-15);
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_below18Age_NoOk() {
        user.setAge(17);
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_equal18_Ok() {
        user.setAge(18);
        assertEquals(user, registrationServiceImpl.register(user));
    }

    @Test
    public void register_above18Age_Ok() {
        user.setAge(30);
        assertEquals(user, registrationServiceImpl.register(user));
    }
}