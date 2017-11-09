package edu.jabs.sudoku.mundo;


import java.util.Properties;

public class Sudoku
{

    // -------------------------------------------
    // Constantes
    // -------------------------------------------

    /**
     * Representa el nombre para el archivos properties.
     */
    private static final String KEY_PROPERTIES = "sudoku.row";

    /**
     * Representa el numero de filas del tablero.
     */
    public static final int NUMERO_FILAS = 9;

    /**
     * Representa el numero de columnas del tablero.
     */
    public static final int NUMERO_COLUMNAS = 9;

    /**
     * Representa el numero de �reas del tablero.
     */
    public static final int NUMERO_AREAS = 9;

    /**
     * Representa el numero de casillas que ser�n mostradas al comienzo del juego. <br>
     * El valor m�ximo debera de ser 9 y el valor m�nimo debera de ser 1.
     */
    public static final int INICIAL_CASILLAS_NUMERO = 3;

    // -------------------------------------------
    // Atributos
    // -------------------------------------------

    /**
     * Informa si el juego esta siendo jugado.
     */
    private boolean jugando;

    /**
     * La matriz de casillas del tablero.
     */
    private Casilla[][] tablero;

    // -------------------------------------------
    // Constructor
    // -------------------------------------------

    public Sudoku( )
    {
        tablero = new Casilla[NUMERO_FILAS][NUMERO_COLUMNAS];
    }

    // -------------------------------------------
    // M�todos
    // -------------------------------------------

    /**
     * Inicializa el tablero con los valores que estan representados en el archivo properties. <br>
     * <b> post: </b> talbero != null. <br>
     * @param properties Objecto con la soluci�n del juego. properties != null.
     * @throws Exception Salta la exception si ay problemas cargando el archivo.
     */
    public void cargarTablero( Properties properties ) throws Exception
    {
        for( int i = 0; i < NUMERO_COLUMNAS; i++ )
        {
            String fila = ( String )properties.get( KEY_PROPERTIES + ( i + 1 ) );
            if( fila != null && fila.length( ) == NUMERO_FILAS )
                cargarFila( fila, i );
            else
                throw new Exception( "El archivo no tine el formato esperado" );
        }
    }

    /**
     * Si el juego comienza, marca las casillas seleccionadas para ser mostradas. <br>
     * <b> post: </b> jugando = true. <br>
     */
    public void comenzarJuego( )
    {
        jugando = true;
        iniciarMarcas( );
    }

    /**
     * Verifica que se cumplan las reglas del juego. <br>
     * <b> pre: </b> tablero != null y jugando = true <br>
     * @param elTablero Tablero con los valores ingresados por el usuario.
     * @return true si el juego enviado por p�rametro es correcto, false en caso contrario.
     */
    public boolean validarTablero( int[][] elTablero )
    {
        ingresarValoresUsuario( elTablero );
        boolean areas = validarAreas( );
        boolean filas = validarFilas( );
        boolean columnas = validarColumnas( );
        boolean ceros = validarRelleno( );
        return areas && filas && columnas && ceros;
    }

