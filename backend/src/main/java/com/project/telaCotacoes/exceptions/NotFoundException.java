package com.project.telaCotacoes.exceptions;

import com.project.telaCotacoes.util.MessageUtils;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundException () {
		super(MessageUtils.NO_RECORDS_FOUND);
	}
	
}
