/*
 * ReproductorVideos.java
 * Licencia GNU.
 */
package reproductorvideos;
import static com.sun.javafx.fxml.expression.Expression.add;
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JPanel;
import javax.media.Manager;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
/**
 * Programa para reproducir videos, utilizando la libreria JMF.
 * @author Ingeniero en Computación Ricardo Presilla
 * @version 1.0.
 */
public class ReproductorVideos extends JPanel {
    ReproductorVideos(URL mediaURL ){
      	setLayout( new BorderLayout() ); // Usando border layout
// Use lightweight components for Swing compatibility
        Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );
        try{// create a player to play the media specified in the URL
            Player mediaPlayer = Manager.createRealizedPlayer( mediaURL );
        // get the components for the video and the playback controls
            Component video = mediaPlayer.getVisualComponent();
            Component controls = mediaPlayer.getControlPanelComponent();
            if ( video != null )
               add( video, BorderLayout.CENTER ); // agrega el componente de video
            if ( controls != null )
               add( controls, BorderLayout.SOUTH ); // agrega los controles
            mediaPlayer.start(); // start playing the media clip
        }catch(NoPlayerException noPlayerException){
            System.err.println( "No archivo de video no encontrado" );
        }catch ( CannotRealizeException cannotRealizeException ){
            System.err.println( "Could not realize media player" );
        }catch ( IOException iOException ){
            System.err.println( "Error leyendo desde el origen" );
        } // end catch
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        // show open file dialog
        int result = fileChooser.showOpenDialog( null );
        if ( result == JFileChooser.APPROVE_OPTION ){ // user chose a file
            URL mediaURL = null;
            try{
                mediaURL = fileChooser.getSelectedFile().toURL();
            }catch ( MalformedURLException malformedURLException ){
               System.err.println( "Could not create URL for the file" );
            } 
            if( mediaURL != null ){ // only display if there is a valid URL
                JFrame mediaTest = new JFrame( "Media Tester" );
                mediaTest.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                ReproductorVideos mediaPanel = new ReproductorVideos( mediaURL );
                mediaTest.add( mediaPanel );
                mediaTest.setSize( 300, 300 );
                mediaTest.setVisible( true );
            } // end inner if
        } // end outer if
    }
    
}
