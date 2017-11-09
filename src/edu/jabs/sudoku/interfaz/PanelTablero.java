package edu.jabs.sudoku.interfaz;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import edu.jabs.sudoku.mundo.Casilla;
import edu.jabs.sudoku.mundo.Sudoku;

public class PanelTablero extends JPanel
{

    // -------------------------------------------
    // Constantes
    // -------------------------------------------

    /**
     * Constantes que representa el numero de celdas de un área.
     */
    public static final int NUM_CELDAS_AREA = 9;

    // -------------------------------------------
    // Atributos
    // -------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazSudoku principal;

    /**
     * Representa el color de la celda cuando esta esta completa.
     */
    private Color colorResuelto;

    /**
     * Representa el color de la celda cuando hay un error en ella;
     */
    private Color colorError;

    /**
     * Representa el color de la celda cuando esta esta vacia.
     */
    private Color colorVacio;

    // -------------------------------------------
    // Atributos de la ventana
    // -------------------------------------------

    /**
     * El tablero de celdas.
     */
    private JTextField[][] celdas;

    // -------------------------------------------
    // Constructor
    // -------------------------------------------

    public PanelTablero( InterfazSudoku ventanaPrincipal )
    {
        // Las propiedades del panel son inicializadas.
        principal = ventanaPrincipal;
        setLayout( new GridLayout( Sudoku.NUMERO_FILAS / 3, Sudoku.NUMERO_COLUMNAS / 3 ) );
        setBackground( Color.BLACK );

        // Colores
        colorResuelto = new Color( 87, 150, 38 );
        colorError = new Color( 200, 1, 1 );
        colorVacio = new Color( 229, 132, 15 );

        celdas = new JTextField[Sudoku.NUMERO_FILAS][Sudoku.NUMERO_COLUMNAS];

        // Áreas del Panel son construidas.
        JPanel[][] panelAreas = new JPanel[Sudoku.NUMERO_AREAS / 3][Sudoku.NUMERO_AREAS / 3];
        Border border = new BevelBorder( BevelBorder.RAISED );

        for( int i = 0; i < Sudoku.NUMERO_AREAS / 3; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_AREAS / 3; j++ )
            {
                panelAreas[ i ][ j ] = new JPanel( );
                panelAreas[ i ][ j ].setLayout( new GridLayout( NUM_CELDAS_AREA / 3, NUM_CELDAS_AREA / 3 ) );
                panelAreas[ i ][ j ].setBorder( border );
                add( panelAreas[ i ][ j ] );
            }
        }

