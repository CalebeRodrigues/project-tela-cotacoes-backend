package com.project.telaCotacoes.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.telaCotacoes.exceptions.BusinessException;
import com.project.telaCotacoes.exceptions.NotFoundException;
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

	@Transactional
	public StockDTO delete(Long id) {
		StockDTO dto = this.findById(id);
		repository.deleteById(dto.getId());
		return dto;
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findAll() {
		return mapper.toDTO(repository.findAll());
	}

	@Transactional(readOnly = true)
	public StockDTO findById(Long id) {
		return repository.findById(id).map(mapper::toDTO).orElseThrow(NotFoundException::new);
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findByToday() {
		return repository.findByToday(LocalDate.now()).map(mapper::toDTO).orElseThrow(NotFoundException::new);
	}

}
