package com.example.edutech.edutech.service;

import com.example.edutech.edutech.repository.InscripcionRepository;
import com.example.edutech.edutech.model.Inscripcion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InscripcionServiceTest {

    private InscripcionRepository inscripcionRepository;
    private InscripcionService inscripcionService;

    @BeforeEach
    public void setUp() {
        inscripcionRepository = Mockito.mock(InscripcionRepository.class);
        inscripcionService = new InscripcionService(inscripcionRepository);
    }

    @Test
    public void testGetAllInscripciones() {
        Inscripcion ins1 = new Inscripcion();
        ins1.setId(1L);
        ins1.setEstudiante("Juan");
        ins1.setCurso("Matemáticas");
        ins1.setFechaInscripcion("2025-06-15");

        Inscripcion ins2 = new Inscripcion();
        ins2.setId(2L);
        ins2.setEstudiante("Ana");
        ins2.setCurso("Historia");
        ins2.setFechaInscripcion("2025-06-16");

        List<Inscripcion> inscripciones = Arrays.asList(ins1, ins2);

        when(inscripcionRepository.findAll()).thenReturn(inscripciones);

        List<Inscripcion> resultado = inscripcionService.getAllInscripciones();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Juan", resultado.get(0).getEstudiante());
        assertEquals("Historia", resultado.get(1).getCurso());

        verify(inscripcionRepository, times(1)).findAll();
    }

    @Test
    public void testGetInscripcionById_Found() {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(1L);
        inscripcion.setEstudiante("Pedro");
        inscripcion.setCurso("Física");
        inscripcion.setFechaInscripcion("2025-06-14");

        when(inscripcionRepository.findById(1L)).thenReturn(Optional.of(inscripcion));

        Optional<Inscripcion> resultado = inscripcionService.getInscripcionById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Pedro", resultado.get().getEstudiante());

        verify(inscripcionRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateInscripcion() {
        Inscripcion nueva = new Inscripcion();
        nueva.setEstudiante("Luis");
        nueva.setCurso("Química");
        nueva.setFechaInscripcion("2025-06-17");

        when(inscripcionRepository.save(nueva)).thenReturn(nueva);

        Inscripcion resultado = inscripcionService.createInscripcion(nueva);

        assertNotNull(resultado);
        assertEquals("Luis", resultado.getEstudiante());

        verify(inscripcionRepository, times(1)).save(nueva);
    }

    @Test
    public void testDeleteInscripcion() {
        Long id = 1L;
        doNothing().when(inscripcionRepository).deleteById(id);

        inscripcionService.deleteInscripcion(id);

        verify(inscripcionRepository, times(1)).deleteById(id);
    }
}