![Logo oficial](imagenes/logoSportCenterURJC.png)

# SportCenter_URJC
Consiste en una web que administra y gestiona los centros deportivos de los campus de la URJC. 
- La parte pública consistiria en la navegación libre por la web y la visualizacion de todos sus apartados.
- La parte privada consiste en la gestion por parte de los usuarios de todos los recursos a los que se tenga acceso. 

## Índice
- [Entidades](#entidades)
- [Funcionalidades del servicio interno](#funcionalidades-del-servicio-interno)
- [Diagrama de navegación](#diagrama-de-navegación)
- [Esquema Entidad Relación](#esquema-entidad-relación)
- [Diagrama UML](#diagrama-uml)
- [Capturas de pantalla Fase 2](#capturas-de-pantalla-fase-2)
- [Miembros del equipo](#miembros-del-equipo)

# Entidades 
### Usuario
Representa a los usuarios del sistema que pueden ser alumnos, docentes, trabajadores y administradores de los centros deportivos.
Los usuarios pueden realizar reservas en los distintos centros deportivos.

### Centro deportivo
Representa los diferentes centros deportivos de los distintos campus.
Cada centro deportivo estará constituido por una serie de distintas Actividades disponibles.

### Actividad
Representa los distintos tipos de actividades deportivas disponibles para los usuarios.
Cada actividad está asociada a un centro deportivo y a una/varias pistas deportivas.

### Reserva
Representa la forma en la que un usuario solicita una reserva para una actividad en un centro deportivo.
Está asociada al centro deportivo donde se realiza la reserva, a la actividad reservada y al usuario que realiza la reserva.

### Pista deportiva
Representa todas las distintas pistas/canchas de las distintas actividades que se realizan en los distintos centros deportivos.
Las pistas deportivas están asociadas a las actividades de los centros deportivos.

# Funcionalidades del servicio interno
Generación de mensaje de aviso sobre la disponibilidad de las actividades.
Generación de correo electrónico a la cuenta de la universidad con los datos de la reserva.
Generación de un documento de texto con toda la información relacionada con el usuario (reservas y/o actividades).

# Diagrama de navegación
![Diagrama de navegación](imagenes/diagramaNavegacionFase3.png)

# Esquema Entidad Relación
![EsquemaER](imagenes/EsquemaER.png)

# Diagramas UML
### UML General
![DiagramaUMLpsi](imagenes/UMLpsi.png)
### UML Aplicación Web
![DiagramaUMLpc](imagenes/UMLpc.png)
### UML Servicio Interno
![DiagramaUMLsic](imagenes/UMLsic.png)
![LeyendaUML](imagenes/LeyendaUML.png)

# Capturas de pantalla Fase 2
Página inicial de bienvenida
![PAGINA INICIO](imagenes/PAGINAINICIO.png)
Formulario de registro de usuario nuevo
![PAGINA REGISTRO](imagenes/PAGINAREGISTRO.png)
Formulario inicio de sesión de usuario registrado
![PAGINA INICIO SESIONL](imagenes/PAGINAINICIOSESION.png)
Página principal de la web
![PAGINA PRINCIPAL](imagenes/PAGINAPRINCIPAL.png)
Página de selección de campus
![PAGINA CAMPUS](imagenes/PAGINACAMPUS.png)
Página de selección de actividad
![PAGINA ACTIVIDAD](imagenes/PAGINAACTIVIDAD.png)
Página de creación de actividad
![PAGINA CREACION ACTIVIDAD](imagenes/PAGINACREACIONACTIVIDAD.png)
Formulario de creación de actividad
![FORMULARIO CREACION ACTIVIDAD](imagenes/FORMULARIOCREACIONACTIVIDAD.png)
Página de borrado de actividad
![PAGINA BORRADO ACTIVIDAD](imagenes/PAGINABORRADOACTIVIDAD.png)
Formulario de reserva de actividad
![PAGINA RESERVAR](imagenes/PAGINARESERVAR.png)
Visualización de reserva
![PANTALLA RESERVADO](imagenes/PANTALLARESERVADO.png)
Página de cancelación de reserva
![PANTALLA CANCELAR](imagenes/PANTALLACANCELAR.png)
Página del perfil del usuario
![PANTALLA PERFIL](imagenes/PANTALLAPERFIL.png)

# Miembros del equipo
- Antonio Francisco Roldan Martin - af.roldan@alumnos.urjc.es - https://github.com/afroldan
- Jorge Molina Tirado - j.molinat.2017@alumnos.urjc.es - https://github.com/JorgeM1404
- Tomasz Wiktor Paterek - tw.paterek.2017@alumnos.urjc.es - https://github.com/konzyy
