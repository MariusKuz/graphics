package Designer;

import org.lwjgl.opengl.GL11;

import GraphicsLab.Colour;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

/**
 * The shape designer is a utility class which assits you with the design of 
 * a new 3D object. Replace the content of the drawUnitShape() method with
 * your own code to creates vertices and draw the faces of your object.
 * 
 * You can use the following keys to change the view:
 *   - TAB		switch between vertex, wireframe and full polygon modes
 *   - UP		move the shape away from the viewer
 *   - DOWN     move the shape closer to the viewer
 *   - X        rotate the camera around the x-axis (clockwise) 
 *   - Y or C   rotate the camera around the y-axis (clockwise)
 *   - Z        rotate the camera around the z-axis (clockwise)
 *   - SHIFT    keep pressed when rotating to spin anti-clockwise
 *   - A 		Toggle colour (only if using submitNextColour() to specify colour)
 *   - SPACE	reset the view to its initial settings
 *  
 * @author Remi Barillec
 *
 */
public class ShapeDesigner extends AbstractDesigner {
	
	/** Main method **/
	public static void main(String args[])
    {   
		new ShapeDesigner().run( WINDOWED, "Designer", 0.01f);
    }
	
	/** Draw the shape **/
    protected void drawUnitShape()
    {
    	//shape1
    	Vertex v1 = new Vertex(-0.5f, -0.5f,  0.5f);
        Vertex v2 = new Vertex(-0.5f,  1.5f,  0.5f);
        Vertex v3 = new Vertex( 0.5f,  1.5f,  0.5f);
        Vertex v4 = new Vertex( 0.5f, -0.5f,  0.5f);
        Vertex v5 = new Vertex(-0.5f, -0.5f, -0.5f);
        Vertex v6 = new Vertex(-0.5f,  1.5f, -0.5f);
        Vertex v7 = new Vertex( 0.5f,  1.5f, -0.5f);
        Vertex v8 = new Vertex( 0.5f, -0.5f, -0.5f);
        
        
        //shape2
        Vertex v9 = new Vertex(-1.0f, -0.25f,  0.25f);
        Vertex v10 = new Vertex(-1.0f, 1.25f,  0.25f);
        Vertex v11 = new Vertex(-1.0f, 1.25f,  -0.25f);
        Vertex v12 = new Vertex(-1.0f, -0.25f,  -0.25f);
        
        
        //shape3
        Vertex v13 = new Vertex(-1.5f, -0.25f,  -0.25f);
        Vertex v14 = new Vertex(-1.5f, -0.25f,  0.25f);
        Vertex v15 = new Vertex(-1.5f, 1.25f,  0.25f);
        Vertex v16 = new Vertex(-1.5f, 1.25f,  -0.25f);
        
        
        //shape4
        Vertex v17 = new Vertex (-1.5f, 1.25f,  0.1f);
        Vertex v18 = new Vertex(-1.5f, 1.25f,  -0.1f);
        Vertex v19 = new Vertex(-1.5f, 1f,  -0.1f);
        Vertex v20 = new Vertex(-1.5f, 1f,  0.1f);
        Vertex v21 = new Vertex(-2.5f, 1.25f,  0.1f); 
        Vertex v22 = new Vertex (-2.5f, 1f,  0.1f);
        Vertex v23 = new Vertex (-2.5f, 1f,  -0.1f);
        Vertex v24 = new Vertex(-2.5f, 1.25f,  -0.1f);
        
        //shape5
        Vertex v25 = new Vertex (-1.5f,0.75f,  0.1f);
        Vertex v26 = new Vertex(-1.5f, 0.75f,  -0.1f);
        Vertex v27 = new Vertex(-1.5f, 0.5f,  -0.1f);
        Vertex v28 = new Vertex(-1.5f, 0.5f,  0.1f);
        Vertex v29 = new Vertex(-2.5f, 0.75f,  0.1f); 
        Vertex v30 = new Vertex (-2.5f, 0.5f,  0.1f);
        Vertex v31 = new Vertex (-2.5f, 0.5f,  -0.1f);
        Vertex v32 = new Vertex(-2.5f, 0.75f,  -0.1f);
        
        //shape6
        Vertex v33 = new Vertex ( 0.5f,  1.5f, -0.5f);
        Vertex v34 = new Vertex(0.5f, 1.5f,  -0.25f);
        Vertex v35 = new Vertex(0.25f, 1.5f,  -0.25f);
        Vertex v36 = new Vertex(0.25f, 1.5f,  -0.5f);
        
        
        
        Vertex v37 = new Vertex(0.375f, 2.5f,  -0.375f);
        Vertex v38 = new Vertex (0.375f, 2.5f,  -0.375f);
        Vertex v39 = new Vertex (0.375f, 2.5f,  -0.375f);
        Vertex v40 = new Vertex( 0.375f, 2.5f,  -0.375f);
        
        //shape 7
        Vertex v41 = new Vertex(-0.5f, -0.5f,  0.5f);
        Vertex v42 = new Vertex(-0.5f,  -1.5f,  0.5f);
        Vertex v43 = new Vertex( 0.5f,  -1.5f,  0.5f);
        Vertex v44 = new Vertex( 0.5f, -0.5f,  0.5f);
        Vertex v45 = new Vertex(-0.5f, -0.5f, -0.5f);
        Vertex v46 = new Vertex(-0.5f,  -1.5f, -0.5f);
        Vertex v47 = new Vertex( 0.5f,  -1.5f, -0.5f);
        Vertex v48 = new Vertex( 0.5f, -0.5f, -0.5f);
        
        //shape1
        // draw the near face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
          

            v3.submit();
            v2.submit();
            v1.submit();
            v4.submit();
        }
        GL11.glEnd();

