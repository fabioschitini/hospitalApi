package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.model.Consulta;


@Service
public interface ConsultaRepository extends JpaRepository<Consulta,Long>  {  

}
 