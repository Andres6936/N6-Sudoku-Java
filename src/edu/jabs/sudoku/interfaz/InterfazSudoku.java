package edu.jabs.sudoku.interfaz;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.jabs.sudoku.mundo.Casilla;
import edu.jabs.sudoku.mundo.Sudoku;

public class InterfazSudoku extends JFrame
{

    /**
     * Serial por default.
     */
    private static final long serialVersionUID = 1L;

    // -------------------------------------------
    // Atributos
    // -------------------------------------------

    /**
     * Clase principal del mundo.
     */
    private Sudoku sudoku;

    // -------------------------------------------
    // Atributos de la ventana
    // -------------------------------------------

    /**
     * Panel Opciones
     */
    private PanelOpciones panelOpciones;

    /**
     * Panel Imagen
     */
    private PanelImagen panelImagen;

    /**
     * PanelTablero
     */
    private PanelTablero panelTablero;

    // -------------------------------------------
    // Constructor
    // -------------------------------------------

    /**
     * Constructor de la ventana.
     */
    public InterfazSudoku( )
    {
        // La clase principal es construida.
        sudoku = new Sudoku( );

        // La ventana es contruida.
        setLayout( new BorderLayout( ) );
        setSize( 500, 500 );
        setResizable( false );
        setTitle( "Sudoku" );
        getContentPane( ).setBackground( Color.BLACK );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

        // Inicialización del panel
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelOpciones = new PanelOpciones( this );
        add( panelOpciones, BorderLayout.SOUTH );

        panelTablero = new PanelTablero( this );
        add( panelTablero, BorderLayout.CENTER );

        // Paneles Auxiliares

        JPanel auxiliarPanel1 = new JPanel( );
        auxiliarPanel1.setPreferredSize( new Dimension( 110, 0 ) );
        auxiliarPanel1.setBackground( Color.BLACK );
        add( auxiliarPanel1, BorderLayout.WEST );

        JPanel auxiliarPanel2 = new JPanel( );
        auxiliarPanel2.setPreferredSize( new Dimension( 110, 0 ) );
        auxiliarPanel2.setBackground( Color.BLACK );
        add( auxiliarPanel2, BorderLayout.EAST );

    }

    // -------------------------------------------
    // Métodos
    // -------------------------------------------

    /**
     * El estado del Sudoku se carga desde un archivo dado.
     */
    public void cargarJuego( )
    {
        // Crear el JFileChooser.
        JFileChooser selecciona = new JFileChooser( );
        selecciona.setCurrentDirectory( new java.io.File( "./data" ) );
        selecciona.setDialogTitle( "Seleciona el Archivo" );
        selecciona.setFileSelectionMode( JFileChooser.FILES_ONLY );
        selecciona.setVisible( true );

        if( selecciona.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
        {
            try
            {
                // Las propiedades son inicializadas.
                Properties propiedades = new Properties( );
                propiedades.load( new FileInputStream( selecciona.getSelectedFile( ) ) );
                // Obtiene las propiedades de mundo.
                sudoku.cargarTablero( propiedades );

                // El juego es inicializado.
                sudoku.comenzarJuego( );
                // Actualizamos el tablero.
                actualizar( );
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Actualiza las gráficas del tablero de acuerdo con la información del mundo.
     */
    private void actualizar( )
    {
        panelTablero.actualizarTablero( );
        panelOpciones.actualizarBotones( );
    }

    /**
     * Valida la información ingresada por el usuario en el Tablero.
     */
    public void validarJuego( )
    {
        int[][] elTablero;
        try
        {
            elTablero = panelTablero.getMatrix( );
            sudoku.desmarcarCasillas( );
            if( sudoku.validarTablero( elTablero ) )
            {
                sudoku.finJuego( );
                dibujarTableroCompletamente( );
                JOptionPane.showMessageDialog( this, "Juego terminado exitosamente", "Juego Finalizado", JOptionPane.INFORMATION_MESSAGE );
            }
            else
            {
                actualizar( );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Cambia el color del tablero mostrando que el juego ha finalizado.
     */
    private void dibujarTableroCompletamente( )
    {
        panelTablero.dibujarTableroCompleto( );
        panelOpciones.actualizarBotones( );
    }

    /**
     * Finaliza el juego y muestra la solución.
     */
    public void mostrarSolucion( )
    {
        sudoku.finJuego( );
        panelTablero.mostrarSolucion( );
        panelOpciones.actualizarBotones( );
        sudoku.limpar( );
    }

    /**
     * Devuelve true o false si el juego esta activo o no.
     * @return true si el juego esta activo, false en caso contrario.
     */
    public boolean juegoActivo( )
    {
        return sudoku.juegoActivo( );
    }

    /**
     * Devuelve las celdas del tablero.
     * @return Las celdas del tablero son devueltas.
     */
    public Casilla[][] getCeldasTablero( )
    {
        return sudoku.getTablero( );
    }

    // -------------------------------------------
    // Puntos de Extensión
    // -------------------------------------------

    /**
     * Método de extension 1.
     */
    public void reqFuncOpcion1( )
    {
        String resultado = sudoku.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método de extension 2.
     */
    public void reqFuncOpcion2( )
    {
        String resultado = sudoku.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -------------------------------------------
    // Principal
    // -------------------------------------------

    public static void main( String[] args )
    {
        InterfazSudoku root = new InterfazSudoku( );
        root.setVisible( true );
    }

}