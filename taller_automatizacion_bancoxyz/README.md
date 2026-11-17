# Taller de automatizacion - Proyecto 9 (XYZ Bank / GlobalSQA)
Screenplay + Serenity BDD + Cucumber + Gradle (Java 17)

Sitio: https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login

## 1. Entorno (lo que se vio en clase)
- **Java 17** (JDK) y variable de entorno `JAVA_HOME` apuntando a el.
- **Gradle 8.6+** (o 9.x) y `GRADLE_HOME` en las variables de entorno; `gradle -v` debe responder.
- **IntelliJ IDEA**: `File > Open` sobre el archivo `build.gradle` y elegir *Open as Project*.
  Luego `Settings > Build Tools > Gradle`: usar **Gradle JDK 17** y Gradle local (no Maven).
- Chrome instalado (Serenity descarga el driver solo).

## 2. Generar el arquetipo con el scaffold
Hacerlo en una carpeta **vacia y distinta** de la que contiene este repo. El profesor evalua que el comando use los nombres del banco.

**Windows (PowerShell)** - OJO: no usar `&&` ni `echo ... > build.gradle` (PowerShell lo guarda en UTF-16 y Gradle no lo lee):
```powershell
cd $HOME\Downloads
mkdir scaffold_banco
cd scaffold_banco
Set-Content -Path build.gradle -Value "plugins { id 'io.github.jumosqu12.screenplayarchitecture' version '2.0.1' }" -Encoding ascii
gradle spa --projectName=taller_automatizacion_bancoxyz --groupId=co.edu.udea.certificacion --principalPackage=banking --type=UX
```
**Linux / macOS / Git Bash:**
```bash
mkdir scaffold_banco && cd scaffold_banco
echo "plugins { id 'io.github.jumosqu12.screenplayarchitecture' version '2.0.1' }" > build.gradle
gradle spa --projectName=taller_automatizacion_bancoxyz --groupId=co.edu.udea.certificacion --principalPackage=banking --type=UX
```
- `groupId = co.edu.udea.certificacion` (el que dicto el profesor), `principalPackage = banking` (modulo bajo prueba).
- `gradle spa` **sobrescribe `build.gradle`**: despues copien `src/`, `build.gradle` y `settings.gradle` de este repo encima
  (PowerShell: `Copy-Item -Path "<ruta_del_repo>\*" -Destination . -Recurse -Force`).
- Si el scaffold dejo clases de ejemplo (runner/step definition de muestra) que dupliquen pasos, borrenlas.
- Si cambian los nombres, renombren tambien el paquete `co.edu.udea.certificacion.banking` (IntelliJ: *Refactor > Rename*)
  y el `glue` de los runners.

## 3. Ejecutar
En PowerShell, **todo argumento `-D` va entre comillas** (si no, PowerShell parte el nombre en el punto).
```powershell
gradle clean test                                              # toda la bateria (BankingRunner)
gradle clean test --tests "*SmokeRunner"                       # solo @smoke
gradle clean test "-Dcucumber.filter.tags=@e2e"                # un tag puntual (@e2e, @deposits, @exception_path...)
gradle clean test "-Dcucumber.filter.tags=@deposits and @happy_path"
gradle clean test "-Dpausa.ms=2000"                            # pausa de 2 s entre pasos (socializacion)
gradle clean test "-Dheadless.mode=true"                       # sin ventana (solo para depurar rapido)
```
Reporte Serenity: `target/site/serenity/index.html` (tarea `aggregate`, corre sola al final de `test`).

## 4. Estructura (lo que pide el profesor)
| Elemento | Donde | Notas |
|---|---|---|
| Feature (Gherkin, ingles, sin tildes) | `src/test/resources/features/` | historia de usuario + escenarios singulares y *Scenario Outline* con *Examples* |
| Runner (punto de entrada) | `src/test/java/.../runners` | `BankingRunner`, `SmokeRunner`: apuntan al feature y al glue |
| StepDefinition | `src/test/java/.../stepdefinitions` | clases `*StepDefinition`; **aqui se define el actor** (`theActorCalled`) |
| Task (lenguaje del negocio) | `src/main/java/.../tasks` | Depositar, Retirar, AgregarCliente... reciben el **modelo**, no parametros sueltos |
| Interaction ("la menuda") | `.../interactions` | DarClic, EscribirTexto, SeleccionarOpcion, AceptarAlerta, Esperar |
| Question (validaciones) | `.../questions` | SaldoActual, MensajeDeTransaccion, NumeroDeTransacciones... |
| User Interface (mapeo de elementos) | `.../userinterfaces` | Targets por pantalla (xpath/css) |
| Model | `.../models` | Cliente, Cuenta, Movimiento, Transferencia |
| Utils | `.../utils` | Texto (compara sin mayusculas/tildes), Constantes |
| Exceptions | `.../exceptions` | errores de automatizacion (alerta ausente, saldo ilegible) |