        // Los TextField son añadidos a los paneles.
        for( int i = 0; i < Sudoku.NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                celdas[ i ][ j ] = new JTextField( "" );
                celdas[ i ][ j ].setHorizontalAlignment( JTextField.CENTER );
                Font tipoFuente = celdas[ i ][ j ].getFont( );
                Font nuevoTipoFuente = new Font( tipoFuente.getName( ), Font.PLAIN, tipoFuente.getSize( ) + 3 );
                celdas[ i ][ j ].setFont( nuevoTipoFuente );
                celdas[ i ][ j ].setPreferredSize( new Dimension( 50, 50 ) );
                celdas[ i ][ j ].setEditable( false );
                panelAreas[ i / 3 ][ j / 3 ].add( celdas[ i ][ j ] );
            }
        }

    }

    // ------------------------------------------
    // Método
    // ------------------------------------------

    /**
     * Repinta los TextField
     */
    public void actualizarTablero( )
    {
        Casilla[][] celdasTablero = principal.getCeldasTablero( );

        for( int i = 0; i < Sudoku.NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                // El textField es añadido con una configuración por default.
                celdas[ i ][ j ].setText( "" );
                celdas[ i ][ j ].setEditable( true );
                celdas[ i ][ j ].setBackground( Color.WHITE );
                Font tipoFuente = celdas[ i ][ j ].getFont( );
                Font nuevoTipoFuente = new Font( tipoFuente.getName( ), Font.PLAIN, tipoFuente.getSize( ) );
                celdas[ i ][ j ].setFont( nuevoTipoFuente );

                // La apariencia y la sensación se modifica de acuerdo con la información.
                if( celdasTablero[ i ][ j ].esInicial( ) && celdasTablero[ i ][ j ].esMarcada( ) )
                {
                    celdas[ i ][ j ].setBackground( colorError );
                    celdas[ i ][ j ].setEditable( false );
                    celdas[ i ][ j ].setText( "" + celdasTablero[ i ][ j ].getValorReal( ) );
                    Font font = celdas[ i ][ j ].getFont( );
                    Font nuevaFont = new Font( font.getName( ), Font.BOLD, font.getSize( ) );
                    celdas[ i ][ j ].setFont( nuevaFont );
                }
                else if( celdasTablero[ i ][ j ].esInicial( ) )
                {
                    celdas[ i ][ j ].setText( "" + celdasTablero[ i ][ j ].getValorReal( ) );
                    Font font = celdas[ i ][ j ].getFont( );
                    Font nuevaFont = new Font( font.getName( ), Font.BOLD, font.getSize( ) );
                    celdas[ i ][ j ].setFont( nuevaFont );
                    celdas[ i ][ j ].setEditable( false );
                    celdas[ i ][ j ].setBackground( Color.WHITE );
                }
                else if( celdasTablero[ i ][ j ].esMarcada( ) )
                {
                    celdas[ i ][ j ].setBackground( colorError );
                    if( celdasTablero[ i ][ j ].getValorIngresado( ) != 0 )
                    {
                        celdas[ i ][ j ].setText( "" + celdasTablero[ i ][ j ].getValorReal( ) );
                    }
                    else
                    {
                        celdas[ i ][ j ].setText( "" );
                    }
                }
                else if( celdasTablero[ i ][ j ].getValorIngresado( ) == 0 )
                {
                    celdas[ i ][ j ].setBackground( colorVacio );
                }
                else if( celdasTablero[ i ][ j ].getValorIngresado( ) != 0 )
                {
                    celdas[ i ][ j ].setBackground( Color.WHITE );
                    celdas[ i ][ j ].setText( "" + celdasTablero[ i ][ j ].getValorReal( ) );
                }
            }
        }
    }

    /**
     * Devuelve una matriz con los valores ingresados por el usuario.
     * @return La matriz es devuelta con los valores ingresados por el usaurio.
     * @throws Exception If el contenido de cada casilla no es un numero entre 1 y 9.
     */
    public int[][] getMatrix( ) throws Exception
    {
        // Inicializamos la matriz
        int[][] tablero = new int[Sudoku.NUMERO_FILAS][Sudoku.NUMERO_COLUMNAS];

        for( int i = 0; i < Sudoku.NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                int valor = 0;
                try
                {
                    String contenido = celdas[ i ][ j ].getText( );

                    if( contenido != null && !contenido.equals( "" ) )
                    {
                        valor = Integer.parseInt( celdas[ i ][ j ].getText( ) );
                        if( valor < 1 || valor > 9 )
                        {
                            throw new Exception( "El contenido ingresado en cada celda de de ser un número entre 1 y 9" );
                        }
                    }

                    tablero[ i ][ j ] = valor;
                }
                catch( NumberFormatException e )
                {
                    throw new Exception( "El contenido ingresado en cada celda de de ser un número entre 1 y 9" );
                }
            }
        }

        return tablero;
    }

    /**
     * Coloca los TextField en el color correcto cuando el juego es completado exitosamente.
     */
    public void dibujarTableroCompleto( )
    {
        for( int i = 0; i < Sudoku.NUMERO_AREAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                celdas[ i ][ j ].setBackground( colorResuelto );
            }
        }
    }

    /**
     * La solución es mostrada en los TextField.
     */
    public void mostrarSolucion( )
    {
        Casilla[][] tableroCasillas = principal.getCeldasTablero( );
        for( int i = 0; i < Sudoku.NUMERO_AREAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                celdas[ i ][ j ].setBackground( colorResuelto );
                celdas[ i ][ j ].setText( "" + tableroCasillas[ i ][ j ].getValorReal( ) );
                celdas[ i ][ j ].setEditable( false );
            }
        }
    }

}