        // draw the left face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            

            v2.submit();
            v6.submit();
            v5.submit();
            v1.submit();
        }
        GL11.glEnd();

        // draw the right face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
         

            v7.submit();
            v3.submit();
            v4.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the top face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
          

            v7.submit();
            v6.submit();
            v2.submit();
            v3.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
   

            v4.submit();
            v1.submit();
            v5.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the far face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v6.submit();
            v7.submit();
            v8.submit();
            v5.submit();
        }
        GL11.glEnd();        
		
        
        
        
        //shape2
        //face1(close)
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v2.submit();
            v10.submit();
            v9.submit();
            v1.submit();
        }
        GL11.glEnd();  
        
        //top face
        //face2
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v2.submit();
            v6.submit();
            v11.submit();
            v10.submit();
        }
        GL11.glEnd();  
        
        //bottom face
        //face3
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v9.submit();
            v12.submit();
            v5.submit();
            v1.submit();
        }
        GL11.glEnd();  
        
        
        //far face
        //face4
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v11.submit();
            v6.submit();
            v5.submit();
            v12.submit();
        }
        GL11.glEnd();  
        
        //leftest face
        //face 5
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v10.submit();
            v11.submit();
            v12.submit();
            v9.submit();
        }
        GL11.glEnd();  
        
        
        
        
        //shape3
        
       //front face
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v10.submit();
            v15.submit();
            v14.submit();
            v9.submit();
        }
        GL11.glEnd();  
        
        //bottomface (2)
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v12.submit();
            v9.submit();
            v14.submit();
            v13.submit();
        }
        GL11.glEnd();  
        
        //back face(3)
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v16.submit();
            v11.submit();
            v12.submit();
            v13.submit();
        }
        GL11.glEnd();  
        
        
        //topface (4)
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v11.submit();
            v16.submit();
            v15.submit();
            v10.submit();
        }
        GL11.glEnd();  
        
        //leftface (5)
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v15.submit();
            v16.submit();
            v13.submit();
            v14.submit();
        }
        GL11.glEnd();  
        
        
        
        
        
        //shape4
        //close face(1)
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v18.submit();
            v24.submit();
            v23.submit();
            v19.submit();
        }
        GL11.glEnd();  
        
        //top face(2)
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v17.submit();
            v21.submit();
            v24.submit();
            v18.submit();
        }
        GL11.glEnd();  
    
    
    
    //back face (3)
    
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v17.submit();
            v20.submit();
            v22.submit();
            v21.submit();
        }
        GL11.glEnd();  
        
        
        //bottom face(4)
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v20.submit();
            v19.submit();
            v23.submit();
            v22.submit();
        }
        GL11.glEnd();  
        
        //right face (5)
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v17.submit();
            v18.submit();
            v19.submit();
            v20.submit();
        }
        GL11.glEnd();
        
        //left face
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v24.submit();
            v21.submit();
            v22.submit();
            v23.submit();
        }
        GL11.glEnd();  
        
      //shape5
        //close face(1)
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v26.submit();
            v32.submit();
            v31.submit();
            v27.submit();
        }
        GL11.glEnd();  
        
        //top face(2)
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v25.submit();
            v29.submit();
            v32.submit();
            v26.submit();
        }
        GL11.glEnd();  
    
    
    
    //back face (3)
    
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v25.submit();
            v28.submit();
            v30.submit();
            v29.submit();
        }
        GL11.glEnd();  
        
        
        //bottom face(4)
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v28.submit();
            v27.submit();
            v31.submit();
            v30.submit();
        }
        GL11.glEnd();  
        
        //right face (5)
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v25.submit();
            v26.submit();
            v27.submit();
            v28.submit();
        }
        GL11.glEnd();
        
        //left face
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v32.submit();
            v29.submit();
            v30.submit();
            v31.submit();
        }
        GL11.glEnd();  
        
        //shape7
        //near face 
        GL11.glBegin(GL11.GL_POLYGON);
        {
          

            v39.submit();
            v38.submit();
            v35.submit();
            v34.submit();
        }
        GL11.glEnd();

        // draw the left face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            

            v37.submit();
            v36.submit();
            v35.submit();
            v38.submit();
        }
        GL11.glEnd();

        // draw the right face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
         

            v40.submit();
            v39.submit();
            v34.submit();
            v33.submit();
        }
        GL11.glEnd();

        // draw the top face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
          

            v40.submit();
            v37.submit();
            v38.submit();
            v39.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
   

            v33.submit();
            v34.submit();
            v35.submit();
            v36.submit();
        }
        GL11.glEnd();

        // draw the far face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v37.submit();
            v40.submit();
            v33.submit();
            v36.submit();
        }
        GL11.glEnd();    
        
        
      //shape7
        // draw the near face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
          

            v43.submit();
            v42.submit();
            v41.submit();
            v44.submit();
        }
        GL11.glEnd();

        // draw the left face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
              

            v42.submit();
            v46.submit();
            v45.submit();
            v41.submit();
        }
        GL11.glEnd();

        // draw the right face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
         

            v47.submit();
            v43.submit();
            v44.submit();
            v48.submit();
        }
        GL11.glEnd();

        // draw the top face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
          

            v47.submit();
            v46.submit();
            v42.submit();
            v43.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
   

            v44.submit();
            v41.submit();
            v45.submit();
            v48.submit();
        }
        GL11.glEnd();

        // draw the far face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
       

            v46.submit();
            v47.submit();
            v48.submit();
            v45.submit();
        }
        GL11.glEnd();        
		
    }
}
