# App Reporte Ciudadano

Proyecto de App en Android Studio para curso **Android Intermedio**

Aplicación de los temas del curso de Android Intermedio en un caso real de una App que vamos contruyendo para el reporte de diferentes casos por parte de ciudadanos

### Temas vistos / aplicados
- [x] **Repaso curso básico**. Creación de un proyecto con dos `Activities` para la comunicación entre ellas
- [x] **Guardar en repositorio de GitHub**. Preparar Android Studio para que el proyecto se enlace a un repositorio en GitHub
- [x] **Persistencia de datos con `SharedPreferences`**. Guardar los datos del usuario en el dispositivo para que no tenga que llenarlos cada vez
- [x] **Navigation**. Creación de una `Activity` tipo Navigation Drawer para conocer sobre *Android Navigation Arquitecture*
- [x] **Assets desde Android Studio**. Creación de iconos para los menús
- [x] **Clases y objetos JSON con GSON**. Uso de la librería **GSON** para manejo de JSON
- [x] **Conectar con WebServices con Retrofit**. Configuración de Retrofit para conexión a WS
- [x] **Envio ~~y recepción~~ de datos a un servidor**. Enviar datos de un formulario ~~y consultar datos en JSON~~
- [x] **Solicitar permisos al usuario**. Checar si la app tiene permisos con `checkSelfPermission()` y sino solicitarlos con `requestPermissions()`
- [x] **Uso de GPS**. Activar y autorizar el uso de GPS para conocer la ubicación actual del usuario con el uso de la Api Google Location and Activity Recognition de Google Play Services
- [ ] **Pantalla de Login**. Realizar una pantalla de login usando todos los temas vistos, llamando al servidor y mostrando la MainActivity si es los datos son correctos
- [ ] ~~**Uso de la cámara**. Activar y autorizar el uso de la cámara para enviar imágenes al servidor~~
- [ ] **Activities para ~~*Splash*~~ y *Login*** Definir layouts y activities para la app ~~de Bitácora electrónica~~

### Evaluación
#### Crear Pantalla de Login y enviar datos a WS para verificar inicio de sesion
1. Crear activity LoginActivity que sea Launcher
2. Agregar campos usuario, contraseña y botón Iniciar Sesión al Layout
3. Agregar Clase LoginResult. Propiedades boolean error, String mensaje
4. Agregar método POST en Interface ReporteService. Parametros String username, password
5. Inicializar campos y service en activity LoginActivity
6. Crear función para llamar al método del servicio y asignarlo al click del botón
7. Mostrar mensaje y si es correcto finalizar activity LoginActivity e iniciar MainActivity

#### Usuarios admitidos
```
$arrLogin = array('admin'   => 'admin123',
                    'user'      => 'password',
                    'root'      => 'toor',
                    'qwerty'    => '123456',
                    'usuario'   => 'contraseña'
                    );
```


### Recursos
- GSON. https://github.com/google/gson
- Retrofit. https://square.github.io/retrofit/

---
Instructor   
**Ing Ivan Santos Pérez**    
@isantosp    
*Nov-Dic 2019*
