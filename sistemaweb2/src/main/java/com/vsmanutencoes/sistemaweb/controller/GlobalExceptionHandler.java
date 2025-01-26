package com.vsmanutencoes.sistemaweb.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(
            DataIntegrityViolationException ex,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) {
        String messageError = "Não é possível excluir este registro, pois ele possui vínculos em outros registros.";
        redirectAttributes.addFlashAttribute("errorMessage", messageError);

        // Recupera o referer para identificar a origem da solicitação
        String referer = request.getHeader("Referer");

        // Redireciona com base na origem
        if (referer != null) {
            if (referer.contains("/materiais")) {
                return "redirect:/materiais";
            } else if (referer.contains("/servicos")) {
                return "redirect:/servicos";
            } else if (referer.contains("/equipamentos")) {
                return "redirect:/equipamentos";
            }
        }

        // Redireciona para uma página padrão se a origem não puder ser identificada
        return "redirect:/";
    }
}


