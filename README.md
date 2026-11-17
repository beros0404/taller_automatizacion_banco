# Taller de automatización — Caso 9: XYZ Bank / GlobalSQA

Proyecto E2E del banco **XYZ Bank** usando **Screenplay + Serenity BDD + Cucumber + Gradle (Java 17)**.

- Sitio: <https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login>
- Caso del taller: **9**
- Stack: Java 17, Gradle 8.10 (wrapper), Serenity BDD 4.2.34, Cucumber, Selenium WebDriver, JUnit 4.

---

## 1. Requisitos

| Herramienta | Versión / nota |
|---|---|
| JDK | **17** |
| Gradle | **8.10**  |
| Chrome | Instalado y actualizado |
| IntelliJ IDEA | Recomendado |

---

## 2. Ejecución rápida

```bash
./gradlew clean test
```

En Windows:

```bat
gradlew.bat clean test
```

El comando ejecuta los dos runners (`BankingRunner` y `SmokeRunner`), por lo que los escenarios `@smoke` se ejecutan dos veces. Para evitar esa duplicación se pueden usar uno de los siguientes comandos:

```bash
# Suite completa: 40 escenarios únicos
./gradlew clean test --tests "*BankingRunner"

# Solo smoke: 16 ejecuciones
./gradlew clean test --tests "*SmokeRunner"
```

### 3.1 Reporte

Al finalizar, Serenity genera:

```text
target/site/serenity/index.html
```

Se pueden abrir con:

```bash
xdg-open target/site/serenity/index.html   # Linux
open target/site/serenity/index.html       # macOS
start target/site/serenity/index.html      # Windows
```

### 3.2 Tags

```bash
# Solo E2E
./gradlew test --tests "*BankingRunner" -Dcucumber.filter.tags=@e2e

# Solo transferencias
./gradlew test --tests "*BankingRunner" -Dcucumber.filter.tags=@transfers

# Gerente sin caminos excepcionales
./gradlew test --tests "*BankingRunner" -Dcucumber.filter.tags="@manager and not @exception_path"

# Excluir un tag
./gradlew test --tests "*BankingRunner" -Dcucumber.filter.tags="not @ignore"
```

### 3.3 Pausa 

La pausa por defecto es **1000 ms** entre acciones. Se puede cambiar sin modificar el código con los siguientes comandos:

```bash
# 2 segundos
./gradlew clean test --tests "*SmokeRunner" -Dpausa.ms=2000

# Sin pausa (ejecución rápida)
./gradlew clean test --tests "*BankingRunner" -Dpausa.ms=0
```

### 3.4 Modo headless

```bash
./gradlew clean test --tests "*SmokeRunner" -Dheadless.mode=true -Dpausa.ms=0
```

Por defecto el navegador es **visible** (`headless.mode = false` en `serenity.conf`). 

---

## 4. Estructura

```text
taller_automatizacion_banco/
├── build.gradle
├── settings.gradle
├── gradle.properties
├── serenity.properties
├── gradlew
├── gradlew.bat
├── gradle/wrapper/
├── README.md
├── src/
│   ├── main/java/co/edu/udea/certificacion/banking/
│   │   ├── exceptions/       # Errores de automatización
│   │   ├── interactions/     # "La menuda": clic, escritura, alertas, esperas, extracto
│   │   ├── models/           # Cliente, Cuenta, Movimiento, Transferencia
│   │   ├── questions/        # Saldo, mensajes, listas, moneda, historial
│   │   ├── tasks/            # Objetivos de negocio
│   │   ├── userinterfaces/   # Targets / localizadores
│   │   └── utils/            # Constantes y normalización de texto
│   └── test/
│       ├── java/.../runners/          # BankingRunner, SmokeRunner
│       ├── java/.../stepdefinitions/  # Hooks + pasos
│       └── resources/
│           ├── features/              # Gherkin
│           └── serenity.conf
└── target/site/serenity/              # Reporte (se genera al ejecutar)
```

Flujo:

```text
Feature (Gherkin)
   ↓
StepDefinition
   ↓
Actor → Task → Interaction → Selenium
   ↓
Question + seeThat (validación)
```

---

## 5. Cobertura funcional

