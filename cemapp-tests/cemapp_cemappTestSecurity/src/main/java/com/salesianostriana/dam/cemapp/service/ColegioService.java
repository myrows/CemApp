package com.salesianostriana.dam.cemapp.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.cemapp.models.Colegio;
import com.salesianostriana.dam.cemapp.repository.ColegioRepository;
import com.salesianostriana.dam.cemapp.service.base.BaseService;

@Service
public class ColegioService extends BaseService<Colegio, Long, ColegioRepository> {

}
