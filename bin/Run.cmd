@echo off

REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad Santiago de Cali (Santiago de Cali - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación
REM Licenciado bajo el esquema Academic Free License version 2.1
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: N1-Sudoku-Java
REM Autor: Joan Andrés Buriticá Salazar
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion del programa
REM ---------------------------------------------------------

cd..
java -classpath ./lib/sudoku.jar edu.jabs.sudoku.interfaz.InterfazSudoku
cd bin