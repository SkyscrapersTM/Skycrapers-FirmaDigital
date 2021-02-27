package com.spring.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import com.spring.entity.Documento;
import com.spring.entity.Empresa;
import com.spring.entity.Role;
import com.spring.entity.Usuario;

public interface DocumentoService {
public List<Documento> listar();
public int save(Documento d);
public Page<Documento> listAllDocumentos(Pageable pageable);
//public List<Role> listaRol();
}
