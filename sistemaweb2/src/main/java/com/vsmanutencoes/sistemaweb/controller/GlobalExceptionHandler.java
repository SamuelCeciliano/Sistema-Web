package com.vsmanutencoes.sistemaweb.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DataIntegrityViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex, RedirectAttributes redirectAttributes) {
        String messageError = "Não é possível excluir este registro, pois ele possui vínculos em outros registros.";
        redirectAttributes.addFlashAttribute("errorMessage", messageError);
        return "redirect:/materiais"; // redireciona para a página de listagem
    }
}


