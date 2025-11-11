<h1>Patrones de diseño usados</h1>
<h2>Front Controller</h2>
<p>
  Se trata de un patrón de diseño que controla que las peticiones del usuario sean manejadas por un solo controlador,
  limitando accesos no deseados y centralizando el manejo de peticiones.
</p>
<h2>Singleton</h2>
<p>
  Aplicado el modelo Singleton al acceso a la conexión de la base de datos. Es decir, permite uan sola instancia de
  la conexión para evitar el desperdicio de recursos y estados incoherentes. Solo se podrá acceder a la base de datos
  de la forma en la que definamos, y con un solo punto de acceso.
</p>
<h2>Builder</h2>
<p>
  La clase Empleado tendrá un EmpleadoBuilder, que implementa Builder, lo cual permite crear empleados de forma
  sencilla mediante composición, en caso de que nuestra clase Empleado necesite nuevas características,
  o subclases más complejas.
</p>
<h2>Factory</h2>
<p>
  La clase Empleado tendrá un EmpleadoFactory, que evita acoplamiento entre componentes.
  Facilita la legibilidad del código (por ejemplo al listar los datos de los empleados de la BBDD)
  ya que administra la creación de los empleados al estandarizar y aislar la query. Además, es fácilmente 
  expandible sin correr riesgos de afectar al resto de funciones y componentes del programa.
</p>
