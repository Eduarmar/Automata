import hashlib
import random
import datetime
import time

class Nodo:
    def __init__(self, partida, cuerpo):
        self.partida = partida
        self.cuerpo = cuerpo
        self.firma_digital = self.calcular_firma()

    def calcular_firma(self):
        contenido = f"{self.partida} {' '.join(map(str, self.cuerpo))}"
        return hashlib.sha256(contenido.encode()).hexdigest()
            

def generar_lista(n, k):
    lista_nodos = []

    tiempo_actual = datetime.datetime.now().strftime("%d-%m-%Y %H:%M:%S")
    primera_firma = hashlib.sha256(tiempo_actual.encode()).hexdigest()
    contenido_inicial = f"{tiempo_actual} {primera_firma}"

    for i in range(n):
        if i == 0:
            partida = contenido_inicial
        else:
            partida = lista_nodos[i - 1].firma_digital
    
        cuerpo = [random.randint(1, 100000) for _ in range(k)]
        nodo = Nodo(partida, cuerpo)
        lista_nodos.append(nodo)

    return lista_nodos

n_values = [3, 10, 200]  
k_values = [4, 200, 10] 
tiempos_ejecucion = []  

for index, (n, k) in enumerate(zip(n_values, k_values)):
    inicio = time.time()
    nodos = generar_lista(n, k)
    fin = time.time()

    tiempo = fin - inicio
    tiempos_ejecucion.append(f"Tiempo {chr(97 + index)}: {tiempo:.5f} seg") 

    print(f"\n=== Prueba con n={n}, k={k} ===")
    print(f"Tiempo de ejecucion: {fin - inicio:.5f} segundos \n")

    for i, nodo in enumerate(nodos):
        print(f"Nodo {i + 1}:")
        print(f"    Partida: {nodo.partida}")
        print(f"    Cuerpo: {nodo.cuerpo}")
        print(f"    Firma Digital: {nodo.firma_digital}")
        print("-" * 40)

print("\n=== Resumen de tiempos ===")
for tiempo in tiempos_ejecucion:
    print(tiempo)















