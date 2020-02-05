package com.testcvc.webservices;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.testcvc.pojo.Hotel;

@Service
public class HotelProducer {
	
	public HotelProducer() {
		super();
	}

	private static final String URL_GET_HOTELS = 
		    "https://cvcbackendhotel.herokuapp.com/hotels/avail/";
	private static final String URL_GET_HOTEL_DETAIL = "https://cvcbackendhotel.herokuapp.com/hotels/";
	
	private Client client = ClientBuilder.newClient();
	
	public List<Hotel> getListHotel(Long idCidade) throws Exception {
		
		List<Hotel> listHotel = client.target(URL_GET_HOTELS)
									.path(String.valueOf(idCidade))
										.request(MediaType.APPLICATION_JSON)
												.get(new GenericType<List<Hotel>> () {});
		
		return listHotel;
	}
	
	public Hotel getHotelDetail(Long idHotel) throws Exception {
		List<Hotel> hotelDetail = client.target(URL_GET_HOTEL_DETAIL)
				.path(String.valueOf(idHotel))
					.request(MediaType.APPLICATION_JSON)
							.get(new GenericType<List<Hotel>> () {});

			return hotelDetail.get(0);
	}
}