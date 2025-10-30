/**
 * Clase de pruebas unitarias para GestionBiblioteca
 * CORREGIDO: Imports y anotaciones de JUnit 4
 * 
 * @author jacks
 */

// IMPORTS CORRECTOS PARA JUNIT 4

import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import sebas.lab.testing.GestionBiblioteca;

public class TestGestionBiblioteca {
    
    // Variable de instancia para la biblioteca
    private GestionBiblioteca biblioteca;
    
    public TestGestionBiblioteca() {
        // Constructor vacío
    }

    @Before  // Correcto: @Before en lugar de @BeforeAll
    public void setUp() {
        // Se ejecuta antes de cada test
        biblioteca = new GestionBiblioteca();
        System.out.println("Iniciando test...");
    }
    
    @After  // Correcto: ya estaba bien
    public void tearDown() {
        // Se ejecuta después de cada test
        biblioteca = null;
        System.out.println("Test finalizado.\n");
    }
    
    // ========== TESTS PARA calcularPrecioConDescuento ==========
    
    @Test
    public void testCalcularPrecioSinDescuento() {
        double resultado = biblioteca.calcularPrecioConDescuento(100.0, 0);
        assertEquals(100.0, resultado, 0.01);
    }
    
    @Test
    public void testCalcularPrecioConDescuento50() {
        // Implementado: test con 50% de descuento sobre 100
        double resultado = biblioteca.calcularPrecioConDescuento(100.0, 50);
        assertEquals(50.0, resultado, 0.01);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalcularPrecioPrecioNegativo() {
        biblioteca.calcularPrecioConDescuento(-50.0, 10);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalcularPrecioDescuentoInvalido() {
        // Implementado: test con descuento mayor a 100
        biblioteca.calcularPrecioConDescuento(100.0, 150);
    }
    
    @Test
    public void testCalcularPrecioDescuento100() {
        // Test adicional: descuento del 100%
        double resultado = biblioteca.calcularPrecioConDescuento(200.0, 100);
        assertEquals(0.0, resultado, 0.01);
    }
    
    // ========== TESTS PARA estaDisponible ==========
    
    @Test
    public void testLibroNoDisponible() {
        assertFalse(biblioteca.estaDisponible("El Quijote"));
    }
    
    @Test
    public void testLibroDisponibleDespuesDeAgregar() {
        biblioteca.agregarLibro("Cien Años de Soledad");
        // Implementado: verificar que el libro esté disponible
        assertTrue(biblioteca.estaDisponible("Cien Años de Soledad"));
    }
    
    @Test
    public void testLibroNullNoDisponible() {
        // Implementado: test con null
        assertFalse(biblioteca.estaDisponible(null));
    }
    
    @Test
    public void testLibroCadenaVaciaNoDisponible() {
        // Test adicional: cadena vacía
        assertFalse(biblioteca.estaDisponible(""));
        assertFalse(biblioteca.estaDisponible("   "));
    }
    
    // ========== TESTS PARA agregarLibro ==========
    
    @Test
    public void testAgregarLibroExitoso() {
        boolean resultado = biblioteca.agregarLibro("1984");
        assertTrue(resultado);
        assertEquals(1, biblioteca.getCantidadLibros());
    }
    
    @Test
    public void testAgregarLibroDuplicado() {
        // Implementado: agregar mismo libro dos veces
        biblioteca.agregarLibro("El Principito");
        boolean resultado = biblioteca.agregarLibro("El Principito");
        assertFalse(resultado);
        assertEquals(1, biblioteca.getCantidadLibros());
    }
    
    @Test
    public void testAgregarLibroNull() {
        // Implementado: intentar agregar null
        boolean resultado = biblioteca.agregarLibro(null);
        assertFalse(resultado);
        assertEquals(0, biblioteca.getCantidadLibros());
    }
    
    @Test
    public void testAgregarLibroCadenaVacia() {
        // Test adicional: cadena vacía
        boolean resultado = biblioteca.agregarLibro("");
        assertFalse(resultado);
        assertEquals(0, biblioteca.getCantidadLibros());
    }
    
    @Test
    public void testAgregarLibroConEspacios() {
        // Test adicional: verificar trim
        boolean resultado = biblioteca.agregarLibro("  El Principito  ");
        assertTrue(resultado);
        assertTrue(biblioteca.estaDisponible("El Principito"));
    }
    
    // ========== TESTS PARA obtenerCategoriaLector ==========
    
    @Test
    public void testCategoriaPrincipiante() {
        String categoria = biblioteca.obtenerCategoriaLector(0);
        assertEquals("Principiante", categoria);
    }
    
    @Test
    public void testCategoriaIntermedio() {
        // Implementado: test con 5 libros
        String categoria = biblioteca.obtenerCategoriaLector(5);
        assertEquals("Intermedio", categoria);
    }
    
    @Test
    public void testCategoriaAvanzado() {
        // Implementado: test con 25 libros
        String categoria = biblioteca.obtenerCategoriaLector(25);
        assertEquals("Avanzado", categoria);
    }
    
    @Test
    public void testCategoriaExperto() {
        // Implementado: test con 100 libros
        String categoria = biblioteca.obtenerCategoriaLector(100);
        assertEquals("Experto", categoria);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCategoriaConNumeroNegativo() {
        biblioteca.obtenerCategoriaLector(-5);
    }
    
    @Test
    public void testCategoriaValoresLimite() {
        // Test de valores límite
        assertEquals("Principiante", biblioteca.obtenerCategoriaLector(0));
        assertEquals("Intermedio", biblioteca.obtenerCategoriaLector(1));
        assertEquals("Intermedio", biblioteca.obtenerCategoriaLector(10));
        assertEquals("Avanzado", biblioteca.obtenerCategoriaLector(11));
        assertEquals("Avanzado", biblioteca.obtenerCategoriaLector(50));
        assertEquals("Experto", biblioteca.obtenerCategoriaLector(51));
    }
    
    // ========== TESTS PARA obtenerLibrosDisponibles ==========
    
    @Test
    public void testObtenerLibrosNuncaEsNull() {
        List<String> libros = biblioteca.obtenerLibrosDisponibles();
        assertNotNull(libros);
    }
    
    @Test
    public void testObtenerLibrosVacioInicial() {
        // Implementado: verificar que la lista está vacía inicialmente
        List<String> libros = biblioteca.obtenerLibrosDisponibles();
        assertEquals(0, libros.size());
        assertTrue(libros.isEmpty());
    }
    
    @Test
    public void testObtenerLibrosDespuesDeAgregar() {
        biblioteca.agregarLibro("El Principito");
        biblioteca.agregarLibro("Harry Potter");
        List<String> libros = biblioteca.obtenerLibrosDisponibles();
        // Implementado: verificar que contiene 2 libros
        assertEquals(2, libros.size());
        assertTrue(libros.contains("El Principito"));
        assertTrue(libros.contains("Harry Potter"));
    }
    
    @Test
    public void testObtenerLibrosRetornaCopia() {
        // Test adicional: verificar que retorna una copia
        biblioteca.agregarLibro("Libro1");
        List<String> libros = biblioteca.obtenerLibrosDisponibles();
        libros.add("LibroExterno");
        
        // La modificación externa no debe afectar la biblioteca
        assertEquals(1, biblioteca.getCantidadLibros());
        assertFalse(biblioteca.estaDisponible("LibroExterno"));
    }
    
    // ========== TEST DE INTEGRACIÓN ==========
    
    @Test
    public void testFlujoCompletoGestionBiblioteca() {
        // Escenario completo de uso
        assertTrue(biblioteca.agregarLibro("Don Quijote"));
        assertTrue(biblioteca.agregarLibro("La Odisea"));
        assertTrue(biblioteca.agregarLibro("Hamlet"));
        
        assertEquals(3, biblioteca.getCantidadLibros());
        assertTrue(biblioteca.estaDisponible("Don Quijote"));
        assertFalse(biblioteca.estaDisponible("Ilíada"));
        
        double precio = biblioteca.calcularPrecioConDescuento(100, 20);
        assertEquals(80.0, precio, 0.01);
        
        String categoria = biblioteca.obtenerCategoriaLector(15);
        assertEquals("Avanzado", categoria);
    }
}