package com.example.Java26_Team1_FinalProject.services;

import com.example.Java26_Team1_FinalProject.entities.CartaDiPagamento;
import com.example.Java26_Team1_FinalProject.entities.Cliente;
import com.example.Java26_Team1_FinalProject.repositories.CartaDiPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaDiPagamentoService {

    @Autowired
    private CartaDiPagamentoRepository cartaDiPagamentoRepository;

    public List<CartaDiPagamento> getAllCarte() {
        return cartaDiPagamentoRepository.findAll();
    }

    public Optional<CartaDiPagamento> findCartaById(Long id){
        return cartaDiPagamentoRepository.findById(id);
    }

    public CartaDiPagamento createCarta(CartaDiPagamento carta){
        return cartaDiPagamentoRepository.save(carta);
    }

    public Optional<CartaDiPagamento> updateCarta(Long id, CartaDiPagamento cartaDiPagamento){
        Optional<CartaDiPagamento> cartaDiPagamentoOptional = cartaDiPagamentoRepository.findById(id);
        if (cartaDiPagamentoOptional.isPresent()){
            CartaDiPagamento cartaTrovata = cartaDiPagamentoOptional.get();
            cartaTrovata.setNumeroCarta(cartaDiPagamento.getNumeroCarta());
            cartaTrovata.setDataDiScadenza(cartaDiPagamento.getDataDiScadenza());
            cartaTrovata.setCvv(cartaDiPagamento.getCvv());
            cartaTrovata.setCircuito(cartaDiPagamento.getCircuito());
            cartaTrovata.setCliente(cartaDiPagamento.getCliente());

            return Optional.of(cartaDiPagamentoRepository.save(cartaTrovata));
        }
        return Optional.empty();
    }

    public boolean deleteCarta (Long id){
        Optional<CartaDiPagamento> cartaDiPagamentoOptional = cartaDiPagamentoRepository.findById(id);
        if (cartaDiPagamentoOptional.isPresent()){
            cartaDiPagamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
