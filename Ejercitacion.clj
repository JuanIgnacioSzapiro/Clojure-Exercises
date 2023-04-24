;; (load-file "Ejercitacion.clj")

;; 1. Definir la función tercer-angulo que reciba los valores de dos de los ángulos interiores de un triángulo y devuelva el valor del restante.
(defn tercer-angulo [angulo1 angulo2] (println (- 180 (+ angulo1 angulo2))))

;; 2. Definir la función segundos que reciba los cuatro valores (días, horas, minutos y segundos) del tiempo que dura un evento y devuelva el valor de ese tiempo expresado solamente en segundos.
(defn segundos [dias horas minutos segundos] (println (+ segundos (* 60 (+ minutos (* 60 (+ horas (* 24 dias))))))))

;; 3. Definir la función sig-mul-10 que reciba un número entero y devuelva el primer múltiplo de 10 que lo supere.
(defn sig-mul-10 [numero] (if (== (rem numero 10) 0)
                            numero
                            (sig-mul-10 (+ numero 1))
                            ))

;; 4. Definir las funciones red, green, blue y alpha que reciban el valor numerico de un color de 32 bits y devuelvan, respectivamente, los valores de las componentes rojo, verde, azul y alfa (RGBA: red, green, blue, alpha) del mismo. 0x8a961422 ingresado en hexa (se caen por izquierda después del bit 64)
(defn alpha [bits] (double (rem bits 0x100))) ;; 22
(defn blue [bits] (double (rem (quot bits 0x100) 0x100))) ;; 8a9614 -> 14
(defn green [bits] (double (rem (quot bits 0x10000) 0x100))) ;; 8a96 -> 196
(defn red [bits] (double (quot bits 0x1000000))) ;; 8a

;; 5. Definir la función capicua? que reciba un número entero no negativo de hasta 5 dígitos y devuelva true si el número es capicúa; si no, false.
(defn capicua? [numero] (if (< numero 100000)
                          (if (> numero 0)
                            (= (clojure.string/reverse (str numero)) (str numero))
                            false
                            )
                          false
                          ))

;; 6. Definir la función aprox-pi que reciba la cantidad de términos a considerar entre los paréntesis de la expresión 4 · (1 - 1/3 + 1/5 - 1/7 + ... + 1/n) y devuelva la correspondiente aproximación de π.000000000
(defn cuentaPrevia [valorInicial valorFinal] (if (>= valorInicial valorFinal) 
                                               0
                                               (+ (if (odd? valorInicial)
                                                    (* -1 (/ 1 (+ 1 (* 2 valorInicial))))
                                                    (/ 1 (+ 1 (* 2 valorInicial))))
                                                  (cuentaPrevia (+ valorInicial 1) valorFinal)
                                                  )
                                               ))
(defn aprox-pi [cantidadTerminos] (double (* 4 (+ 1 (cuentaPrevia 1 cantidadTerminos)))))

(defn aprox-piAlternativo 
  ([cantidadTerminos] ((double (* 4 (+ 1 (recur 1 cantidadTerminos))))))
  ([valorInicial valorFinal] (if (>= valorInicial valorFinal) 
                               0 
                               (+ (if (odd? valorInicial) 
                                    (* -1 (/ 1 (+ 1 (* 2 valorInicial)))) 
                                    (/ 1 (+ 1 (* 2 valorInicial)))) 
                                  (recur (+ valorInicial 1) valorFinal)
                                  )
                               ))
  ) ;; con esta alternativa no se crea mas de una funcion


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
  ([numeroTermino] (recur 1 1 0 numeroTermino))
  ([fiboValor fiboValorAnterior valorInicial valorFinal] (if (>= valorInicial valorFinal) 
                                                           fiboValorAnterior 
                                                           (recur (+ fiboValor fiboValorAnterior) fiboValor (+ valorInicial 1) valorFinal) ;; recur es una funcion de recurcion optimizada que no crea nuevos marcos
                                                           ))
  )

;; 9. Definir la función cant-dig que reciba un número entero y devuelva la cantidad de dígitos que este tiene.
(defn cant-dig [numero] (+ (if (< numero 10) 
                             1
                             (cant-dig (/ numero 10))
                             )))

;; 10. Definir la función pot? que reciba dos números naturales y devuelva true si el primero es potencia del segundo; si no, false.
(defn miPot [primero segundo segundoOriginal] (if (< primero segundo)
                                                false
                                                (if (= primero segundo)
                                                  true
                                                  (recur primero (* segundo segundoOriginal) segundoOriginal)
                                                  )
                                                ))
(defn pot? [primero segundo] (miPot primero segundo segundo))
(defn pot?Alternativo 
  ([primero segundo] (recur primero segundo segundo))
  ([primero segundo segundoOriginal] (if (< primero segundo)
                                       false
                                       (if (= primero segundo)
                                         true
                                         (recur primero (* segundo segundoOriginal) segundoOriginal))
                                       ))) ;; con esta alternativa no se crea mas de una funcion

;; 11. Definir la función digs que reciba un número y devuelva una lista con sus dígitos.
(defn digs 
  ([numero] (digs numero (list)))
  ([numero tachito] (if (< numero 10)
                      (conj tachito numero)
                      (recur (quot numero 10) (conj tachito (rem numero 10)))
                      )
   )
  )
(defn digsAlternativo [numero] (map (fn [x] (Integer/parseInt x)) (map str (map char (str numero))))) ;; version alternativa con funciones existentes 
;; tener en cuenta que no se puede hacer un mapeo de numeros