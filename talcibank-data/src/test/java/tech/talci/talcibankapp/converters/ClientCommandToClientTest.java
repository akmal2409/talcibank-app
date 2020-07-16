package tech.talci.talcibankapp.converters;

import org.junit.Before;
import org.junit.Test;
import tech.talci.talcibankapp.commands.ClientCommand;
import tech.talci.talcibankapp.domain.Client;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ClientCommandToClientTest {

    public static final Long ID_VALUE = Long.valueOf(2L);
    public static final String LAST_NAME = "Last name";
    public static final String FIRST_NAME = "FIRST NAME";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String SECRET_PHRASE = "Secret Phrase";
    public static final String ADDRESS = "Address";
    public static final String PHONE_NUMBER = "313213";
    public static final LocalDate BIRTH_DATE = LocalDate.now();

    ClientCommandToClient converter;

    @Before
    public void setUp() throws Exception {
        converter = new ClientCommandToClient();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new ClientCommand()));
    }

    @Test
    public void convert() {
        //given
        ClientCommand command = new ClientCommand();
        command.setEmail(EMAIL);
        command.setFirstName(FIRST_NAME);
        command.setLastName(LAST_NAME);
        command.setId(ID_VALUE);
        command.setPassword(PASSWORD);
        command.setSecretPhrase(SECRET_PHRASE);
        command.setAddress(ADDRESS);
        command.setPhoneNumber(PHONE_NUMBER);
        command.setBirthDate(BIRTH_DATE);

        //when
        Client client = converter.convert(command);

        //then
        assertNotNull(client);
        assertEquals(ID_VALUE, client.getId());
        assertEquals(LAST_NAME, client.getLastName());
        assertEquals(FIRST_NAME, client.getFirstName());
        assertEquals(PASSWORD, client.getPassword());
        assertEquals(SECRET_PHRASE, client.getSecretPhrase());
        assertEquals(ADDRESS, client.getAddress());
        assertEquals(PHONE_NUMBER, client.getPhoneNumber());
        assertEquals(BIRTH_DATE, client.getBirthDate());
    }
}