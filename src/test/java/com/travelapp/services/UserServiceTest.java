package com.travelapp.services;

import com.travelapp.exceptions.BadRequestException;
import com.travelapp.exceptions.ResourceNotFoundException;
import com.travelapp.exceptions.ResourcePersistenceException;
import com.travelapp.models.Role;
import com.travelapp.models.Ticket;
import com.travelapp.models.User;
import com.travelapp.repos.UserRepository;
import com.travelapp.web.dtos.Credentials;
import com.travelapp.web.dtos.Principal;
import com.travelapp.web.dtos.TicketDto;
import com.travelapp.web.dtos.UserDto;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private static UserRepository mockRepo;

    private static UserService sut;

    private static List<User> mockUsers;

    @BeforeClass
    public static void setUp() {
        mockRepo = mock(UserRepository.class);
        mockUsers = new ArrayList<>();
        sut = new UserService(mockRepo);
        User a = new User(1, "aanderson", "password", "Alex",
                "Anderson", "aanderson@gmail.com", new Role(1, "Admin"));
        User b = new User(2, "bbailey", "password", "Bob",
                "Bailey", "bbailey@gmail.com", new Role(2, "User"));
        User c = new User(3, "ccalhoun", "password", "Charlie",
                "Calhoun", "ccalhoun@gmail.com", new Role(2, "User"));
        User d = new User(4, "ddavis", "password", "David",
                "Davis", "ddavis@gmail.com", new Role(2, "User"));
        User e = new User(5, "eeinstein", "password", "Emily",
                "Einstein", "eeinstein@gmail.com", new Role(2, "User"));
        mockUsers.add(a);
        mockUsers.add(b);
        mockUsers.add(c);
        mockUsers.add(d);
        mockUsers.add(e);
    }


    @Test
    public void getAllUsersTest1() {

        when(mockRepo.getAll()).thenReturn(mockUsers);

        List<UserDto> users = sut.getAllUsers();

        assertEquals(users.size(), 5);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void getAllUsersThrowsErrorTest(){
        List<Ticket> test = new ArrayList<>();
        when(mockRepo.getAll()).thenThrow();

        sut.getAllUsers();
    }

    @Test
    public void getByIdTest1() {

        when(mockRepo.findById(1)).thenReturn(mockUsers.get(1));

        UserDto user = sut.getById(1);

        assert(user.equals(new UserDto(mockUsers.get(1))));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void getByIdThrowsResourceNotFoundExceptionTest1(){
        List<Ticket> test = new ArrayList<>();
        when(mockRepo.findById(1)).thenReturn(null);

        sut.getById(1);
    }

    @Test(expected = BadRequestException.class)
    public void getByIdThrowsBadRequestExceptionTest2(){
        List<Ticket> test = new ArrayList<>();
        when(mockRepo.findById(1)).thenReturn(mockUsers.get(1));

        sut.getById(-1);
    }

    @Test
    public void getUserTicketsTest1() {

        Ticket t1 = new Ticket(1, 5.99, "arr", "dst", new Date(), new Date(), mockUsers.get(1));
        Ticket t2 = new Ticket(2, 5.99, "arr", "dst", new Date(), new Date(), mockUsers.get(1));
        Ticket t3 = new Ticket(3, 5.99, "arr", "dst", new Date(), new Date(), mockUsers.get(1));
        List<Ticket> mockTickets = new ArrayList<>();
        mockTickets.add(t1);
        mockTickets.add(t2);
        mockTickets.add(t3);
        User tempUser = new User(mockUsers.get(1)).setTickets(mockTickets);
        when(mockRepo.getUserTickets(1)).thenReturn(mockTickets);

        List<TicketDto> tickets = sut.getUserTickets(1);

        assertEquals(tickets.size(), 3);


    }

    @Test(expected = ResourceNotFoundException.class)
    public void getUserTicketsThrowsResourceNotFoundExceptionTest() {

        Ticket t1 = new Ticket(1, 5.99, "arr", "dst", new Date(), new Date(), mockUsers.get(1));
        Ticket t2 = new Ticket(2, 5.99, "arr", "dst", new Date(), new Date(), mockUsers.get(1));
        Ticket t3 = new Ticket(3, 5.99, "arr", "dst", new Date(), new Date(), mockUsers.get(1));
        List<Ticket> mockTickets = new ArrayList<>();
        mockTickets.add(t1);
        mockTickets.add(t2);
        mockTickets.add(t3);
        User tempUser = new User(mockUsers.get(1)).setTickets(mockTickets);
        when(mockRepo.getUserTickets(1)).thenThrow();

        List<TicketDto> tickets = sut.getUserTickets(1);

    }

    @Test(expected = BadRequestException.class)
    public void getUserTicketsThrowsBadRequestExceptionTest() {

        Ticket t1 = new Ticket(1, 5.99, "arr", "dst", new Date(), new Date(), mockUsers.get(1));
        Ticket t2 = new Ticket(2, 5.99, "arr", "dst", new Date(), new Date(), mockUsers.get(1));
        Ticket t3 = new Ticket(3, 5.99, "arr", "dst", new Date(), new Date(), mockUsers.get(1));
        List<Ticket> mockTickets = new ArrayList<>();
        mockTickets.add(t1);
        mockTickets.add(t2);
        mockTickets.add(t3);
        User tempUser = new User(mockUsers.get(1)).setTickets(mockTickets);
        when(mockRepo.getUserTickets(1)).thenReturn(mockTickets);

        List<TicketDto> tickets = sut.getUserTickets(-1);

    }

    @Test
    public void findUserByCredentialsTest1() {
        when(mockRepo.findUserByCredentials(new Credentials("aanderson", "password"))).
                thenReturn(mockUsers.get(1));

        Principal user = sut.findUserByCredentials(new Credentials("aanderson", "password"));


        assert(user.equals(new Principal(mockUsers.get(1))));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void findUserByCredentialsThrowsResourceNotFoundExceptionest() {
        when(mockRepo.findUserByCredentials(new Credentials("aanderson", "password"))).
                thenThrow();

        Principal user = sut.findUserByCredentials(new Credentials("aanderson", "password"));

    }

    @Test(expected = BadRequestException.class)
    public void findUserByCredentialsThrowsBadRequestExceptionTest1() {
        when(mockRepo.findUserByCredentials(new Credentials("aanderson", "password")))
                .thenReturn(mockUsers.get(1));

        Principal user = sut.findUserByCredentials(new Credentials("", "password"));

    }

    @Test(expected = BadRequestException.class)
    public void findUserByCredentialsThrowsBadRequestExceptionTest2() {
        when(mockRepo.findUserByCredentials(new Credentials("aanderson", "password"))).
                thenReturn(mockUsers.get(1));

        Principal user = sut.findUserByCredentials(new Credentials("aanderson", ""));

    }

    @Test
    public void saveNewUserTest1() {

        when(mockRepo.save(mockUsers.get(1))).thenReturn(mockUsers.get(1));

        UserDto user = sut.saveNewUser(mockUsers.get(1));

        assertEquals(user, new UserDto(mockUsers.get(1)));

    }

    @Test(expected = ResourcePersistenceException.class)
    public void saveNewUserThrowsResourcePersistenceExceptionTest() {

        when(mockRepo.save(mockUsers.get(1))).thenThrow(new ResourcePersistenceException());

        UserDto user = sut.saveNewUser(mockUsers.get(1));

    }

    @Test(expected = BadRequestException.class)
    public void saveNewUserThrowsBadRequestExceptionTest1() {
        when(mockRepo.checkUsername("")).thenReturn(false);
        when(mockRepo.checkEmail("")).thenReturn(false);
        User test = mockUsers.get(1).setUsername("");
        //when(mockRepo.save(test)).thenReturn(mockUsers.get(1));

        UserDto user = sut.saveNewUser(test);

    }

    @Test(expected = BadRequestException.class)
    public void saveNewUserThrowsBadRequestExceptionTest2() {
        User test = new User(mockUsers.get(1)).setUsername("");
        //when(mockRepo.save(test)).thenReturn(mockUsers.get(1));
        UserDto user = sut.saveNewUser(test);

    }

    @Test(expected = BadRequestException.class)
    public void saveNewUserThrowsBadRequestExceptionTest3() {

        User tempUser = new User(mockUsers.get(1)).setFirstName("");
        //when(mockRepo.save(mockUsers.get(1))).thenReturn(mockUsers.get(1));

        UserDto user = sut.saveNewUser(tempUser);

    }

    @Test
    public void updateUserTest1() {
        when(mockRepo.checkUsername("")).thenReturn(false);
        when(mockRepo.checkEmail("")).thenReturn(false);
        when(mockRepo.update(mockUsers.get(2).setUsername("test1"))).thenReturn(true);

        boolean user = sut.updateUser(mockUsers.get(2).setUsername("test1"));

        assertEquals(user, true);

    }

    @Test(expected = ResourcePersistenceException.class)
    public void updateUserThrowsResourcePersistenceExceptionTest() {
        when(mockRepo.update(mockUsers.get(1))).thenThrow(new ResourcePersistenceException());

        sut.updateUser(mockUsers.get(1));

    }

    @Test(expected = BadRequestException.class)
    public void updateUserThrowsBadRequestExceptionTest1() {

        User test = new User(mockUsers.get(1)).setEmail("");
        //when(mockRepo.update(mockUsers.get(1))).thenReturn(true);
        sut.updateUser(test);

    }

    @Test(expected = BadRequestException.class)
    public void updateUserThrowsBadRequestExceptionTest2() {

        User tempUser = new User(mockUsers.get(1)).setRole(new Role("Invalid"));
        when(mockRepo.update(tempUser)).thenReturn(true);

        boolean user = sut.updateUser(tempUser);

    }

    @Test(expected = BadRequestException.class)
    public void updateUserThrowsBadRequestExceptionTest3() {

        User tempUser = new User(mockUsers.get(1)).setUsername("");
        when(mockRepo.update(tempUser)).thenReturn(true);

        boolean user = sut.updateUser(tempUser);

    }

    @Test
    public void deleteUserByIdTest1() {

        when(mockRepo.deleteById(1)).thenReturn(true);

        boolean user = sut.deleteUserById(1);

        assertEquals(user, true);

    }

    @Test
    public void deleteUserByIdThrowsExceptionTest() {

        when(mockRepo.deleteById(1)).thenThrow();

        boolean user = sut.deleteUserById(1);

        assert(user);

    }

    @Test(expected = BadRequestException.class)
    public void deleteUserByIdThrowsBadRequestExceptionTest() {

        when(mockRepo.deleteById(1)).thenReturn(true);

        boolean user = sut.deleteUserById(-1);

        assert(false);
    }
}
