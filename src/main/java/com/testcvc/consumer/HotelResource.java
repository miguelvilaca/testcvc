package com.testcvc.consumer;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.testcvc.format.Format;
import com.testcvc.pojo.Hotel;
import com.testcvc.pojo.Response;
import com.testcvc.service.HotelService;

@RequestMapping("/v1")
@RestController
public class HotelResource {

	@Autowired
	private HotelService hotelService;
	
	@RequestMapping(value = "/hotels/get/{cityCode}/{checkin}/{checkout}/{adults}/{childs}", method = RequestMethod.GET)
	public Response loadHotels(@PathVariable(value = "cityCode") Long cityCode,
			@PathVariable(value = "checkin") String checkin,
				@PathVariable(value = "checkout") String checkout,
					@PathVariable(value = "adults") Integer adults,
						@PathVariable(value = "childs") Integer childs,
			HttpServletResponse response) {
		Response resp = new Response();
		resp.setSuccess(true);

		try{
			Long days = Format.getDayBetweenDates(checkin, checkout);
			
			List<Hotel> listHotel = this.hotelService.consultaViagemCalculada(cityCode, days, adults, childs);
			resp.setData(listHotel);

		} catch (Exception e) {
			resp.setSuccess(false);
			resp.setError(e.getMessage());
		}

		return resp;
	}
	
	@RequestMapping(value = "/hotels/get/detail/{idHotel}", method = RequestMethod.GET)
	public Response loadHotelsDetail(@PathVariable(value = "idHotel") Long idHotel, HttpServletResponse response) {
		Response resp = new Response();
		resp.setSuccess(true);
		
		try{
			Hotel hotel = this.hotelService.consultaHotelDetail(idHotel);
			resp.setData(hotel);

		} catch (Exception e) {
			resp.setSuccess(false);
			resp.setError(e.getMessage());
		}

		return resp;
	}
}
