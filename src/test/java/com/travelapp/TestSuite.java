package com.travelapp;

import com.travelapp.services.TicketServiceTest;
import com.travelapp.services.UserServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TicketServiceTest.class,
        UserServiceTest.class
})

public class TestSuite {}
