import com.example.edutech.edutech.controller.CursoController;
import com.example.edutech.edutech.model.Curso;
import com.example.edutech.edutech.service.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CursoControllerTest {

    @InjectMocks
    private CursoController cursoController;

    @Mock
    private CursoService cursoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarCursos() {
        // Arrange
        Curso curso1 = new Curso(1, "Java Básico", "Intro", "Programación", "2025-07-01");
        Curso curso2 = new Curso(2, "Spring Boot", "Framework", "Backend", "2025-07-02");

        when(cursoService.getCursos()).thenReturn(List.of(curso1, curso2));

        // Act
        CollectionModel<EntityModel<Curso>> resultado = cursoController.listarCursos();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.getContent().size());
        verify(cursoService, times(1)).getCursos();
    }

    @Test
    public void testBuscarCursoExistente() {
        // Arrange
        Curso curso = new Curso(1, "Java Básico", "Intro", "Programación", "2025-07-01");
        when(cursoService.getCurso(1)).thenReturn(curso);

        // Act
        EntityModel<Curso> resultado = cursoController.buscarCurso(1);

        // Assert
        assertNotNull(resultado);
        assertEquals("Java Básico", resultado.getContent().getNombrecurso());
        verify(cursoService, times(1)).getCurso(1);
    }

    @Test
    public void testBuscarCursoInexistente() {
        // Arrange
        when(cursoService.getCurso(99)).thenReturn(null);

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            cursoController.buscarCurso(99);
        });

        assertEquals("Curso no encontrado con ID: 99", ex.getMessage());
    }
}