# 🐝 ApiculturaApp — API

REST API para gestión de colmenas de apicultura.  
Desarrollada como proyecto personal durante el ciclo DAM.

## Stack
Java 21 · Spring Boot 3.5.13 · Spring Data JPA · H2 · Lombok

## Parte de un sistema mayor
| Repositorio | Descripción | Estado |
|---|---|---|
| [apicultura-consola](https://github.com/OAlvarezOliveira/apicultura-consola) | Prototipo Java consola | ✅ Fase 1 |
| [apicultura-api](https://github.com/OAlvarezOliveira/apicultura-api) | REST API Spring Boot | 🚧 En desarrollo |
| apicultura-desktop | Cliente JavaFX | ⏳ Pendiente |
| apicultura-android | App Android campo | ⏳ Pendiente |

## Arrancar en local
```bash
git clone https://github.com/OAlvarezOliveira/apicultura-api.git
cd apicultura-api
./mvnw spring-boot:run
```
Servidor en `http://localhost:8080`  
Consola H2 en `http://localhost:8080/h2-console`

## 📓 Diario de desarrollo

### Sesión 1 — 09/04/2026
- Setup proyecto Maven con Spring Tools en Eclipse
- Dependencias: Web, JPA, H2, Lombok, Validation
- application.properties configurado
- Primer arranque exitoso — Tomcat en puerto 8080


### Sesión 2 — 10/04/2026

- Instalación y configuración de Lombok en Eclipse
- Primera entidad JPA `Colmena` — de clase Java a tabla en H2 automáticamente
- Enums `EstadoColmena` y `TipoAlza` con `@Enumerated(EnumType.STRING)`
- Entidad `Revision` con relación `@ManyToOne` hacia `Colmena`
- Relación bidireccional `@OneToMany` con cascade y orphanRemoval
- `ColmenaRepository` extendiendo `JpaRepository` — 0 líneas de SQL escritas
- `ColmenaService` con lógica de negocio — estado calculado automáticamente
- `ColmenaController` con endpoints GET y POST funcionales
- Primera revisión registrada y verificada en H2 en tiempo real
- Concepto clave aprendido: inyección de dependencias con `@RequiredArgsConstructor`
