# 📱 Android App - Kotlin + Jetpack Compose

Proyecto de práctica en desarrollo Android utilizando **Kotlin** y **Jetpack Compose**.  
La aplicación está estructurada siguiendo los principios de **Clean Architecture**, separando claramente las capas para lograr un código más mantenible, escalable y testeable.

## 🧠 Arquitectura

El proyecto implementa:

- **Clean Architecture**
  - Separación en capas: `data`, `domain`, `ui`
- **MVVM (Model-View-ViewModel)** en la capa de presentación
- **Inyección de dependencias** para desacoplar componentes
- Manejo de datos **remotos y locales**
- Uso de **casos de uso (use cases)** para la lógica de negocio

## 🗂️ Estructura del proyecto

```bash
com.tuapp
│
├── data
│   ├── remote
│   │   ├── api
│   │   ├── dto
│   │   └── repository
│   │
│   └── local
│       ├── db
│       └── entity
│
├── domain
│   ├── model
│   ├── repository
│   └── usecase
│
├── ui
│   ├── components
│   ├── theme
│   │
│   ├── home
│   │
│   ├── login
│
├── navigation
│
└── di