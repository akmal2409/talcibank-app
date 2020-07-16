package tech.talci.talcibankapp.converters;

import org.junit.Before;
import org.junit.Test;
import tech.talci.talcibankapp.commands.ClientCommand;
import tech.talci.talcibankapp.domain.Client;

import java.lang.ref.Cleaner;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class ClientToClientCommandTest {

    public static final Long ID_VALUE = Long.valueOf(2L);
    public static final String LAST_NAME = "Last name";
    public static final String FIRST_NAME = "FIRST NAME";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String SECRET_PHRASE = "Secret Phrase";
    public static final String ADDRESS = "Address";
    public static final String PHONE_NUMBER = "313213";
    public static final LocalDate BIRTH_DATE = LocalDate.now();

    ClientToClientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new ClientToClientCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Client()));
    }

    @Test
    public void convert() {

        //given
        Client client = new Client();
        client.setEmail(EMAIL);
        client.setFirstName(FIRST_NAME);
        client.setLastName(LAST_NAME);
        client.setId(ID_VALUE);
        client.setPassword(PASSWORD);
        client.setSecretPhrase(SECRET_PHRASE);
        client.setAddress(ADDRESS);
        client.setPhoneNumber(PHONE_NUMBER);
        client.setBirthDate(BIRTH_DATE);

        //when
        ClientCommand command = converter.convert(client);

        //then
        assertNotNull(command);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(LAST_NAME, command.getLastName());
        assertEquals(FIRST_NAME, command.getFirstName());
        assertEquals(PASSWORD, command.getPassword());
        assertEquals(SECRET_PHRASE, command.getSecretPhrase());
        assertEquals(ADDRESS, command.getAddress());
        assertEquals(PHONE_NUMBER, command.getPhoneNumber());
        assertEquals(BIRTH_DATE, command.getBirthDate());

    }
}