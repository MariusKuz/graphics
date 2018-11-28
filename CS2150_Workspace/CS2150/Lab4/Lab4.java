/* Lab4.java
 * A simple scene consisting of a house
 * 24/08/2007
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [Ry(35) T(0,-0.5,-5)] House
 *      |
 *      +-- [S(1,0.5,1) T(0,0.75,0)] Roof
 */
package Lab4;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;

import GraphicsLab.*;

/**
 * This sample demonstrates the use of OpenGL lighting and materials to enhance the appearance of a 3D scene 
 * 
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * </ul>
 *
 * <p>Adapted from Mark Bernard's LWJGL NeHe samples
 *
 * @author Anthony Jones and Dan Cornford
 */
public class Lab4 extends GraphicsLab
{
    /** display list id for the house */
    private final int houseList = 1;
    /** display list id for the roof */
    private final int roofList  = 2;
    
    /** angle that the house is rotate to see the lighting better */
    private float houseRotationAngle= 35.0f;
    
    public static void main(String args[])
    {   new Lab4().run(WINDOWED,"Lab 4 - Lighting",0.01f);
    }
    
    protected void initScene()
    {
        // global ambient light level
        float globalAmbient[]   = {0.2f,  0.2f,  0.2f, 1f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));
        
        
        // the first light for the scene is white...
        float diffuse0[]  = { 1f,  0.5f, 0.0f, 1.0f};
        // ...with a dim ambient contribution...
        float ambient0[]  = { 0.1f,  0.1f, 0.1f, 1.0f};
        // ...and is positioned above and behind the viewpoint
        float position0[] = { 0.0f, 10.0f, 5.0f, 1.0f}; 

        // supply OpenGL with the properties for the first light
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
  		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
        // enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);
        


        // enable lighting calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        // ensure that all normals are re-normalised after transformations automatically
        GL11.glEnable(GL11.GL_NORMALIZE);
        
        // prepare the display lists for later use
        GL11.glNewList(houseList,GL11.GL_COMPILE);
        {   drawUnitCube();
        }
       GL11.glEndList();
    //    GL11.glNewList(roofList,GL11.GL_COMPILE);
     //   {   drawUnitRoof();
   //     }
    //    GL11.glEndList();
    }
    protected void checkSceneInput()
    {
      if(Keyboard.isKeyDown(Keyboard.KEY_R))
      {   houseRotationAngle += 1.0f * getAnimationScale(); // Make the house go around if the R key is pressed
          if (houseRotationAngle > 360.0f) // Wrap the angle back around into 0-360 degrees.
          {  houseRotationAngle = 0.0f;
          }
      }
    }
    protected void updateScene()
    {// empty
    }
    protected void renderScene()
    {
        // position the house
        GL11.glTranslatef(0.0f, -0.5f, -5.0f);
        GL11.glRotatef(houseRotationAngle, 0.0f, 1.0f, 0.0f);

        // how shiny are the front faces of the house (specular exponent)
        float houseFrontShininess  = 2.0f;
        // specular reflection of the front faces of the house
        float houseFrontSpecular[] = {1f, 1f, 1f, 1.0f};
        // diffuse reflection of the front faces of the house
        float houseFrontDiffuse[]  = {1f, 1f, 1f, 1.0f};
        
        // set the material properties for the house using OpenGL
        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, FloatBuffer.wrap(houseFrontDiffuse));
        GL11.glScalef(-0.5f, -0.5f, 0.5f);
        // draw the base of the house using its display list
        GL11.glCallList(houseList);
        
        // position and scale the house's roof relative to the base of the house
        GL11.glTranslatef(0.0f, 0.75f, 0.0f);
       
        
        // how shiny are the front faces of the roof (specular exponent)
        float roofFrontShininess = 60.0f;
        // specular reflection of the front faces of the roof
        float roofFrontSpecular[] = {0.2f, 0.2f, 0.2f, 1.0f};
        // diffuse reflection of the front faces of the roof
        float roofFrontDiffuse[] = {0.2f, 0.2f, 0.2f, 1.0f};
        
        // Set the material properties for the house using OpenGL
        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, roofFrontShininess);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(roofFrontSpecular));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(roofFrontDiffuse));
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, FloatBuffer.wrap(roofFrontDiffuse));
        
        // draw the roof  using its display list
        GL11.glCallList(roofList);
    }
    protected void cleanupScene()
    {// empty
    }

    /**
     * Draws a roof geometry of unit length, width and height aligned along the x axis.
     * The roof uses the current OpenGL material settings for its appearance
     */
    private void drawUnitRoof()
    {
        Vertex v1 = new Vertex(-0.5f,-0.5f,-0.5f);
        Vertex v2 = new Vertex(-0.5f, 0.5f, 0.0f);
        Vertex v3 = new Vertex(-0.5f,-0.5f, 0.5f);
        Vertex v4 = new Vertex( 0.5f,-0.5f,-0.5f);
        Vertex v5 = new Vertex( 0.5f, 0.5f, 0.0f);
        Vertex v6 = new Vertex( 0.5f,-0.5f, 0.5f);
        
        // left gable
        GL11.glBegin(GL11.GL_TRIANGLES);
        {
            new Normal(v3.toVector(),v2.toVector(),v1.toVector()).submit();
            
            v3.submit();
            v2.submit();
            v1.submit();
        }
        GL11.glEnd();
        
        // back slope
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v1.toVector(),v2.toVector(),v5.toVector(),v4.toVector()).submit();
            
            v1.submit();
            v2.submit();
            v5.submit();
            v4.submit();
        }
        GL11.glEnd();
        
        // front slope
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v5.toVector(),v2.toVector(),v3.toVector(),v6.toVector()).submit();
            
            v5.submit();
            v2.submit();
            v3.submit();
            v6.submit();
        }
        GL11.glEnd();
        
        // right gable
        GL11.glBegin(GL11.GL_TRIANGLES);
        {
            new Normal(v5.toVector(),v6.toVector(),v4.toVector()).submit();
            
            v5.submit();
            v6.submit();
            v4.submit();
        }
        GL11.glEnd();
    }
    /**
     * Draws a cube of unit length, width and height using the current OpenGL material settings
     */
    private void drawUnitCube()
    {
        // the vertices for the cube (note that all sides have a length of 1)
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
