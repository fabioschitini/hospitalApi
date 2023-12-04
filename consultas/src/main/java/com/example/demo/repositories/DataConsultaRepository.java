package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.model.DataConsulta;




@Service
public interface DataConsultaRepository extends JpaRepository<DataConsulta,Long>   {

}
