package com.kronos.musica;

import com.kronos.musica.principal.Principal;
import com.kronos.musica.repository.CancionRepository;
import com.kronos.musica.repository.CantanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicaApplication implements CommandLineRunner {

	@Autowired
	private CantanteRepository repository;

	@Autowired
	private CancionRepository repoCancion;

	public static void main(String[] args) {
		SpringApplication.run(MusicaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, repoCancion);
		principal.mostrarMenu();
	}
}
