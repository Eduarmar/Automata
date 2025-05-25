const crypto = require("crypto");
const { performance } = require("perf_hooks");

console.time("Tiempo de ejecucion");

class Nodo {
    constructor(partida, cuerpo) {
        this.partida = partida;
        this.cuerpo = cuerpo;
        this.firmaDigital = this.calcularFirma();
    }

    calcularFirma() {
        const contenido = `${this.partida} ${this.cuerpo.join(" ")}`;
        return crypto.createHash("sha256").update(contenido).digest("hex");
    }
}

function generarLista(n, k) {
    let listaNodos = [];

    let tiempoActual = new Date().toLocaleString("es-VE", { timeZone: "America/Caracas"});
    let primeraFirma = crypto.createHash("sha256").update(`${tiempoActual}`).digest("hex");
    let contenidoInicial = `${tiempoActual} ${primeraFirma}`;

    for(let i=0; i<n; i++){
        let partida = (i == 0)? contenidoInicial : listaNodos[i-1].firmaDigital;
        let cuerpo = Array.from({ length: k}, () => Math.floor(Math.random() * 100000) + 1);
        let nodo = new Nodo(partida, cuerpo);
        listaNodos.push(nodo);
    }
    return listaNodos;
}


const nValues = [3, 10, 200];
const kValues = [4, 200, 10];
const tiemposEjecucion = [];


nValues.forEach((n, index) => {
    let k = kValues[index];

    let inicio = performance.now();  
    let nodos = generarLista(n, k);
    let fin = performance.now(); 

    let tiempo = (fin - inicio).toFixed(5);
    console.log(`Tiempo para n=${n}, k=${k} = ${tiempo}`);
    tiemposEjecucion.push(`Tiempo ${String.fromCharCode(97 + index)}: ${tiempo} ms`);

    console.log(`\n=== Prueba con n=${n}, k=${k} ===`);
    nodos.forEach((nodo, i) => {
        console.log(`Nodo ${i + 1}:`);
        console.log(`  Partida: ${nodo.partida}`);
        console.log(`  Cuerpo: ${nodo.cuerpo}`);
        console.log(`  Firma Digital: ${nodo.firmaDigital}`);
        console.log("-".repeat(40));
    });
});

console.log("\n=== Resumen de tiempos ===");
tiemposEjecucion.forEach(tiempo => console.log(tiempo));
