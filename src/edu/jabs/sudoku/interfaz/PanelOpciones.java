package edu.jabs.sudoku.interfaz;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelOpciones extends JPanel implements ActionListener
{

    // -------------------------------------------
    // Constantes
    // -------------------------------------------

    private static final String OPCION_1 = "OPCION_1";

    private static final String OPCION_2 = "OPCION_2";

    private static final String ABRIR_ARCHIVO = "ABRIR_ARCHIVO";

    private static final String VALIDAR = "VALIDAR";

    private static final String CARGAR_JUEGO = "CARGAR_JUEGO";

    // -------------------------------------------
    // Atributos
    // -------------------------------------------

    private InterfazSudoku principal;

    // -------------------------------------------
    // Atributos de la ventana
    // -------------------------------------------

    private JButton btnOpcion1;

    private JButton btnOpcion2;

    private JButton btnArchivo;

    private JButton btnValidar;

    private JButton btnMostrarSolucion;

    // -------------------------------------------
    // Constructor
    // -------------------------------------------

    /**
     * Constructor del Panel
     * @param ventanaPrincipal Ventana Principal. ventanaPrincipal 1= null.
     */
    public PanelOpciones( InterfazSudoku ventanaPrincipal )
    {

        principal = ventanaPrincipal;

        TitledBorder border = new TitledBorder( "Opciones" );
        border.setTitleColor( Color.WHITE );
        setBorder( border );
        setLayout( new GridLayout( 1, 2 ) );
        setBackground( Color.BLACK );

        // Boton de Abrir Archivo
        btnArchivo = new JButton( "Cargar" );
        btnArchivo.setActionCommand( ABRIR_ARCHIVO );
        btnArchivo.addActionListener( this );
        add( btnArchivo );

        // Boton de Validar
        btnValidar = new JButton( "Validar" );
        btnValidar.setActionCommand( VALIDAR );
        btnValidar.addActionListener( this );
        btnValidar.setEnabled( false );
        add( btnValidar );

        // Boton de mostrar solucion
        btnMostrarSolucion = new JButton( "Solución" );
        btnMostrarSolucion.setActionCommand( CARGAR_JUEGO );
        btnMostrarSolucion.addActionListener( this );
        btnMostrarSolucion.setEnabled( false );
        add( btnMostrarSolucion );

        // Boton opcion 1
        btnOpcion1 = new JButton( "Opción 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        // Boton opcion 2
        btnOpcion2 = new JButton( "Opcion 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );

    }

    // -------------------------------------------
    // Métodos
    // -------------------------------------------

    /**
     * Método que maneja los Eventos.
     * @param e Acción generada por un evento. e != null.
     */
    @Override
    public void actionPerformed( ActionEvent e )
    {
        if( OPCION_1.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( e.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
        else if( ABRIR_ARCHIVO.equals( e.getActionCommand( ) ) )
        {
            principal.cargarJuego( );
        }
        else if( VALIDAR.equals( e.getActionCommand( ) ) )
        {
            principal.validarJuego( );
        }
        else if( CARGAR_JUEGO.equals( e.getActionCommand( ) ) )
        {
            principal.mostrarSolucion( );
        }

    }

    /**
     * Habilita o deshabilita los botones correspondies a la accion de validar y mostrar solución <br>
     * dependiendo del estado del juego.
     */
    public void actualizarBotones( )
    {
        if( principal.juegoActivo( ) )
        {
            btnValidar.setEnabled( true );
            btnMostrarSolucion.setEnabled( true );
        }
        else
        {
            btnValidar.setEnabled( false );
            btnMostrarSolucion.setEnabled( false );
        }
    }

}