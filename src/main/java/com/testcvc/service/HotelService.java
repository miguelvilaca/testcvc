package com.testcvc.service;

import java.util.List;

import com.testcvc.pojo.Hotel;


public interface HotelService {

	public abstract List<Hotel> consultaListaHoteis(Long idCidade) throws Exception;
	
	public Hotel consultaHotelDetail(Long idHotel) throws Exception;
	
	public abstract List<Hotel> consultaViagemCalculada(Long idCidade, Long days, Integer adults, Integer childs) throws Exception;
}
