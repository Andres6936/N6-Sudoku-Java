package edu.jabs.sudoku.mundo;


public class Casilla
{

    // -------------------------------------------
    // Atributos
    // -------------------------------------------

    /**
     * Representa el valor actal de la casilla en el juego.
     */
    private int valorActual;

    /**
     * Representa el valor ingresado por el usuario en la casilla.
     */
    private int valorIngreso;

    /**
     * Indica si la casilla se muestra al principio del juego.
     */
    private boolean inicial;

    /**
     * Indica si la casilla debe mostrarse en rojo.
     */
    private boolean marcada;

    // -------------------------------------------
    // Constructor
    // -------------------------------------------

    /**
     * Construye la casilla con el valor dado por parámetro.
     * @param valor El numero correspondiente a la casilla. 1 <= value <= 9.
     */
    public Casilla( int valor )
    {
        valorActual = valor;
        valorIngreso = 0;
        inicial = false;
        marcada = false;
    }

    // -------------------------------------------
    // Métodos
    // -------------------------------------------

    /**
     * Indica si la casilla fue seleccionada como una casilla que se mostrara al principio del juego.
     * @return Se devuelve true si la casilla es una de esas casillas iniciales, false en caso contrario.
     */
    public boolean esInicial( )
    {
        return inicial;
    }

    /**
     * Devuelve el valor real de la casilla.
     * @return Devuelve el valor real de la casilla.
     */
    public int getValorReal( )
    {
        return valorActual;
    }

    /**
     * Indica si la casilla esta marcada para ser mostrada en rojo.
     * @return Se devuelve true si la casilla debe de ser vista en rojo, false en caso contrario.
     */
    public boolean esMarcada( )
    {
        return marcada;
    }

    /**
     * Devuelve el valor ingresado por el usuario.
     * @return Devuelve el valor ingresado por el usuario.
     */
    public int getValorIngresado( )
    {
        return valorIngreso;
    }

    /**
     * Cambia el estado del juego para que pueda mostrarse al inicio del juego.<br>
     * <b> post: </b> inicial = true <br>
     */
    public void setInicial( )
    {
        inicial = true;
    }

    /**
     * Marca una casilla para que esta pueda ser mostrada en rojo. <br>
     * <b> post: </b> marcada = true <br>
     */
    public void marcar( )
    {
        marcada = true;
    }

    /**
     * Cambia el valor ingesado por el usuario. <br>
     * <b> post: </b> valorIngreso = nValorIngreso <br>
     * @param nValorIngreso Un entero entre 1 y 9
     */
    public void setValorIngresado( int nValorIngreso )
    {
        valorIngreso = nValorIngreso;
    }

    /**
     * Remueve la casilla marcada para que esta no se muestre en rojo.<br>
     * <b> post: </b> marcada = false <br>
     */
    public void desmarcar( )
    {
        marcada = false;
    }

}