    /**
     * Rellena el tablero con los valores ingresado por el usuario. <br>
     * <b> pre: </b> tablero != null y jugando = true. <br>
     * @param elTablero Tablero con los valores ingresado por el usuario. elTablero != null.
     */
    public void ingresarValoresUsuario( int[][] elTablero )
    {
        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < NUMERO_COLUMNAS; j++ )
            {
                tablero[ i ][ j ].setValorIngresado( elTablero[ i ][ j ] );
            }
        }
    }

    /**
     * Limpia el tablero, recontruyendelo. <br>
     * <b> post: </b> tablero != null <br>
     */
    public void limpar( )
    {
        tablero = new Casilla[NUMERO_FILAS][NUMERO_COLUMNAS];
    }

    /**
     * Usado para indicar el fin del juego. <br>
     * <b> post: </b> jugando = false <br>
     */
    public void finJuego( )
    {
        jugando = false;
    }

    /**
     * Informa si el juego esta activo o no (si el juego no ha finalizado).
     * @return true si el juego no ha finalizado todavia, false en caso contrario.
     */
    public boolean juegoActivo( )
    {
        return jugando;
    }

    /**
     * Construye una fila de casillas desde un String leido desde un archivo properties. <br>
     * <b> pre: </b> casillas != null. <br>
     * @param fila El String leido desde el archivo properties. fila != null y debe de tener el mismo largo que NUMERO_FILAS.
     * @param numFila La fila en la matriz donde se va a poner la cadena. 0 <= numFila <= NUMERO_FILA.
     * @throws Exception La exception es lanzada si hay problemas cargando el archivo.
     */
    private void cargarFila( String fila, int numFila ) throws Exception
    {
        for( int i = 0; i < fila.length( ); i++ )
        {
            int valor;
            try
            {
                valor = Integer.parseInt( fila.substring( i, i + 1 ) );
            }
            catch( NumberFormatException e )
            {
                throw new Exception( "El archivo properties no tiene el formato requerido" );
            }

            Casilla casilla = new Casilla( valor );
            tablero[ numFila ][ i ] = casilla;
        }
    }

    /**
     * Escoge y marca las casillas que se van a mostrar cuando se carga el juego. <br>
     * <b> pre: </b> tablero != null. <br>
     */
    private void iniciarMarcas( )
    {
        for( int i = 0; i < NUMERO_AREAS; i++ )
        {
            Casilla[] casillas = getCasillasArea( i + 1 );
            int contador = 0;

            while( contador < INICIAL_CASILLAS_NUMERO )
            {
                int valor = generarNumeroRandomConRango( casillas.length );
                Casilla casilla = casillas[ valor ];

                if( !casilla.esInicial( ) )
                {
                    contador++;
                    casilla.setInicial( );
                }
            }

        }
    }

    /**
     * Genera un numero random entre 0 y (Rango - 1).
     * @param Rango Tama�o del rango.
     * @return Entero positivo entre 0 y (Rango - 1)
     */
    private int generarNumeroRandomConRango( int Rango )
    {
        return ( int ) ( Math.random( ) * Rango );
    }

    /**
     * Devuelve un array con las casillas que se encuentran en el area. <br>
     * <b> pre: </b> tablero != null. <br>
     * @param area El area de las casillas deseadas. 1 <= area <= 9
     * @return Un array es devuelto con las casillas del area respectiva.
     */
    private Casilla[] getCasillasArea( int area )
    {
        Casilla[] casillas = new Casilla[NUMERO_FILAS];

        int ComienzoFila = ( ( area - 1 ) / 3 ) * 3;
        int ComienzoColumna = ( ( area - 1 ) % 3 ) * 3;
        int contador = 0;

        for( int i = ComienzoFila; i < ComienzoFila + 3; i++ )
        {
            for( int j = ComienzoColumna; j < ComienzoColumna + 3; j++ )
            {
                casillas[ contador ] = tablero[ i ][ j ];
                contador++;
            }
        }

        return casillas;
    }

    /**
     * Verifica que se haya completado las filas del tablero sin haber ningun n�mero repetido.<br>
     * <b> pre: </b> tablero != null y cada una de las posiciones del tablero hayan sido inicializadas. <br>
     * @return true si las reglas del juego se cumplen, false en caso contrario.
     */
    private boolean validarFilas( )
    {
        boolean respuesta = true;
        for( int i = 0; i < NUMERO_COLUMNAS; i++ )
        {
            Casilla[] casillas = getFila( i );
            if( EstanRepetidas( casillas ) )
            {
                marcar( casillas );
                respuesta = false;
            }
        }

        return respuesta;
    }

    /**
     * Verifica que se haya completado la columnas del tablero sin haber ningun n�mero repetido. <br>
     * <b> pre: </b> tablero != null y cada una de las posicion del tablero hayan sido inicializadas. <br>
     * @return true si las reglas del juego se cumplen, false en caso contrario.
     */
    private boolean validarColumnas( )
    {
        boolean respuesta = true;
        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            Casilla[] casillas = tablero[ i ];
            if( EstanRepetidas( casillas ) )
            {
                marcar( casillas );
                respuesta = false;
            }
        }

        return respuesta;
    }

    /**
     * Verifica que se hayan completado las areas del tablero sin haber ningun n�mero repetido. <br>
     * <b> pre: </b> tablero != null y cada una de las posiciones del tablero hayan sido inicializadas. <br>
     * @return true si las reglas del juego se cumplen, false en caso contrario.
     */
    private boolean validarAreas( )
    {
        boolean respuesta = true;
        for( int i = 0; i < NUMERO_AREAS; i++ )
        {
            Casilla[] casillas = getCasillasArea( i + 1 );
            if( EstanRepetidas( casillas ) )
            {
                marcar( casillas );
                respuesta = false;
            }
        }

        return respuesta;
    }

    /**
     * Marca las casillas para que se muestren como incorrectas. <br>
     * <b> post: </b> Las casillas del array son marcadas como incorrectas. <br>
     * @param casillas El array de casillas que ser�n marcadas. casillas != null.
     */
    private void marcar( Casilla[] casillas )
    {
        for( int i = 0; i < casillas.length; i++ )
        {
            casillas[ i ].marcar( );
        }
    }

    /**
     * Devuelve el numero de casillas en un fila. <br>
     * <b> pre: </b> casillas != null. <br>
     * @param numFila El numero de la fila que obtendremos. 0 <= numFila < NUMERO_COLUMNAS
     * @return Un array de casillas que se obtendran de la fila.
     */
    private Casilla[] getFila( int numFila )
    {
        Casilla[] casillas = new Casilla[NUMERO_COLUMNAS];
        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            casillas[ i ] = tablero[ i ][ numFila ];
        }

        return casillas;
    }

    /**
     * Método que evalua si hay n�meros repetidos en un array de casillas. <br>
     * @param casillas que ser�n evaludas. casillas != null y cada posicion del tablero debe de estar inicializado <br>
     * @return true si no hay n�mero repetidos en el array, false en caso contrario.
     */
    private boolean EstanRepetidas( Casilla[] casillas )
    {
        for( int i = 0; i < casillas.length; i++ )
        {
            Casilla casilla = casillas[ i ];
            for( int j = 0; casilla != null && j < casillas.length; j++ )
            {
                Casilla casilla2 = casillas[ j ];
                if( casilla.getValorIngresado( ) == casilla2.getValorIngresado( ) && i != j && casilla.getValorIngresado( ) != 0 )
                    return true;
            }
        }

        return false;
    }

    /**
     * Remueve las casillas marcadas con error. <br>
     * <b> pre: </b> casillas != null y cada una de las posiciones del tablero deben de estar inicializadas. <br>
     * <b> post: </b> Cada casilla de la matriz es desmarcada.
     */
    public void desmarcarCasillas( )
    {
        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < NUMERO_COLUMNAS; j++ )
            {
                tablero[ i ][ j ].desmarcar( );
            }
        }
    }

    /**
     * Verifica que no hayan casillas vacias en el tablero. <br>
     * <b> pre: </b> casillas != null y cada una de las posiciones del tablero deben de estar inicializadas. <br>
     * @return true si no hay casillas vacias, false en caso contrario.
     */
    private boolean validarRelleno( )
    {
        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < NUMERO_COLUMNAS; j++ )
            {
                Casilla casilla = tablero[ i ][ j ];
                if( casilla == null || casilla.getValorIngresado( ) == 0 )
                    return false;
            }
        }

        return true;
    }

    /**
     * Método que devuelve el tablero de juego.
     * @return Devuelve las casillas del juego.
     */
    public Casilla[][] getTablero( )
    {
        return tablero;
    }

    // ------------------------------------------
    // Puntos de Extensi�n
    // ------------------------------------------

    /**
     * Método de extensi�n 1.
     * @return Respuesta 1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método de extensi�n 2.
     * @return Respuesta 2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}