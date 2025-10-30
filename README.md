# Laboratorio 06 - Pruebas Unitarias + Mockito

## Suite de Pruebas Unitarias

### 1. Tests para `calcularPrecioConDescuento()`

Seleccionar 3 escenarios de los siguientes e implementarlos.

- Precio sin descuento (0%)
- Precio con 50% de descuento
- Precio con 100% de descuento
- Excepción con precio negativo
- Excepción con descuento mayor a 100

### 2. Tests para `estaDisponible()`


Seleccionar 2 escenarios de los siguientes e implementarlos.

- Libro no disponible (no agregado)
- Libro disponible después de agregarlo
- Libro null retorna false
- Cadena vacía retorna false

### 3. Tests para `agregarLibro()`

Seleccionar 2 escenarios de los siguientes e implementarlos.

- Agregar libro exitosamente
- Agregar libro duplicado retorna false
- Agregar null retorna false
- Verificar trim de espacios

### 4. Tests para `obtenerCategoriaLector()`


Seleccionar 3 escenarios de los siguientes e implementarlos.

- 0 libros → `"Principiante"`
- 5 libros → `"Intermedio"`
- 25 libros → `"Avanzado"`
- 100 libros → `"Experto"`
- Valores límite (1, 10, 11, 50, 51)
- Excepción con número negativo

### 5. Tests para `obtenerLibrosDisponibles()`


Seleccionar 2 escenarios de los siguientes e implementarlos.

- Nunca retorna null
- Lista vacía inicialmente
- Contiene libros después de agregarlos
- Retorna una copia (modificaciones externas no afectan)

---

## Tests con Mockito - UserService

### 6. Tests para `getUserName()` usando Mocks


**Configuración:**
- Crear mock de `UserRepository` con `Mockito.mock()`
- Inyectar el mock en `UserService`
- Usar `when().thenReturn()` para configurar respuestas

**Test 1: Usuario Existe (Happy Path)**
- Mockear `repository.findById("123")` para retornar un `User(id="123", name="Juan")`
- Llamar `userService.getUserName("123")`
- Verificar que retorna `"Juan"`
- Verificar con `verify()` que `findById()` fue llamado 1 vez

**Test 2: Usuario No Existe (Error)**
- Mockear `repository.findById("999")` para retornar `null`
- Llamar `userService.getUserName("999")`
- Verificar que lanza `IllegalArgumentException`
- Verificar que el mensaje es `"User not found"`


