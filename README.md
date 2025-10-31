# 🏥 Sistema de Gestión de Citas Médicas

## 📋 Descripción del Proyecto

Sistema web desarrollado en **Spring Boot** para la gestión integral de citas médicas, diseñado para clínicas y centros de salud. Permite la gestión de usuarios, pacientes, médicos, citas y diagnósticos con diferentes niveles de acceso según el rol del usuario.

---

## 🚀 Tecnologías Utilizadas

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| **Java** | 17+ | Lenguaje de programación principal |
| **Spring Boot** | 3.x | Framework principal |
| **Spring Data JPA** | - | Persistencia de datos |
| **Thymeleaf** | - | Motor de plantillas |
| **SQL Server** | - | Base de datos |
| **Bootstrap** | 5.3.0 | Framework CSS |
| **HTML5/CSS3** | - | Frontend |

---

## 📁 Estructura del Proyecto

```
GestionCitas/
├── src/main/java/cibertec/edu/GestionCitas/
│   ├── Controller/          # Controladores REST/MVC
│   │   ├── AdminController.java
│   │   ├── AuthController.java
│   │   ├── HomeController.java
│   │   ├── MedicoController.java
│   │   └── PacienteController.java
│   ├── Entity/              # Entidades JPA
│   │   ├── Cita.java
│   │   ├── Diagnostico.java
│   │   ├── Medico.java
│   │   ├── Paciente.java
│   │   ├── Usuario.java
│   │   └── DTO/
│   │       ├── MedicoUsuarioDTO.java
│   │       └── PacienteUsuarioDTO.java
│   ├── Repository/          # Repositorios JPA
│   │   ├── CitaRepository.java
│   │   ├── DiagnosticoRepository.java
│   │   ├── MedicoRepository.java
│   │   ├── PacienteRepository.java
│   │   └── UsuarioRepository.java
│   ├── Service/             # Lógica de negocios
│   │   ├── CitaService.java
│   │   ├── DiagnosticoService.java
│   │   ├── MedicoService.java
│   │   ├── PacienteService.java
│   │   └── UsuarioService.java
│   └── GestionCitasApplication.java
├── src/main/resources/
│   ├── application.properties
│   ├── static/
│   │   ├── css/styles.css
│   │   └── js/funciones.js
│   └── templates/
│       ├── Admin/
│       ├── Auth/
│       ├── Medico/
│       └── Paciente/
└── pom.xml
```

---

## 🔧 Configuración e Instalación

### Prerrequisitos
- Java 17 o superior
- Apache Maven 3.6+
- SQL Server

### Variables de Entorno
Configurar en `src/main/resources/application.properties`:

```properties
# SQL Server Configuration
spring.datasource.url=jdbc:sqlserver://tu-servidor:1433;database=GestionCitas;user=tu-usuario;password=tu-password;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect

# Server Configuration
server.port=8080
```

### Instalación
```bash
# Clonar el repositorio
git clone [URL_DEL_REPOSITORIO]

# Entrar al directorio
cd GestionCitas

# Ejecutar la aplicación
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

---

## 👥 Roles y Permisos del Sistema

### 🔧 Administrador (Admin)
**Acceso:** Panel completo de administración
**Funcionalidades:**
- Gestión de usuarios del sistema
- Registro y edición de médicos
- Gestión de pacientes
- Supervisión de todo el sistema

### 👨‍⚕️ Médico
**Acceso:** Panel médico específico
**Funcionalidades:**
- Visualización de citas asignadas
- Edición del estado de citas
- Registro de diagnósticos
- Atención a pacientes
- Visualización de perfil

### 🧑‍💼 Paciente
**Acceso:** Panel de paciente
**Funcionalidades:**
- Reserva de citas médicas
- Visualización de citas propias
- Consulta de diagnósticos
- Edición de perfil personal

---

## 📖 Manual de Usuario

### 🚪 Acceso al Sistema

#### Inicio de Sesión
1. Ingrese a `http://localhost:8080`
2. Utilice las credenciales asignadas:
   - **Usuario:** `admin`
   - **Contraseña:** `admin123`
3. El sistema lo redirigirá según su rol

