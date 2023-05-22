;; (load-file "Ejercitacion.clj")


;; (require '[clojure.string :as s])
;; (s/join " " '("hola" "mundo"))


;; 1. Definir la función tercer-angulo que reciba los valores de dos de los ángulos interiores de un triángulo y devuelva el valor del restante.
(defn tercer-angulo [angulo1 angulo2] (println (- 180 (+ angulo1 angulo2))))

;; 2. Definir la función segundos que reciba los cuatro valores (días, horas, minutos y segundos) del tiempo que dura un evento y devuelva el valor de ese tiempo expresado solamente en segundos.
(defn segundos [dias horas minutos segundos] (println (+ segundos (* 60 (+ minutos (* 60 (+ horas (* 24 dias))))))))

;; 3. Definir la función sig-mul-10 que reciba un número entero y devuelva el primer múltiplo de 10 que lo supere.
(defn sig-mul-10
  ([numero] (if (== (rem numero 10) 0)
                            (+ numero 10)
                            (sig-mul-10 (+ numero 1) 1)))
  ([numero x] (if (== (rem numero 10) 0)
              numero
              (sig-mul-10 (+ numero 1) 1))))

;; 4. Definir las funciones red, green, blue y alpha que reciban el valor numerico de un color de 32 bits y devuelvan, respectivamente, los valores de las componentes rojo, verde, azul y alfa (RGBA: red, green, blue, alpha) del mismo. 0x8a961422 ingresado en hexa (se caen por izquierda después del bit 64)
(defn alpha [bits] (double (rem bits 0x100))) ;; 22
(defn blue [bits] (double (rem (quot bits 0x100) 0x100))) ;; 8a9614 -> 14
(defn green [bits] (double (rem (quot bits 0x10000) 0x100))) ;; 8a96 -> 196
(defn red [bits] (double (quot bits 0x1000000))) ;; 8a

;; 5. Definir la función capicua? que reciba un número entero no negativo de hasta 5 dígitos y devuelva true si el número es capicúa; si no, false.
(defn capicua? [numero] (if (< numero 100000)
                          (if (> numero 0)
                            (= (clojure.string/reverse (str numero)) (str numero))
                            false)
                          false))

;; 6. Definir la función aprox-pi que reciba la cantidad de términos a considerar entre los paréntesis de la expresión 4 · (1 - 1/3 + 1/5 - 1/7 + ... + 1/n) y devuelva la correspondiente aproximación de π.000000000
(defn cuentaPrevia [valorInicial valorFinal] (if (>= valorInicial valorFinal)
                                               0
                                               (+ (if (odd? valorInicial)
                                                    (* -1 (/ 1 (+ 1 (* 2 valorInicial))))
                                                    (/ 1 (+ 1 (* 2 valorInicial))))
                                                  (cuentaPrevia (+ valorInicial 1) valorFinal))))
(defn aprox-pi [cantidadTerminos] (double (* 4 (+ 1 (cuentaPrevia 1 cantidadTerminos)))))

(defn aprox-piAlternativo
  ([cantidadTerminos] ((double (* 4 (+ 1 (aprox-piAlternativo 1 cantidadTerminos))))))
  ([valorInicial valorFinal] (if (>= valorInicial valorFinal)
                               0
                               (+ (if (odd? valorInicial)
                                    (* -1 (/ 1 (+ 1 (* 2 valorInicial))))
                                    (/ 1 (+ 1 (* 2 valorInicial))))
                                  (aprox-piAlternativo (+ valorInicial 1) valorFinal))))) ;; con esta alternativa no se crea mas de una funcion


;; 7. Definir la función invertir que reciba un número entero no negativo y lo devuelva espejado.
(defn invertir [numero] (if (> numero 0)
                          (clojure.string/reverse (str numero))
                          false))

;; 8. Definir la función nth-fibo que reciba un número entero no negativo y devuelva el correspondiente término de la sucesión de Fibonacci.
(defn fibo [fiboValor fiboValorAnterior valorInicial valorFinal] (if (>= valorInicial valorFinal)
                                                                   fiboValorAnterior
                                                                   (recur (+ fiboValor fiboValorAnterior) fiboValor (+ valorInicial 1) valorFinal) ;; recur es una funcion de recurcion optimizada que no crea nuevos marcos
                                                                   ))
(defn nth-fibo [numeroTermino] (fibo 1 1 0 numeroTermino))
(defn nth-fiboAlternativo
  ([numeroTermino] (nth-fiboAlternativo 1 1 0 numeroTermino))
  ([fiboValor fiboValorAnterior valorInicial valorFinal] (if (>= valorInicial valorFinal)
                                                           fiboValorAnterior
                                                           (recur (+ fiboValor fiboValorAnterior) fiboValor (+ valorInicial 1) valorFinal) ;; recur es una funcion de recurcion optimizada que no crea nuevos marcos
                                                           )))

;; 9. Definir la función cant-dig que reciba un número entero y devuelva la cantidad de dígitos que este tiene.
(defn cant-dig [numero] (+ (if (< numero 10)
                             1
                             (cant-dig (/ numero 10)))))

;; 10. Definir la función pot? que reciba dos números naturales y devuelva true si el primero es potencia del segundo; si no, false.
(defn miPot [primero segundo segundoOriginal] (if (< primero segundo)
                                                false
                                                (if (= primero segundo)
                                                  true
                                                  (recur primero (* segundo segundoOriginal) segundoOriginal))))
(defn pot? [primero segundo] (miPot primero segundo segundo))
(defn pot?Alternativo
  ([primero segundo] (pot?Alternativo primero segundo segundo))
  ([primero segundo segundoOriginal] (if (< primero segundo)
                                       false
                                       (if (= primero segundo)
                                         true
                                         (pot?Alternativo primero (* segundo segundoOriginal) segundoOriginal))))) ;; con esta alternativa no se crea mas de una funcion

;; 11. Definir la función digs que reciba un número y devuelva una lista con sus dígitos.
(defn digs
  ([numero] (digs numero (list)))
  ([numero tachito] (if (< numero 10)
                      (conj tachito numero)
                      (digs (quot numero 10) (conj tachito (rem numero 10))))))
(defn digsAlternativo [numero] (map (fn [x] (Integer/parseInt x)) (map str (map char (str numero))))) ;; version alternativa con funciones existentes 
;; tener en cuenta que no se puede hacer un mapeo de numeros

;; 12. Definir la función repartir que, llamada sin argumentos, devuelva la cadena "Uno para vos, uno para mí". De lo contrario, se devolverá una lista, en la que habrá una cadena "Uno para X, uno para mí" por cada argumento X.
(defn repartir
  ([] "Uno para vos, uno para mí")
  ([& lista] (map (partial printf "Uno para %s, uno para mi\n") lista)))
;; 13. Definir una función para producir una lista con los elementos en las posiciones pares de dos listas dadas. (paresDeListas '(1 2 3) '(a b c))
(defn paresDeListas [& listas] (apply concat (map (fn [x] (take-nth 2 x)) listas)))

;; 14. La transcripción es el proceso en el que la secuencia de ADN de un gen se copia (transcribe) para hacer una molécula de ARN. La cadena de ARN transcrita se forma reemplazando cada nucleótido del ADN por su complemento de ARN: G → C, C → G, T → A y, por último, A → U. Definir la función adn2arn que reciba una cadena de ADN y la devuelva transcrita en ARN. (adn2arn "GCTA")
(defn adn2arn [ADN] (clojure.string/replace (clojure.string/replace (clojure.string/replace (clojure.string/replace (clojure.string/replace ADN #"G" " ") #"C" "G") #" " "C") #"A" "U") #"T" "A"))

;; 15. Definir una función para eliminar las ocurrencias de un dato escalar en una lista (a todo nivel). (borrarTodo 'b '(a b c))
(defn borrarTodo [dato lista] (cond
                                (empty? lista) lista
                                (not (seq? (first lista)))
                                (if (not (= dato (first lista)))
                                  (conj (borrarTodo dato (rest lista)) (first lista))
                                  (borrarTodo dato (rest lista))) ;; caso en que sea atómico
                                :default (conj (borrarTodo dato (rest lista)) (borrarTodo dato (first lista)))))

;; 16. Definir una función para obtener el último símbolo de una lista (a todo nivel).
(defn ultimoDeTodos [lista] (cond
                              (empty? lista) lista ;; empty
                              (not (seq? (last lista))) (last lista) ;; caso atomico
                              :default (ultimoDeTodos (last lista))))

;; 17. Definir una función para obtener el elemento central de una lista.
(defn elementoCentral [lista] (get (vec (flatten lista)) (+ (quot (count lista) 2) 1))) ;; division a entero

;; 18. Definir una función para eliminar los elementos repetidos de una lista simple.
(defn eliminarRepetidos [secuencia] (cond
                                      (list? secuencia) (into () (into #{} secuencia))
                                      (vector? secuencia) (into [] (into #{} secuencia))
                                      :default (into #{} secuencia))
  )

;; 19. Definir una función para ordenar una lista de listas por longitud creciente. '((1) () (2 3 4))
(defn ordenarPorLongitudCreciente [lista] (sort-by (fn [x] (count x)) lista))

;; 20. Un ISBN-10 es válido si sus 10 dígitos x1, x2, x3, ... x10 cumplen lo siguiente:
;; (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9 * 2 + x10 * 1) mod 11 == 0
;; Un ISBN-10 está dividido en cuatro partes: el código de país o lengua de origen (de 1 a 5 dígitos), el editor, el número del artículo y un dígito de control. Opcionalmente, estas cuatro partes pueden estar separadas mediante espacios en blanco o guiones. El dígito de control puede valer X que representa el valor 10. Por ejemplo, 3-598-21507-X es un ISBN-10 válido. Escribir la función isbn-10? que devuelve true si la cadena recibida es un ISBN-10 válido; si no, false.
(defn isbn-10? [cadena] (cond
                          (= (re-seq #"\d+[- ]{0,1}+\d{3}+[- ]{0,1}+\d{5}+[- ]{0,1}+[\dxX]" cadena) nil) false
                          (not= (rem (reduce + (map #(* (first %) (second %)) (map list (reverse (range 1 11)) (replace '{\0 0, \1 1, \2 2, \3 3, \4 4, \5 5, \6 6, \7 7, \8 8 , \9 9, \x 10, \X 10} (seq (clojure.string/replace (clojure.string/replace cadena #" " "") #"-" "")))))) 11) 0) false
                          :default true))

(defn isbn-10?V2 [cadena] (cond
                            (= (re-seq #"\d+[- ]{0,1}+\d{3}+[- ]{0,1}+\d{5}+[- ]{0,1}+[\dxX]" cadena) nil) false
                            (not= (as-> cadena v
                                    (clojure.string/replace v #" " "")
                                    (clojure.string/replace v #"-" "")
                                    (seq v)
                                    (replace '{\0 0, \1 1, \2 2, \3 3, \4 4, \5 5, \6 6, \7 7, \8 8 , \9 9, \x 10, \X 10} v)
                                    (map list (reverse (range 1 11)) v)
                                    (map #(* (first %) (second %)) v)
                                    (reduce + v)
                                    (rem v 11)) 0) false
                            :default true))

;; 21. Definir una función para obtener la matriz triangular superior (incluyendo la diagonal principal) de una matriz cuadrada que está representada como una lista de listas.
(defn matriz-triangular-superior [] )