| Requerimientos | Feature | Caminos |
|---|---|---|
| Depósitos | `features/cliente/deposits.feature` | feliz (1, 100, 99999), sin monto, historial |
| Retiros y saldo | `features/cliente/withdrawals.feature` | feliz, saldo total, monto mayor al saldo |
| Transferencias | `features/cliente/transfers.feature` | feliz y saldo insuficiente (simulada) |
| Historial / extracto | `features/cliente/transaction_history.feature` | movimientos consecutivos, reset, sin monto |
| Resumen y sesión | `features/cliente/account_overview.feature` | bienvenida por cliente, logout |
| Nuevos clientes | `features/gerente/customer_management.feature` | alta, duplicado, formulario vacío |
| Abrir cuentas | `features/gerente/customer_management.feature` | Dollar, Pound, Rupee |
| Listar / buscar clientes | `features/gerente/customer_management.feature` | listar, buscar por nombre y cuenta |
| Administración | `features/gerente/customer_administration.feature` | eliminar, buscar por cuenta |
| E2E | `features/e2e/*.feature` | cliente + cuenta + operaciones, cuentas nuevas |

**Números:** 40 escenarios únicos; 56 ejecuciones si se corren ambos runners (`@smoke` se repite).

---

## 6. Decisiones técnicas importantes

### 6.1 Transferencias simuladas

La aplicación **no tiene una pantalla de transferencias**. Se modela como una operación de negocio compuesta:

1. Retirar el monto de la cuenta origen.
2. Verificar que el retiro fue exitoso.
3. Depositar el monto en la cuenta destino.

Está implementado en `TransferirEntreCuentas`. **No es atómico**: si el depósito falla después del retiro, el dinero queda retirado. Esto es una limitación de la app.

### 6.2 Workaround del filtro de fechas del extracto

La app tiene un defecto: la tabla de **Transactions** queda vacía porque `startDate` no se inicializa y el filtro `sDate` excluye todos los movimientos. La interacción `MostrarTodosLosMovimientos` fija el rango en el scope de Angular antes de contar las filas. No modifica transacciones; solo corrige el filtro de la vista. 

### 6.3 Estado determinista

La app persiste clientes, cuentas y transacciones en `localStorage`. `AbrirElBanco` elimina esas claves y recarga la página antes de cada escenario; la app vuelve a crear los datos semilla. Además, `serenity.conf` usa `use.unique.browser = true`.

Datos semilla:

| Cliente | Cuentas | Monedas |
|---|---|---|
| Hermoine Granger | 1001, 1002, 1003 | Dollar, Pound, Rupee |
| Harry Potter | 1004, 1005, 1006 | Dollar, Pound, Rupee |
| Ron Weasly | 1007, 1008, 1009 | Dollar, Pound, Rupee |
| Albus Dumbledore | 1010, 1011, 1012 | Dollar, Pound, Rupee |
| Neville Longbottom | 1013, 1014, 1015 | Dollar, Pound, Rupee |

Harry se usa para las pruebas de cuenta porque empieza en 0 y sin movimientos.

### 6.4 Cuentas por moneda, no por número

`SeleccionarCuentaPorMoneda` recorre las opciones de la cuenta y elige la que coincide con Dollar/Pound/Rupee. Así no se depende de números hardcodeados en las transferencias.

### 6.5 Sin `ignoreFailures`

El build **falla si alguna prueba falla**. El reporte se genera igual porque `test.finalizedBy aggregate`. No se ocultan fallos.

---

## 7. Localizadores principales

