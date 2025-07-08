import com.example.edutech.edutech.model.Inscripcion;
import com.example.edutech.edutech.service.InscripcionServiceImpl;

import org.junit.jupiter.api.*;

public class InscripcionServiceTest {

    @Test
    public void AgregarInscripcionTest() {
        Inscripcion esperado = new Inscripcion("Felipe", "Matematicas", "2025-07-08");
        InscripcionServiceImpl inscripcionService = new InscripcionServiceImpl();

        Inscripcion resultado = inscripcionService.saveInscripcion(esperado);
        Assertions.assertEquals(esperado.getEstudiante(), resultado.getEstudiante());
        Assertions.assertEquals(esperado.getCurso(), resultado.getCurso());
        Assertions.assertEquals(esperado.getFechaInscripcion(), resultado.getFechaInscripcion());
    }

    @Test
    public void EliminarInscripcionTest() {
        // Crear una inscripción para eliminar
        Inscripcion inscripcion = new Inscripcion("Felipe", "Matematicas", "2025-07-08");
        InscripcionServiceImpl inscripcionService = new InscripcionServiceImpl();
        
        // Guardar la inscripción
        inscripcionService.saveInscripcion(inscripcion);
        
        // Eliminar la inscripción por estudiante (no por ID)
        String resultado = inscripcionService.deleteInscripcion("Felipe");
        
        // Verificar que se eliminó correctamente
        Assertions.assertEquals("Inscripcion eliminada", resultado);
        
        // Verificar que ya no existe
        Inscripcion inscripcionEliminada = inscripcionService.getInscripcion("Felipe");
        Assertions.assertNull(inscripcionEliminada);
    }

    @Test
    public void BuscarInscripcionTest() {
        // Crear y guardar una inscripción
        Inscripcion esperado = new Inscripcion("Ana", "Fisica", "2025-07-08");
        InscripcionServiceImpl inscripcionService = new InscripcionServiceImpl();
        
        inscripcionService.saveInscripcion(esperado);
        
        // Buscar la inscripción por estudiante (no por ID)
        Inscripcion resultado = inscripcionService.getInscripcion("Ana");
        
        // Verificar que se encontró correctamente
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(esperado.getEstudiante(), resultado.getEstudiante());
        Assertions.assertEquals(esperado.getCurso(), resultado.getCurso());
        Assertions.assertEquals(esperado.getFechaInscripcion(), resultado.getFechaInscripcion());
    }
}