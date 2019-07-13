package com.bext.swaggertest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class RestTestController {

	@RequestMapping(method = RequestMethod.GET, value = "/api/simpletext")
	public String getSimpleText() {
		return " prueba para swagger simple texto, jalbertomr@blogspot.com";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/jsonsimulado", produces = "application/json")
	public String getJsonSimulado() {
		return "{ \"simulado\" : \"json simulado\"," +
				" \"mensaje\": \"prueba para swagger jalbertomr@blogspot.com\" }";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/jsonDTO", produces = "application/json")
	public String getJsonResponseDTO() {
		String respuesta = "valor inicializado";

		ObjectMapper objectMapper = new ObjectMapper();
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMensaje( " prueba para swagger simple texto, jalbertomr@blogspot.com");
		responseDTO.setTime(System.currentTimeMillis());
		responseDTO.setIp( getIp());

		try {
			respuesta = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString( responseDTO);
		} catch (JsonProcessingException e) {
			respuesta = "No pudo convertir DTO a JSON" + e.getMessage();
		}
		return respuesta;
	}

	private String getIp() {
		String hostname = null;
		try {
			hostname = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			hostname = "desconocido";
		}

		return hostname;
	}
}
