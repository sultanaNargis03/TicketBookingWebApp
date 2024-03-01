package com.telusko.service;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.telusko.request.Passenger;
import com.telusko.response.Ticket;

@Service
public class BookingTicketServiceImpl implements IBookingTicketService {

	private static final String Book_URL = "http://localhost:8484/TicketBookingApp/api/ticket-booking/getTicketNumber";
	private static final String GET_URL = "http://localhost:8484/TicketBookingApp/api/ticket-booking/getTicket/{ticketNumber}";
	

	@Override
	public Ticket bookTicket(Passenger passenger) 
	{
		System.out.println(passenger+" in Integration logic method");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Ticket> response=restTemplate.postForEntity(Book_URL, passenger, Ticket.class);
		
		return response.getBody();
	}

	@Override
	public Ticket fetchTicketInfo(Integer ticketNumber) 
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Ticket> response=restTemplate.getForEntity(GET_URL, Ticket.class,ticketNumber);
		Ticket ticket=response.getBody();
		return ticket;
	}

}
