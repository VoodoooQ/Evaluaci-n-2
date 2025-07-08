import com.example.edutech.edutech.model.Curso;
import com.example.edutech.edutech.service.CursoServiceImpl;
import org.junit.jupiter.api.*;
import java.util.List;

public class CursoServiceTest {

    @Test
    public void agregarCursoTest() {
        Curso esperado = new Curso(1, "Matemáticas", "Curso de matemáticas", "Ciencias", "2025-07-08");
        CursoServiceImpl cursoService = new CursoServiceImpl();
        Curso resultado = cursoService.saveCurso(esperado);
        Assertions.assertEquals(esperado.getNombrecurso(), resultado.getNombrecurso());
        Assertions.assertEquals(esperado.getDescripcion(), resultado.getDescripcion());
        Assertions.assertEquals(esperado.getCategoria(), resultado.getCategoria());
        Assertions.assertEquals(esperado.getFechaCreacion(), resultado.getFechaCreacion());
    }

    @Test
    public void buscarCursoTest() {
        Curso esperado = new Curso(1, "Matemáticas", "Curso de matemáticas", "Ciencias", "2025-07-08");
        CursoServiceImpl cursoService = new CursoServiceImpl();
        cursoService.saveCurso(esperado);
        Curso resultado = cursoService.getCurso(0);
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(esperado.getNombrecurso(), resultado.getNombrecurso());
    }

    @Test
    public void actualizarCursoTest() {
        Curso original = new Curso(1, "Matemáticas", "Curso de matemáticas", "Ciencias", "2025-07-08");
        Curso actualizado = new Curso(1, "Física", "Curso de física", "Ciencias", "2025-07-09");
        CursoServiceImpl cursoService = new CursoServiceImpl();
        cursoService.saveCurso(original);
        Curso resultado = cursoService.updateCurso(0, actualizado);
        Assertions.assertEquals(actualizado.getNombrecurso(), resultado.getNombrecurso());
        Assertions.assertEquals(actualizado.getDescripcion(), resultado.getDescripcion());
        Assertions.assertEquals(actualizado.getFechaCreacion(), resultado.getFechaCreacion());
    }

    @Test
    public void eliminarCursoTest() {
        Curso curso = new Curso(1, "Matemáticas", "Curso de matemáticas", "Ciencias", "2025-07-08");
        CursoServiceImpl cursoService = new CursoServiceImpl();
        cursoService.saveCurso(curso);
        String mensaje = cursoService.deleteCurso(0);
        Assertions.assertEquals("Curso eliminado", mensaje);
        Assertions.assertNull(cursoService.getCurso(0));
    }

    @Test
    public void listarCursosTest() {
        CursoServiceImpl cursoService = new CursoServiceImpl();
        cursoService.saveCurso(new Curso(1, "Matemáticas", "Curso de matemáticas", "Ciencias", "2025-07-08"));
        cursoService.saveCurso(new Curso(2, "Física", "Curso de física", "Ciencias", "2025-07-09"));
        List<Curso> cursos = cursoService.getCursos();
        Assertions.assertEquals(2, cursos.size());
    }
}