Flujo: *Feature -> Runner -> StepDefinition -> Task -> Interaction -> (UI) y Question para validar*.
Habilidad del actor: `BrowseTheWeb`, entregada por `OnlineCast` en `HooksStepDefinition`.

## 5. Criterios de aceptacion -> escenarios
| Requerimiento del taller | Feature | Caminos |
|---|---|---|
| Depositos | deposits.feature | feliz (1, 100, 99999), excepcional (sin monto), alterno (historial) |
| Retiros / balance | withdrawals.feature | feliz, limite (saldo total = 0), excepcional (monto > saldo) |
| Transferencias | transfers.feature | feliz y excepcional (simulada, ver nota) |
| Agregar clientes | customer_management.feature | feliz, duplicado, formulario vacio |
| Abrir cuentas | customer_management.feature | Dollar / Pound / Rupee |
| Listar clientes | customer_management.feature | listar y buscar (incluye 0 resultados) |
| E2E | complete_banking_flow.feature | gerente crea cliente + cuenta -> cliente deposita y retira |

## 6. Validar los localizadores (como lo mostro el profesor)
Los localizadores se escribieron sin poder abrir la app. Antes de la primera corrida, para **cada fila**:
abrir la pagina, `F12` -> flecha de inspeccion -> clic en el elemento -> `Ctrl+F` en la pestana *Elements*
-> pegar el XPath/CSS y confirmar que resalta **1 solo elemento** (en las tablas de filas, varios es normal).
Si falla, corregirlo unicamente en `userinterfaces/`.

| Pantalla | Elemento | Localizador |
|---|---|---|
| AccountPage | nombre del cliente | `//span[contains(@class,'fontBig')]` |
| AccountPage | lista de cuentas | `#accountSelect` |
| AccountPage | numero de cuenta | `(//div[@ng-hide='noAccount']//strong)[1]` |
| AccountPage | saldo de la cuenta | `(//div[@ng-hide='noAccount']//strong)[2]` |
| AccountPage | boton Transactions | `//button[@ng-click='transactions()']` |
| AccountPage | boton Deposit | `//button[@ng-click='deposit()']` |
| AccountPage | boton Withdrawl | `//button[@ng-click='withdrawl()']` |
| AccountPage | campo monto | `//input[@ng-model='amount']` |
| AccountPage | boton confirmar monto | `//form//button[@type='submit']` |
| AccountPage | mensaje de la transaccion | `//span[@ng-show='message']` |
| AccountPage | boton Logout | `//button[@ng-click='logout()']` |
| AddCustomerPage | campo First Name | `//input[@ng-model='fName']` |
| AddCustomerPage | campo Last Name | `//input[@ng-model='lName']` |
| AddCustomerPage | campo Post Code | `//input[@ng-model='postCd']` |
| AddCustomerPage | boton Add Customer (submit) | `//form//button[@type='submit']` |
| CustomerLoginPage | lista de clientes | `#userSelect` |
| CustomerLoginPage | boton Login | `//button[normalize-space()='Login']` |
| CustomersPage | buscador de clientes | `//input[@ng-model='searchCustomer']` |
| CustomersPage | filas de clientes | `//table//tbody/tr` |
| CustomersPage | fila del cliente {0} | `//table//tbody/tr[contains(normalize-space(.), '{0}')]` |
| LoginPage | boton Customer Login | `//button[@ng-click='customer()']` |
| LoginPage | boton Bank Manager Login | `//button[@ng-click='manager()']` |
| LoginPage | boton Home | `//button[@ng-click='home()']` |
| ManagerPage | boton Add Customer | `//button[@ng-click='addCust()']` |
| ManagerPage | boton Open Account | `//button[@ng-click='openAccount()']` |
| ManagerPage | boton Customers | `//button[@ng-click='showCust()']` |
| OpenAccountPage | lista de clientes | `#userSelect` |
| OpenAccountPage | lista de monedas | `#currency` |
| OpenAccountPage | boton Process | `//button[normalize-space()='Process']` |
| TransactionsPage | filas de transacciones | `//table//tbody/tr` |
| TransactionsPage | boton Back | `//button[@ng-click='back()']` |

## 7. Notas
- **Transferencias:** el portal no tiene pantalla de transferencia; se modelo como retiro en la cuenta origen + deposito
  en la destino (solo si el retiro fue exitoso). Mencionarlo en la socializacion.
- Los datos de ejemplo (Harry Potter: cuentas 1004, 1005, 1006) y los mensajes en ingles salen de la app publica; si cambian,
  se ajustan en los `.feature`.
- Cada escenario abre y cierra su propio navegador, por lo que son independientes.
- Gherkin en ingles y sin tildes: el profesor advirtio que las tildes/caracteres especiales dan problemas de reconocimiento.
