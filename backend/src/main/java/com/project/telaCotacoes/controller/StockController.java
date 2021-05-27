package com.project.telaCotacoes.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.telaCotacoes.model.dto.StockDTO;

@RestController
@RequestMapping(value = "/stock")
public class StockController {

	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<StockDTO> save (@Valid @RequestBody StockDTO dto) {
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<StockDTO> update (@Valid @RequestBody StockDTO dto) {
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDTO>> findAll () {
		List<StockDTO> list = new ArrayList<>();
		
		StockDTO dto = new StockDTO();
		
		dto.setId(1l);
		dto.setName("Magazine Luiza");
		dto.setPrice(100D);
		dto.setVariation(10D);
		dto.setDate(LocalDate.now());
		
		list.add(dto);
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> findById (@PathVariable Long id) {
		List<StockDTO> list = new ArrayList<>();
		
		StockDTO dto = new StockDTO();
		
		dto.setId(1l);
		dto.setName("Magazine Luiza");
		dto.setPrice(100D);
		dto.setVariation(10D);
		dto.setDate(LocalDate.now());

		StockDTO dto2 = new StockDTO();
		
		dto2.setId(2l);
		dto2.setName("Ponto Frio");
		dto2.setPrice(200D);
		dto2.setVariation(20D);
		dto2.setDate(LocalDate.now());
		
		list.add(dto);
		list.add(dto2);
		
		StockDTO dtoSelect = list.stream().filter(x -> x.getId().compareTo(id) == 0).findFirst().get();
		
		return ResponseEntity.ok(dtoSelect);
	}
	
}


