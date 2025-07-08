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
        // Aquí puedes implementar el test para eliminar una inscripción
    }

    @Test
    public void BuscarInscripcionTest() {
        // Aquí puedes implementar el test para buscar una inscripción
    }
}