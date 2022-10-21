; Aluno: Alan Renato Bunese
; Disciplina: Programação Funcional
; Professor: Frank Alcantara

; 1.
; Na aula disponível em: https://replit.com/@frankalcantara/ClojureAula2?v=1 foram
; destacadas diversas funções (expressões), como funções de primeira ordem, disponíveis
; em Clojure. Sua primeira tarefa será descrever cada uma destas funções e apresentar
; dois exemplos de uso de cada uma delas. Lembre-se os exemplos precisam ser utilizados
; de forma que o resultado da função possa ser visto no terminal.

; A definição de função de primeira ordem são funções que recebem parâmetros com funções
; ou retornam funções. No documento tem um comentário "funções de primeira ordem range,
; map, inc, filter, odd, into". Mas algumas destas funções apresentadas não são de
; primeira ordem. Então incluí apenas aqueles que realmente são: map, filter. As
; demais funções apresentadas inc, odd e into não recebem funções como entrada e nem
; retornam funções, ou seja, não são de primeira ordem.

; - Map
; Recebe como entrada uma função e uma lista. Retornará uma nova lista de mesmo tamanho
; da lista de entrada, porém com a função aplicada em cada elemento.
(println "map: entrada: #(+ 5 %) [5 10 15 20 25 30]; resultado: " (map #(+ 5 %) [5 10 15 20 25 30]))
(println "map: entrada: #(mod 7 %) [1 2 3 4 5 6 7 8 9 10]; resultado: " (map #(mod 7 %) [1 2 3 4 5 6 7 8 9 10]))

; - Filter
; Recebe como entrada uma função e uma lista. Retornará uma nova lista apenas com os
; elementos em que a função retornou True.
(println "filter: entrada: odd? [1 2 3 4 5 6 7 8 9 10]; resultado: " (filter odd? [1 2 3 4 5 6 7 8 9 10]))
(println "filter: entrada: #(= (mod % 7) 0) [1 2 3 4 5 6 7 8 9 10]; resultado: " (filter #(= (mod % 7) 0) [1 2 3 4 5 6 7 8 9 10]))

; 2.
; Utilizando a linaguagem Clojure, crie uma função chamada ehPrimo que receba um inteiro
; e devolva True caso este inteiro seja verdadeiro e False caso contrário.
(defn divs [n]
 (->> (range 1 (+ n 1))
      (filter #(= (mod n %) 0))
 )
)

(defn ehPrimo [n]
 (= (divs n) [1 n])
)

; Testando ehPrimo.
(println "ehPrimo: entrada: 1; resultado: " (ehPrimo 1))
(println "ehPrimo: entrada: 2; resultado: " (ehPrimo 2))
(println "ehPrimo: entrada: 35; resultado: " (ehPrimo 35))
(println "ehPrimo: entrada: 17; resultado: " (ehPrimo 17))

; 3.
; Utilizando a linguagem Clojure, crie uma função chamada fatoresPrimos que receba um
; inteiro e devolva uma lista dos seus fatores primos. Decomposição em fatores primos
; é uma tarefa fundamental de aritimética.
(defn- fatoresPrimos
 ([n]
  (fatoresPrimos n [])
 )
 ([n lst]
  (if (= (count (divs n)) 1) ; Se temos apenas o 1 na lista
   lst ; Retorna a própria lista de divisores
   (fatoresPrimos (/ n (second (divs n))) (concat lst [(second (divs n))]) )) ; Caso contrário...
   ; Aqui chamamos novamente recursivo, dividindo N pelo 2 elemento - que será o nosso divisor mínimo.
   ; Graças a matemática, esse divisor será sempre primo. Adicionamos esse divisor a segunda lista também.
 )
)

; Testando fatores primos.
(println "fatoresPrimos: entrada: 35; resultado: " (fatoresPrimos 35))
(println "fatoresPrimos: entrada: 78; resultado: " (fatoresPrimos 78))

; 4.
; Utilizando a linguagem Clojure, crie uma função chamada todosPrimos que receba dois
; valores inteiros e devolve todos os números primos que existam entre estes dois
; valores.
(defn todosPrimos [s e]
  (->> (range s (+ e 1))
       (filter #(ehPrimo %))
  )
)

; Testando todos os primos.
(println "todosPrimos: entrada: 5 15; resultado: " (todosPrimos 5 15))
(println "todosPrimos: entrada: 55 70; resultado: " (todosPrimos 55 70))
