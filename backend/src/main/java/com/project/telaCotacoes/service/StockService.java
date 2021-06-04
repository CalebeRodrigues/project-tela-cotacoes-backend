package com.project.telaCotacoes.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.telaCotacoes.exceptions.BusinessException;
import com.project.telaCotacoes.mapper.StockMapper;
import com.project.telaCotacoes.model.Stock;
import com.project.telaCotacoes.model.dto.StockDTO;
import com.project.telaCotacoes.repository.StockRepository;
import com.project.telaCotacoes.util.MessageUtils;

@Service
public class StockService {

	@Autowired
	private StockRepository repository;

	@Autowired
	private StockMapper mapper;

	@Transactional
	public StockDTO save(StockDTO dto) {
		Optional<Stock> optional = repository.findByNameAndDate(dto.getName(), dto.getDate());

		if (optional.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXIST);
		}

		Stock stock = mapper.toEntity(dto);
		repository.save(stock);

		return mapper.toDTO(stock);
	}

	@Transactional
	public StockDTO update(@Valid StockDTO dto) {
		Optional<Stock> optional = 
				repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());

		if (optional.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXIST);
		}

		Stock stock = mapper.toEntity(dto);
		repository.save(stock);

		return mapper.toDTO(stock);
	}

}
