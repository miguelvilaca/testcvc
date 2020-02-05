package com.testcvc.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testcvc.pojo.Hotel;
import com.testcvc.pojo.Quarto;
import com.testcvc.webservices.HotelProducer;


@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelProducer clienteBroker;
	 
	public HotelServiceImpl() {
		super();
	}

	@Override
	public List<Hotel> consultaListaHoteis(Long idCidade) throws Exception{

		return clienteBroker.getListHotel(idCidade);
	}
	
	@Override
	public Hotel consultaHotelDetail(Long idHotel) throws Exception {

		return clienteBroker.getHotelDetail(idHotel);
	}

	@Override
	public List<Hotel> consultaViagemCalculada(Long idCidade, Long days, Integer adults, Integer childs) throws Exception {
		List<Hotel> list = null;
		List<Hotel> listHotel = new ArrayList<Hotel>();
		List<Quarto> listQuarto = new ArrayList<Quarto>();;

		list = consultaListaHoteis(idCidade);
		if(list != null) {
			for(Hotel pHotel: list) {
				Hotel hotel = new Hotel();
				hotel.setId(pHotel.getId());
				hotel.setCityName(pHotel.getCityName());
				for(Quarto pQuarto: pHotel.getRooms()) {
					Quarto quarto = new Quarto();
					BigDecimal taxa = calcViagem(days, adults, childs, pQuarto);
					listQuarto.add(pQuarto);
				}
				hotel.setRooms(listQuarto);
				listHotel.add(hotel);
			}
		}
			
		return listHotel;
	}

	
	public BigDecimal calcViagem(Long days, Integer adults, Integer childs, 
			Quarto quarto) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		BigDecimal totalPerDayAdults = quarto.getPrice().get("adult").multiply(new BigDecimal(days));
		BigDecimal totalPerDayChilds = quarto.getPrice().get("child").multiply(new BigDecimal(days));
		
		totalPrice = totalPerDayAdults.multiply(new BigDecimal(adults));
		totalPrice = totalPrice.add(totalPerDayChilds.multiply(new BigDecimal(childs)));
		totalPrice = totalPrice.divide(new BigDecimal("0.7"), 2, RoundingMode.HALF_UP);
		
		quarto.setTotalPrice(totalPrice);
		LinkedHashMap<String, BigDecimal> price = new LinkedHashMap<String, BigDecimal>();
		price.put("pricePerDayAdult", totalPerDayAdults);
		price.put("pricePerDayChild", totalPerDayChilds);
		quarto.setPriceDetail(price);	
		
		return totalPrice;
	}
}
