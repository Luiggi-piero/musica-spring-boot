package com.kronos.musica.principal;

import com.kronos.musica.model.Cancion;
import com.kronos.musica.model.Cantante;
import com.kronos.musica.repository.CancionRepository;
import com.kronos.musica.repository.CantanteRepository;

import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private CantanteRepository repositorio;
    private CancionRepository repositorioCancion;

    public Principal(CantanteRepository repository, CancionRepository repoCancion) {
        this.repositorio = repository;
        this.repositorioCancion = repoCancion;
    }

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0){
            var menu = """
                    ****** MENÚ ******
                    1 - Crear cantante 
                    2 - Crear canción
                    3 - Buscar canciones por cantante
                                  
                    0 - Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    crearCantante();
                    break;
                case 2:
                    crearCancion();
                    break;
                case 3:
                    buscarCancionesPorCantante();
                    break;

                case 0:
                    System.out.println("Programa terminado");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void crearCantante(){
        System.out.println("Nombre del cantante: ");
        var nombre = teclado.nextLine();

        Cantante cantante = new Cantante(nombre);
        repositorio.save(cantante);
        System.out.println("Cantante creado!");
    }

    private void crearCancion(){
        System.out.println("Nombre de la canción: ");
        var nombre = teclado.nextLine();

        System.out.println("Duración: ");
        var duracion = teclado.nextDouble();

        System.out.println("Fecha de lanzamiento yyy-mm-dd: ");
        var fechaLanzamiento = teclado.next();

        System.out.println("Id del cantante: ");
        var idCantante = teclado.nextLong();
        Optional<Cantante> cantanteBuscado = repositorio.findById(idCantante);

        if(cantanteBuscado.isPresent()){
            //System.out.println("cantante encontrado " + cantanteBuscado.get().getNombre());
            Cancion cancion = new Cancion(nombre, duracion, fechaLanzamiento, cantanteBuscado.get());
            repositorioCancion.save(cancion);
            System.out.println("Canción creada!");
        }else {
            System.out.println("cantante No encontrado");
        }
    }

    private void buscarCancionesPorCantante(){
        System.out.println("Nombre del cantante: ");
        var nombre = teclado.nextLine();

        Optional<Cantante> cantanteBuscado = repositorio.buscarPorNombre(nombre);

        if(cantanteBuscado.isPresent()){
            var canciones = cantanteBuscado.get().getCanciones();
            System.out.println("Canciones encontradas de " + cantanteBuscado.get().getNombre());
            canciones.forEach(System.out::println);
        }else {
            System.out.println("Cantante no encontrado");
        }
    }
}
