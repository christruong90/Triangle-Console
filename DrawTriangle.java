package q2;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * <p>Generates a triangle using processMouseDrag and processMouseClick 
 * events that also generates a circle in the center of triangle. </p>
 *
 * @author Chris Truong
 * @version 1.0
 */
public class DrawTriangle extends Application {
    /** Declared constant THREE. */
    private static final int THREE = 3;
    /** Declared constant FOUR. */
    private static final int FOUR = 4;
    /** The contents of the application scene. */
    private Group root;
    /** center point. */
    private Point2D center;
    /** circle to move to first mouse click location. */
    private Circle atCenter = new Circle(0, 0, THREE);
    /** declared instance firstPoint. */
    private Point2D firstPoint;
    
    
   
    /**
     * Displays an initially empty scene, waiting for the user to draw lines
     * with the mouse.
     * 
     * @param primaryStage
     *            a Stage
     */
    public void start(Stage primaryStage) {
        root = new Group(atCenter);
        atCenter.setFill(Color.CYAN);

        final int appWidth = 800;
        final int appHeight = 500;
        Scene scene = new Scene(root, appWidth, appHeight, Color.BLACK);

        primaryStage.setTitle("Equilateral Triangle");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        scene.setOnMousePressed(this::processMousePress);
        scene.setOnMouseDragged(this::processMouseDrag);
        
    }
    
    /**
     * Declared event method processMouse Drag.
     * @param event accepted in parameter as MouseEvent type.
     */
    public void processMouseDrag(MouseEvent event) {
        firstPoint = new Point2D(event.getSceneX(), event.getSceneY());
        
        
        Point2D centerOrigin = new Point2D((firstPoint.getX() - center.getX()), 
                firstPoint.getY() - center.getY());
        
        double secondPointX = centerOrigin.getX() 
                * Math.cos((2 * Math.PI) / THREE) 
                - (centerOrigin.getY() * Math.sin((2 * Math.PI) / THREE));
        double secondPointY = centerOrigin.getX() 
                * Math.sin((2 * Math.PI) / THREE) 
                + (centerOrigin.getY() * Math.cos((2 * Math.PI) / THREE));
        
        double thirdPointX = centerOrigin.getX() 
                * Math.cos((FOUR * Math.PI) / THREE) - (centerOrigin.getY() 
                        * Math.sin((FOUR * Math.PI) / THREE));
        double thirdPointY = centerOrigin.getX() 
                * Math.sin((FOUR * Math.PI) / THREE) + (centerOrigin.getY() 
                        * Math.cos((FOUR * Math.PI) / THREE));
        
        Point2D secondPoint = new Point2D(secondPointX, secondPointY);
        Point2D thirdPoint = new Point2D(thirdPointX, thirdPointY);
        
        Circle myCircle = new Circle(center.getX(), center.getY(), THREE);
        myCircle.setFill(Color.BLUE);
        
        
        
        Polygon myTriangle = new Polygon();
        myTriangle.getPoints().addAll(new Double[] {
                thirdPoint.getX() + center.getX(), thirdPoint.getY() 
                + center.getY(),
                secondPoint.getX() + center.getX(), secondPoint.getY() 
                + center.getY(),
                centerOrigin.getX() + center.getX(), centerOrigin.getY() 
                + center.getY()
        });
        
        
        myTriangle.setFill(Color.ORANGE);
        
        root.getChildren().clear();
        root.getChildren().add(myTriangle);
        root.getChildren().add(myCircle);
        
        
        
    }
    
    /**
     * Declared processMousePress event to process event when mouse is clicked.
     * @param event of MouseEvent type accepted in parameter.
     */
    public void processMousePress(MouseEvent event) {
        center = new Point2D(event.getSceneX(), event.getSceneY());
        
        Circle myCircle = new Circle(center.getX(), center.getY(), THREE);
        myCircle.setFill(Color.BLUE);
        
        root.getChildren().clear();
        root.getChildren().add(myCircle);
        
        
        
    }


    /**
     * Launches the JavaFX application.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