#### Registro de Pacientes
1. Desde la página de acceso, haga clic en "¿Eres nuevo? Regístrate como paciente"
2. Complete el formulario con:
   - Información personal
   - Datos de contacto
   - Credenciales de acceso
3. Presione "Registrar"

---

## 📋 Guía de Funcionalidades por Rol

### 🔧 PANEL ADMINISTRADOR │ Panel Admin

#### 👤 Gestión de Usuarios
**Acceso:** Menú → Usuarios
- **Ver usuarios:** Lista completa de usuarios registrados
- **Crear usuario:** Formulario para nuevos usuarios admin
- **Editar usuario:** Modificar información existente
- **Validaciones:** Email único, username único

#### 🩺 Gestión de Médicos
**Acceso:** Menú → Médicos
- **Crear médico:** Registrar nuevo médico con datos completos
- **Listar médicos:** Ver todos los médicos con especialidades
- **Editar médico:** Modificar información profesional
- **Validaciones:** Email único, especialidad requerida

#### 👥 Gestión de Pacientes
**Acceso:** Menú → Pacientes
- **Listar pacientes:** Ver todos los pacientes registrados
- **Editar paciente:** Modificar datos personales
- **Validaciones:** DNI único, email único, username único

---

### 👨‍⚕️ PANEL MÉDICO

#### 📋 Mis Citas
**Acceso:** Menú → Mis Citas
- **Ver agenda:** Citas asignadas con detalles completos
- **Estado de citas:**
  - 🟡 **Pendiente:** Cita sin confirmar
  - ✅ **Confirmada:** Cita autorizada
  - ❌ **Cancelada:** Cita anulada
- **Información mostrada:**
  - Fecha y hora
  - Datos del paciente
  - Observaciones especiales

#### ✏️ Editar Citas
**Acceso:** Desde "Mis Citas" → Botón "Editar"
- **Modificar estados:** Cambiar entre Pendiente/Confirmada/Cancelada
- **Agregar observaciones:** Notas médicas específicas
- **Actualizar horas:** Ajustar horarios si es necesario

#### 🧾 Diagnósticos
**Acceso:** Menú → Diagnósticos
- **Agregar diagnóstico:** Para pacientes atendidos
- **Campos requeridos:**
  - Fecha de diagnóstico
  - Descripción del diagnóstico
  - Plan de tratamiento prescrito
- **Vinculación:** Relacionado con paciente y médico específico

#### 👤 Mi Perfil
**Acceso:** Menú → Mi Perfil
- **Información editable:**
  - Datos de contacto
  - Especialidad médica
  - Horarios de atención

---

### 🧑‍💼 PANEL PACIENTE

#### 📅 Reservar Cita
**Acceso:** Menú → Reservar Cita
- **Seleccionar médico:** Lista de especialistas disponibles
- **Elegir fecha:** Calendario para programación
- **Seleccionar hora:** Horarios disponibles
- **Observaciones:** Notas especiales para el médico
- **Confirmación:** Cita creada con estado "Pendiente"

#### 📋 Mis Citas
**Acceso:** Menú → Mis Citas
- **Ver citas programadas:** Lista cronológica de citas
- **Información mostrada:**
  - Fecha y hora de la cita
  - Médico asignado
  - Estado actual
  - Observaciones
- **Estados posibles:**
  - 🟡 Pendiente de confirmación
  - ✅ Confirmada por el médico
  - ❌ Cancelada

#### 👤 Mi Perfil
**Acceso:** Menú → Mi Perfil
- **Datos editables:**
  - Información personal
  - Datos de contacto
  - Cambiar contraseña
- **Información visible:**
  - Historial de citas
  - Diagnósticos recibidos

---

## ⚙️ Estados del Sistema

### Estados de Citas
| Estado | Descripción | Quién puede cambiar |
|---------|-------------|-------------------|
| 🟡 **Pendiente** | Cita solicitada, esperando confirmación | Médico |
| ✅ **Confirmada** | Cita autorizada y programada | Médico |
| ❌ **Cancelada** | Cita anulada | Médico |

### Estados de Usuarios
| Estado | Descripción |
|---------|-------------|
| 🟢 **Activo** | Usuario con acceso completo |
| 🔴 **Inactivo** | Usuario sin permisos |