| Pantalla | Elemento | Localizador |
|---|---|---|
| LoginPage | Customer Login | `//button[@ng-click='customer()']` |
| LoginPage | Bank Manager Login | `//button[@ng-click='manager()']` |
| LoginPage | Home | `//button[@ng-click='home()']` |
| CustomerLoginPage | lista de clientes | `#userSelect` |
| CustomerLoginPage | Login | `//button[normalize-space()='Login']` |
| AccountPage | nombre del cliente | `//span[contains(@class,'fontBig')]` |
| AccountPage | lista de cuentas | `#accountSelect` |
| AccountPage | número de cuenta | `(//div[@ng-hide='noAccount']//strong)[1]` |
| AccountPage | saldo | `(//div[@ng-hide='noAccount']//strong)[2]` |
| AccountPage | moneda | `(//div[@ng-hide='noAccount']//strong)[3]` |
| AccountPage | Transactions | `//button[@ng-click='transactions()']` |
| AccountPage | Deposit | `//button[@ng-click='deposit()']` |
| AccountPage | Withdrawl | `//button[@ng-click='withdrawl()']` |
| AccountPage | campo monto | `//input[@ng-model='amount']` |
| AccountPage | confirmar monto | `//form//button[@type='submit']` |
| AccountPage | mensaje | `//span[@ng-show='message']` |
| AccountPage | Logout | `//button[contains(@class,'logout')]` |
| AddCustomerPage | First Name | `//input[@ng-model='fName']` |
| AddCustomerPage | Last Name | `//input[@ng-model='lName']` |
| AddCustomerPage | Post Code | `//input[@ng-model='postCd']` |
| AddCustomerPage | Add Customer | `//form//button[@type='submit']` |
| ManagerPage | Add Customer | `//button[@ng-click='addCust()']` |
| ManagerPage | Open Account | `//button[@ng-click='openAccount()']` |
| ManagerPage | Customers | `//button[@ng-click='showCust()']` |
| OpenAccountPage | cliente | `#userSelect` |
| OpenAccountPage | moneda | `#currency` |
| OpenAccountPage | Process | `//button[normalize-space()='Process']` |
| CustomersPage | buscador | `//input[@ng-model='searchCustomer']` |
| CustomersPage | filas | `//table//tbody/tr` |
| CustomersActionsPage | Delete de un cliente | `//table//tbody/tr[td[1][normalize-space()='{0}'] and td[2][normalize-space()='{1}']]//button[normalize-space()='Delete']` |
| TransactionsPage | filas | `//table//tbody/tr` |
| TransactionsActionsPage | Reset | `//button[@ng-click='reset()']` |


---

## 8. Comandos útiles

```bash
# Compilar
./gradlew compileJava compileTestJava

# Suite completa sin duplicar smoke
./gradlew clean test --tests "*BankingRunner"

# Smoke
./gradlew clean test --tests "*SmokeRunner"

# Reporte
xdg-open target/site/serenity/index.html

# Ver tareas
./gradlew tasks

# Limpiar
./gradlew clean
```

---

## 9. Solución de problemas

| Síntoma | Causa probable | Solución |
|---|---|---|
| `Unsupported class file major version` | JDK incorrecto | Usar JDK 17 en IntelliJ y Gradle JVM |
| `No chrome binary` / driver | Chrome no instalado o driver desactualizado | Instalar Chrome; `autodownload = true` está configurado |
| `DevToolsActivePort` / Chrome no inicia | Entorno Linux/CI | `--no-sandbox` y `--disable-dev-shm-usage` ya están en `serenity.conf` |
| `Undefined step` | `glue` incorrecto | Verificar `co.edu.udea.certificacion.banking.stepdefinitions` |
| El reporte no aparece | No se ejecutó `aggregate` | Ejecutar `./gradlew clean test`; `test.finalizedBy aggregate` |
| El navegador no se cierra | Falta `@After` | `HooksStepDefinition` ejecuta `OnStage.drawTheCurtain()` |
| Acentos rotos | Codificación | `serenity.properties` y `options.encoding` están en UTF-8 |
| `ElementNotInteractable` | Vista Angular aún no renderizada | Ya se usan `WaitUntil` en formularios y transiciones |
| El historial aparece vacío | Defecto del filtro de fechas de la app | `MostrarTodosLosMovimientos` lo corrige |

---

## 10. Limitaciones conocidas

1. **No hay transferencia nativa**: se simula con retiro + depósito; no es atómica.
2. **El extracto tiene un defecto de filtrado**: se corrige con un workaround documentado.
3. **La app es pública y puede cambiar**: si cambian textos, localizadores o datos semilla, se debe ajustar `userinterfaces/` y los `.feature`.
4. **Gherkin en inglés y sin tildes**: se mantiene por compatibilidad con la configuración de Cucumber y para evitar problemas de codificación.

---

## 11. Evidencia de ejecución

Al 2026-11-17, la suite completa se ejecutó en modo headless con:

```bash
./gradlew clean test -Dheadless.mode=true -Dpausa.ms=0
```

Resultado:

```text
56 ejecuciones
0 fallos
0 errores
Reporte: target/site/serenity/index.html
```
