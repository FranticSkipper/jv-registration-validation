package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistrationServiceImplTest {
    private static final RegistrationService registrationServiceImpl
            = new RegistrationServiceImpl();

    @BeforeEach
    public void preparePeopleStorage() {
        Storage.people.clear();
        User user = new User();
        user.setLogin("Benson");
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword("123456qwe");
        Storage.people.add(user);
    }

    @Test
    public void register_nullUser_NotOk() {
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(null);
        });
    }

    @Test void register_userAlreadyExists_NotOk() {
        User user = new User();
        user.setLogin("Benson");
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword("123456qwe");
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test void register_userNotExists_Ok() {
        User user = new User();
        user.setLogin("qwertyui");
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword("123456qwe");
        assertEquals(user, registrationServiceImpl.register(user));
    }

    @Test
    public void register_nullLogin_NotOk() {
        User user = new User();
        user.setLogin(null);
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword("123456qwe");
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_emptyLogin_NotOk() {
        User user = new User();
        user.setLogin("");
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword("123456qwe");
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_5lengthLogin_NotOk() {
        User user = new User();
        user.setLogin("qwert");
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword("123456qwe");
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_6lengthLogin_Ok() {
        User user = new User();
        user.setLogin("qwerty");
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword("123456qwe");
        assertEquals(user, registrationServiceImpl.register(user));
    }

    @Test
    public void register_10lengthLogin_Ok() {
        User user = new User();
        user.setLogin("qwertasdfg");
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword("123456qwe");
        assertEquals(user, registrationServiceImpl.register(user));
    }

    @Test
    public void register_nullPassword_NotOk() {
        User user = new User();
        user.setLogin("qwertasdfg");
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword(null);
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_emptyPassword_NotOk() {
        User user = new User();
        user.setLogin("qwertasdfg");
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword("");
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_5lengthPassword_NotOk() {
        User user = new User();
        user.setLogin("qwertasdfg");
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword("q2w4t");
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_6lengthPassword_Ok() {
        User user = new User();
        user.setLogin("qwertasdfg");
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword("qwe123");
        assertEquals(user, registrationServiceImpl.register(user));
    }

    @Test
    public void register_10lengthPassword_Ok() {
        User user = new User();
        user.setLogin("qwertasdfg");
        user.setId(1234567890L);
        user.setAge(20);
        user.setPassword("qwe123f52z");
        assertEquals(user, registrationServiceImpl.register(user));
    }

    @Test
    public void register_negativeAge_NoOk() {
        User user = new User();
        user.setLogin("qwertasdfg");
        user.setId(1234567890L);
        user.setAge(-15);
        user.setPassword("123456qwe");
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_below18Age_NoOk() {
        User user = new User();
        user.setLogin("qwertasdfg");
        user.setId(1234567890L);
        user.setAge(17);
        user.setPassword("123456qwe");
        assertThrows(InvalidateUserData.class, () -> {
            registrationServiceImpl.register(user);
        });
    }

    @Test
    public void register_equal18_Ok() {
        User user = new User();
        user.setLogin("qwertasdfg");
        user.setId(1234567890L);
        user.setAge(18);
        user.setPassword("123456qwe");
        assertEquals(user, registrationServiceImpl.register(user));
    }

    @Test
    public void register_above18Age_Ok() {
        User user = new User();
        user.setLogin("qwertasdfg");
        user.setId(1234567890L);
        user.setAge(30);
        user.setPassword("123456qwe");
        assertEquals(user, registrationServiceImpl.register(user));
    }
}
