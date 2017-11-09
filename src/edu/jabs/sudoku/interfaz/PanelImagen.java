package edu.jabs.sudoku.interfaz;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PanelImagen extends JPanel
{

    // -------------------------------------------
    // Constructor
    // -------------------------------------------

    /**
     * Serial por default.
     */
    private static final long serialVersionUID = 2L;

    /**
     * Constructor de la clase.
     */
    public PanelImagen( )
    {
        JLabel imagen = new JLabel( );
        ImageIcon icono = new ImageIcon( "./data/imagenes/title.png" );
        // El Label es añadido.
        imagen = new JLabel( "" );
        imagen.setIcon( icono );
        add( imagen );

        setBackground( Color.BLACK );
        setBorder( new LineBorder( Color.BLACK ) );
    }

}