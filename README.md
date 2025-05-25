Generación de Nodos con SHA-256 en Python, JavaScript y Java

Descripción:

Este proyecto compara la generación de estructuras con firma digital SHA-256 en Python, JavaScript y Java, midiendo su rendimiento y tiempos de ejecución en distintas configuraciones (n nodos y k elementos por nodo).

1️⃣ Instalación y Ejecución
🔹 Python
1️⃣ Requisitos: Python 3.x
2️⃣ Ejecutar el script:

      python automata.py


🔹 JavaScript (Node.js)
1️⃣ Instalar Node.js: https://nodejs.org/es
2️⃣ Ejecutar el script:

      node automata.js


🔹 Java
1️⃣ Instalar JDK: https://www.oracle.com/java/technologies/downloads/#java17-windows
2️⃣ Compilar y ejecutar:

        javac Automata.java
        java Automata


2️⃣ Métodos de Medición de Tiempos
Cada lenguaje usa un método específico:
✅ Python: time.time()
✅ JavaScript: performance.now()
✅ Java: System.nanoTime()
Estos métodos garantizan una medición precisa del rendimiento en distintas condiciones.

3️⃣ Resultados de Ejecución
Tiempos de ejecución obtenidos:
| Lenguaje | n=3, k=4    | n=10, k=200 | n=200, k=10 | 
| Java     | 55.80670 ms | 8.37660 ms  | 31.29100 ms | 
| JS       | 18.51090 ms | 0.88220 ms  | 2.31110 ms  | 
| Python   | 0.00007 seg | 0.00141 seg | 0.00142 seg | 


📌 Python es el más rápido, seguido por JavaScript, con Java mostrando tiempos más elevados por su gestión de memoria.

4️⃣ Estructura del Código
✅ Generación de nodos con SHA-256, asegurando vinculación entre ellos.
✅ Creación de listas con datos aleatorios, procesados en cada lenguaje.
✅ Medición y almacenamiento de tiempos de ejecución, mostrando resultados finales.

