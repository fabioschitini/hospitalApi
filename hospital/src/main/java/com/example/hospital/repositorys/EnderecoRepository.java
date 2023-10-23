package com.example.hospital.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.hospital.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco,Long>  {

}
 