---

## 🔧 Funciones Técnicas

### Autenticación y Seguridad
- **Sesiones HTTP:** Manejo seguro de usuarios logueados
- **Validación de roles:** Acceso diferenciado por tipo de usuario
- **Verificación de sesión:** Protección de rutas sensibles

### Base de Datos
- **Modelo relacional:** Usuario → Medico/Paciente → Citas/Diagnósticos
- **Integridad referencial:** Claves foráneas y constraints
- **Auditoría:** Registro de todas las operaciones

### Interfaz de Usuario
- **Diseño responsivo:** Bootstrap 5.3.0
- **Modo oscuro:** Theme aplicado por defecto
- **Navegación dinámica:** JavaScript para cargar vistas AJAX
- **Experiencia fluida:** Transiciones y efectos visuales

---

## 🚨 Resolución de Problemas

### Problemas Comunes

#### ❌ Error de conexión a BD
```
Solución: Verificar configuración en application.properties
- URL de conexión
- Credenciales de usuario
- Nombre de la base de datos
```

#### ❌ Usuario no encontrado
```
Solución: 
1. Verificar que el usuario existe en la BD
2. Confirmar username y password correctos
3. Contactar administrador si persiste
```

#### ❌ Sesión expirada
```
Solución:
1. Cerrar sesión
2. Iniciar sesión nuevamente
3. Verificar conectividad
```

---

## 📊 Datos de Prueba

### Script SQL de Inserción
Para poblar la base de datos con datos de prueba, ejecutar:

```sql
-- [Script SQL comentado anteriormente]
-- Ver sección "Scripts SQL para SQL Server" para datos completos
```

### Usuarios de Prueba por Defecto

| Rol | Usuario | Contraseña | Descripción |
|-----|---------|------------|-------------|
| Admin | `admin` | `admin123` | Administrador principal |
| Médico | `dr.perez` | `medico123` | Dr. Juan Pérez - Cardiología |
| Médico | `dra.garcia` | `medico123` | Dra. María García - Pediatría |
| Paciente | `carlos.s` | `paciente123` | Carlos Silva - Paciente de prueba |

---

## 🛠️ Desarrollo y Extensión

### Estructuras Extensibles

#### 👎 Entidades Futuras
- **Especialidades:** Catálogo de especialidades médicas
- **Horarios:** Configuración de disponibilidad médica
- **Facturación:** Módulo de pagos y facturas
- **Reportes:** Dashboard con métricas del sistema

#### 🔧 Interfaces Futuras
- **API REST:** Para integración con otros sistemas
- **Aplicación móvil:** Versión móvil del sistema
- **Notificaciones:** Alertas por email/SMS
- **Calendario avanzado:** Integración con calendarios externos

---

## 📞 Soporte Técnico

### Información de Contacto
- **Desarrolladores:** Grupo EFSRTIV
- **Institución:** Cibertec - V Ciclo EFSRTIV
- **Versión:** 1.0
- **Fecha:** Octubre 2025

### Documentación Adicional
- **Código fuente:** Comentarios detallados en inglés/español
- **Base de datos:** Diagrama ER disponible
- **API endpoints:** Documentación de rutas REST

---

## 📖 Notas de Versión

### Versión 1.0 (Octubre 2025)
- ✨ Funcionalidades principales implementadas
- 🔐 Sistema de autenticación completo
- 📋 Gestión de citas básica
- 👥 Roles de usuario diferenciados
- 🎨 Interfaz moderna con Bootstrap

### Próximas Versiones
- 📅 **v1.1:** Mejoras en UX/UI
- 📊 **v1.2:** Sistema de reportes
- 📱 **v2.0:** Aplicación móvil
- 🔌 **v2.1:** API REST completa

---

## ✅ Conclusión

El Sistema de Gestión de Citas Médicas es una solución integral para clínicas pequeñas y medianas, proporcionando todas las herramientas necesarias para la administración eficiente de citas médicas, pacientes y médicos. La arquitectura modular permite futuras expansiones y mejoras según las necesidades específicas de cada centro médico.

**¡Gracias por usar nuestro sistema! 🏥**
