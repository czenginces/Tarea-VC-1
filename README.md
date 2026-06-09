# Sistema de Gestión de Usuarios

## 📌 Descripción
Sistema desarrollado en Java aplicando Programación Orientada a Objetos (POO) para la gestión de usuarios con distintos perfiles (Administrador y Tester).

Permite registrar usuarios, gestionar perfiles, controlar accesos y aplicar validaciones básicas.

---

## 🧠 Tecnologías
- Java
- IntelliJ IDEA
- Git

---

## 🧱 Modelo del sistema

### Clase base
- Usuario
  - nombre
  - apellido
  - email
  - contraseña
  - país de nacimiento

### Perfiles
- Administrador
- Tester (Junior, Líder, Senior)

---

## ⚙️ Funcionalidades y datos requeridos

### 🔓 Sin loguearse

#### Inicio de sesión administrador
**Datos:**
- Email  
- Contraseña  

**Descripción:**  
Permite al usuario administrador ingresar al sistema utilizando sus credenciales.

---

#### Crear cuenta administrador
**Datos:**
- Nombre  
- Apellido  
- Email  
- Contraseña  
- Repetir contraseña  
- País de nacimiento  

**Descripción:**  
Permite registrar un nuevo usuario administrador ingresando sus datos personales.

---

#### Olvidé mi contraseña
**Datos:**
- Email  
- Nueva contraseña  
- Repetir contraseña  

**Descripción:**  
Permite restablecer la contraseña del usuario.

---

### 🔐 Logueado

#### Ver datos del perfil
**Datos:**
- Nombre  
- Apellido  
- Email  
- País de nacimiento  
- Perfil (no editable)  

**Descripción:**  
Permite visualizar la información del usuario logueado.

---

#### Editar datos del perfil
**Datos:**
- Nombre  
- Apellido  
- Email  
- País de nacimiento  
- Perfil (no editable)  

**Descripción:**  
Permite modificar los datos personales del usuario.

---

#### Ver usuarios
**Datos:**
- Información de usuarios registrados  

**Descripción:**  
Permite visualizar la lista completa de usuarios.

---

#### Eliminar usuarios (no admin)
**Datos:**
- Acción eliminar  

**Descripción:**  
Permite eliminar usuarios que no tengan perfil administrador.

---

#### Crear usuario Tester Junior
**Datos:**
- Nombre  
- Apellido  
- Email  
- País de nacimiento  
- Contraseña por defecto  

**Descripción:**  
Permite crear un usuario con rol tester junior.

---

#### Crear usuario Tester Líder
**Datos:**
- Nombre  
- Apellido  
- Email  
- País de nacimiento  
- Contraseña por defecto  

**Descripción:**  
Permite crear un usuario con rol tester líder.

---

#### Crear usuario Tester Senior
**Datos:**
- Nombre  
- Apellido  
- Email  
- País de nacimiento  
- Contraseña por defecto  

**Descripción:**  
Permite crear un usuario con rol tester senior.

---

#### Cerrar sesión
**Datos:**
- Confirmar (Sí)  
- Cancelar  

**Descripción:**  
Permite salir del sistema con confirmación.

---

## 🚀 Ejecución

1. Clonar repositorio:
   git clone https://github.com/czenginces/PrimerPrograma.git
2. Abrir en IntelliJ IDEA  
3. Ejecutar la clase principal  

##  Diagrama

# Diagrama UML

```text
                    Usuario
    ----------------------------------
    - nombre : String
    - email : String
    - contrasena : String
    - apellido : String
    - paisNacimiento : String
    ----------------------------------
    + getNombre()
    + getEmail()
    + getContrasena()
    + getApellido()
    + getPaisNacimiento()
    + setNombre()
    + setEmail()
    + setContrasena()
    + setApellido()
    + setPaisNacimiento()
    + getTipoUsuario()
              ▲
              │
      ┌───────┴────────┐
      │                │
   +------+       +--------+
   |Admin |       | Tester |
   +------+       +--------+


+-------------------+
| SistemaUsuarios   |
+-------------------+
| - usuarios[]      |
| - cantidadUsuarios|
+-------------------+
| + SistemaUsuarios |
+-------------------+
          ◇
          │ contiene
          ▼
      Usuario


+--------+        usa        +---------+
|  Main  | ----------------> | Usuario |
+--------+                   +---------+
```
## Relaciones

- `Admin` hereda de `Usuario`.
- `Tester` hereda de `Usuario`.
- `SistemaUsuarios` contiene una colección de objetos `Usuario`.
- `Main` utiliza objetos de tipo `Usuario` para gestionar el administrador registrado y el proceso de login.
## 📌 Autor
Cynthia Zengin
