# Paint

A Java Swing desktop application for working with 2D graphics, built around the **MVC** architectural pattern and several classic design patterns. This project extends an earlier Paint application (originally built for Object-Oriented Information Technologies) with the additional functionality required for the **Design Patterns (Dizajnerski zadaci) 2024/2025** course project.

## Overview

Paint lets the user draw, select, and edit geometric shapes on a canvas, with full undo/redo support, a live action log, save/load of both the log and the complete drawing, and Z-order control over drawn shapes.

## Features

- **Shapes**: Point, Line, Circle, Donut (extends Circle), Rectangle, Hexagon
- **Colors** — outline and fill color chosen via `JColorChooser`; the currently active outline/fill colors are displayed and can be changed by clicking on them
- **Transparent hole** — the Donut's inner hole is rendered as true transparency using `Graphics2D`, `Shape`, `Area`, and `Ellipse2D`
- **Hexagon via Adapter** — hexagon add/delete/modify is implemented by adapting an external `hexagon.jar` library through the Adapter pattern
- **Undo / Redo** — implemented with the Command and Prototype patterns; the Undo/Redo buttons are only enabled when the corresponding action is available, and Redo is cleared once a new action is performed after an Undo
- **Multi-selection** — multiple shapes can be selected at once
- **Modify** — enabled only when exactly one shape is selected; a modified shape remains selected afterward
- **Delete** — enabled only when at least one shape is selected
- **Z-order control** — To Front, To Back (move one position), Bring to Front, Bring to Back (move to the top/bottom position)
- **Action log** — every user action (draw, select, modify, delete, Z-order changes, undo, redo) is logged and shown live in the application
- **Persistence**
  - Save the action log to an external text file
  - Save the complete drawing via serialization to an external file (Strategy pattern)
  - Load a log file and replay it command by command, interactively rebuilding the drawing
  - Load a complete saved drawing

## Design patterns used

| Pattern | Where it's used |
|---|---|
| **MVC** | Overall application architecture (Model = shape list, View = drawing `JPanel`, Controller = user input handling) |
| **Command** | Encapsulating user actions to support undo/redo |
| **Prototype** | Cloning shape/command state for undo/redo |
| **Adapter** | Wrapping `hexagon.jar` to integrate the Hexagon shape |
| **Strategy** | Saving the drawing/log in different ways (text log vs. serialized drawing) |
| **Observer** | Enabling/disabling the Delete and Modify buttons based on the current selection state |

## Project structure

```
NebojsaVujicPaint/
└── src/
    ├── command/        # Command pattern implementations (draw, delete, modify, z-order, undo/redo)
    ├── controller/      # application controller (MVC)
    ├── geometry/        # shape classes (Point, Line, Circle, Donut, Rectangle, Hexagon...)
    ├── gui/             # Swing user interface
    ├── hexagonAdapter/  # Adapter pattern wrapper around hexagon.jar
    ├── model/           # application model (MVC) — the list of drawn shapes
    ├── Observer/         # Observer pattern (button enable/disable based on selection)
    ├── Sort/            # sorting of drawn elements / Z-order handling
    ├── stack/           # stack implementation backing undo/redo history
    ├── strategy/         # Strategy pattern for saving the log/drawing
    └── view/            # rendering / drawing panel (MVC)
```

## Technologies

- Java (Java Modules — `module-info.java`)
- Swing (GUI), Java 2D (`Graphics2D`, `Shape`, `Area`, `Ellipse2D`)
- Java Serialization
- External library: `hexagon.jar` (wrapped via Adapter)

## Getting started

1. Open the project in IntelliJ IDEA (`File → Open`)
2. Wait for modules and dependencies to be indexed
3. Make sure `hexagon.jar` is added as a project library/dependency
4. Run the main class from the `gui` package (run configuration `Nebojsa_Vujic_it60`)

## Author

Nebojša Vujić — IT60/2023, Design Patterns course project, academic year 2025/2026.
