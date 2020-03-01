package br.org.certi.controllers;

import br.org.certi.dto.Numero;
import br.org.certi.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@Validated
public class NumeroExtensoController {

	@GetMapping("/{number}")
	public Numero converter(
			@PathVariable(value = "number")
			@Min(value=-99999, message = "O número não pode ser menor que 99.999 negativo.")
			@Max(value=99999, message = "O número não pode ser maior que 99.999.")
					Integer number) {
		return Numero.NumeroBuilder
				.aNumero()
				.withNumero(number)
				.build();
	}

	@GetMapping("/")
	public ResponseEntity path() {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(
						Error.ErrorBuilder
								.anError()
								.withMessage("Usar host:port/{Número a ser convertido}")
								.build()
				);
	}
}
