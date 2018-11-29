/* CS2150Coursework.java
 * TODO: put your university username and full name here
 *
 * Scene Graph:
 *  Scene origin
 *  |
 *
 *  TODO: Provide a scene graph for your submission
 */
package coursework.kuzminsm;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;
import GraphicsLab.*;

/**
 * TODO: Briefly describe your submission here
 *
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * </ul>
 * TODO: Add any additional controls for your sample to the list above
 *
 */
public class CS2150Coursework extends GraphicsLab
{
	
    private Texture groundTexture;
    private Texture skyTexture;
    private Texture spaceTexture;
    private Texture brickTexture;
    private final int planeList = 3;
    private final int houseList = 1;
    private final int launcherList = 2;
   
    private float groundPlaneY = -1.0f;
    private float backPlaneY = 4.0f;
    private float spacePlaneY = 18.0f;
    private float maxSpaceY = 18f;
    private float minSpaceY = -1f;
    private float launcherY = 1.0f;
    private float rocketScale = 1f;
    private float rocketSquareScale = 1f;
    //TODO: Feel free to change the window title and default animation scale here
    public static void main(String args[])
    {   new CS2150Coursework().run(WINDOWED,"CS2150 Coursework Submission",0.01f);
    }

    protected void initScene() throws Exception
    {//TODO: Initialise your resources here - might well call other methods you write.
    	groundTexture = loadTexture ("coursework/kuzminsm/textures/groundTexture.jpg");
    	skyTexture = loadTexture ("coursework/kuzminsm/textures/skyTexture.png");
    	spaceTexture = loadTexture ("coursework/kuzminsm/textures/spaceTexture.jpg");
    	brickTexture = loadTexture ("coursework/kuzminsm/textures/brickTexture.jpg");
    	
    	float globalAmbient[]  ={0.2f,  0.2f,  0.2f, 1f};
    	GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));
        // the first light for the scene is white...
        float diffuse0[]  = { 0.6f,  0.6f, 0.6f, 1.0f};
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
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_NORMALIZE);
        
        
    
        
        GL11.glNewList(planeList,GL11.GL_COMPILE);
        {   drawUnitPlane();
        }
        GL11.glEndList();
        
        GL11.glNewList(houseList,GL11.GL_COMPILE);
        {   drawUnitCube();
        }
       GL11.glEndList();

       GL11.glNewList(launcherList,GL11.GL_COMPILE);
       { drawLauncher();
       }
      GL11.glEndList();

    	
    	
    }
    protected void checkSceneInput()
    {//TODO: Check for keyboard and mouse input here
        if(Keyboard.isKeyDown(Keyboard.KEY_R)){  
        	if(spacePlaneY > minSpaceY) {
        	groundPlaneY -= 1.0f * getAnimationScale(); 
        	backPlaneY -= 1.0f * getAnimationScale();
        	spacePlaneY -= 1.0f * getAnimationScale();
        	launcherY -= 1.0f * getAnimationScale();
        	rocketScale -= 0.03f * getAnimationScale();
        	rocketSquareScale -=0.025f * getAnimationScale();
        	}
         
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_F)){  
        	if(spacePlaneY < maxSpaceY) {
        	groundPlaneY += 1.0f * getAnimationScale(); 
        	backPlaneY += 1.0f * getAnimationScale();
        	spacePlaneY += 1.0f * getAnimationScale();
        	launcherY+= 1.0f * getAnimationScale();
        	rocketScale += 0.03f * getAnimationScale();
        	rocketSquareScale +=0.025f * getAnimationScale();
        	
        	}
         
        }
        

    }
    protected void updateScene()
    {
        //TODO: Update your scene variables here - remember to use the current animation scale value
        //        (obtained via a call to getAnimationScale()) in your modifications so that your animations
        //        can be made faster or slower depending on the machine you are working on
    }
    protected void renderScene()
    {//TODO: Render your scene here - remember that a scene graph will help you write this method! 
     //      It will probably call a number of other methods you will write.
    	
        // draw the ground plane
    	
        GL11.glPushMatrix();
        {
            // disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.WHITE.submit();
            // enable texturing and bind an appropriate texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTexture.getTextureID());
            
            // position, scale and draw the ground plane using its display list
            GL11.glTranslatef(0.0f,groundPlaneY,-10.0f); //y=-1f
            GL11.glScalef(25.0f, 1.0f, 20.0f);
            GL11.glCallList(planeList);
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();

        // draw the back plane
        GL11.glPushMatrix();
        {
            // disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.WHITE.submit();
            // enable texturing and bind an appropriate texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,skyTexture.getTextureID());
            
            // position, scale and draw the back plane using its display list
            GL11.glTranslatef(0.0f,backPlaneY,-20.0f); //y=4f
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glScalef(25.0f, 1.0f, 10.0f);
            GL11.glCallList(planeList);
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
        //draw the space plane
        GL11.glPushMatrix();
        {
        	GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            Colour.WHITE.submit();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,spaceTexture.getTextureID());
            GL11.glTranslatef(0.0f,spacePlaneY,-20.0f);
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glScalef(25.0f, 10.0f, 19f);
            GL11.glCallList(planeList);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        {
        	
            // how shiny are the front faces of the trunk (specular exponent)
            float trunkFrontShininess  = 20.0f;
            // specular reflection of the front faces of the trunk
            float trunkFrontSpecular[] = {0.7f, 0.7f, 0.7f, 1.0f};
            // diffuse reflection of the front faces of the trunk
            float trunkFrontDiffuse[]  = {1f, 1f, 1f, 1.0f};
        
            // set the material properties for the trunk using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, trunkFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(trunkFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(trunkFrontDiffuse));
           
            // position the tree
            GL11.glTranslatef(0.0f, -0.65f, -10f);
            GL11.glScalef(rocketScale, rocketScale, rocketScale);
            // draw the trunk using a cylinder quadric object. Surround the draw call with a
            // push/pop matrix pair, as the cylinder will originally be aligned with the Z axis
            // and will have to be rotated to align it along the Y axis
            GL11.glPushMatrix();
            {
                GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                new Cylinder().draw(0.5f, 0.5f, 3f, 10, 10);
            }
            GL11.glPopMatrix();

            // how shiny are the front faces of the leafy head of the tree (specular exponent)
            float headFrontShininess  = 20.0f;
            // specular reflection of the front faces of the head
            float headFrontSpecular[] = {1f, 0.1f, 0.1f, 1.0f};
            float headFrontDiffuse[]  = {1f, 0.1f, 0.1f, 1.0f};
            
            // set the material properties for the head using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, headFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(headFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(headFrontDiffuse));

            // position and draw the leafy head using a sphere quadric object
           
            GL11.glTranslatef(0.0f,3.0f,0.0f);
            GL11.glPushMatrix();
            {
            	
            	GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                new Cylinder().draw(0.5f, 0.0f, 1f, 10,10);
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        
        float scale2 = rocketSquareScale -0.3f;
        GL11.glPushMatrix();{
        GL11.glTranslatef(0.65f, -0.3f, -10.0f);
        GL11.glRotatef(0, 0, 0, 0);
        GL11.glScalef(scale2, scale2, scale2);
        GL11.glCallList(houseList);
        }
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();{
        GL11.glTranslatef(-0.65f, -0.3f, -10.0f);
        GL11.glScalef(scale2, scale2, scale2);
        GL11.glRotatef(180, 0, 1, 0);
        GL11.glCallList(houseList);
        }
        GL11.glPopMatrix();
        
        
        GL11.glPushMatrix();{
        GL11.glTranslatef(0f, -0.3f, -9.5f);
        GL11.glScalef(scale2, scale2, scale2);
        GL11.glRotatef(-90, 0, 1, 0);
        GL11.glCallList(houseList);
        }
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();{
        GL11.glTranslatef(0.0f, -0.3f, -10.5f);
        GL11.glScalef(scale2, scale2, scale2);
        GL11.glRotatef(90, 0, 1, 0);
        GL11.glCallList(houseList);
        }
        GL11.glPopMatrix();
        
        GL11.glPopMatrix();
        
        
        GL11.glPushMatrix();
        {
           //CODE MISSING


           
            // position and scale the house
	        GL11.glTranslatef(2.75f, launcherY, -12f);
	        GL11.glScalef(1.2f, 1.2f, 1.2f);
	        // rotate the house a little so that we can see more of it
	        GL11.glRotatef(35.0f, 0.0f, 1.0f, 0.0f);
	
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

	        // draw the base of the house using its display list
	        GL11.glCallList(launcherList);
	        
	        }
        GL11.glPopMatrix();
        
        
        
        
    }
    protected void setSceneCamera()
    {
        // call the default behaviour defined in GraphicsLab. This will set a default perspective projection
        // and default camera settings ready for some custom camera positioning below...  
        super.setSceneCamera();

        //TODO: If it is appropriate for your scene, modify the camera's position and orientation here
        //        using a call to GL11.gluLookAt(...)
   }

    protected void cleanupScene()
    {//TODO: Clean up your resources here
    }
    private void resetAnimations() {
    	//RESET
    }
    
    private void drawUnitPlane()
    {
        Vertex v1 = new Vertex(-0.5f, 0.0f,-0.5f); // left,  back
        Vertex v2 = new Vertex( 0.5f, 0.0f,-0.5f); // right, back
        Vertex v3 = new Vertex( 0.5f, 0.0f, 0.5f); // right, front
        Vertex v4 = new Vertex(-0.5f, 0.0f, 0.5f); // left,  front
        
        // draw the plane geometry. order the vertices so that the plane faces up
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v4.toVector(),v3.toVector(),v2.toVector(),v1.toVector()).submit();
            
            GL11.glTexCoord2f(0.0f,0.0f);
            v4.submit();
            
            GL11.glTexCoord2f(1.0f,0.0f);
            v3.submit();
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v2.submit();
            
            GL11.glTexCoord2f(0.0f,1.0f);
            v1.submit();
        }
        GL11.glEnd();
        
        // if the user is viewing an axis, then also draw this plane
        // using lines so that axis aligned planes can still be seen
        if(isViewingAxis())
        {
            // also disable textures when drawing as lines
            // so that the lines can be seen more clearly
            GL11.glPushAttrib(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            {
                v4.submit();
                v3.submit();
                v2.submit();
                v1.submit();
            }
            GL11.glEnd();
            GL11.glPopAttrib();
        }
    }
    
    
    private void drawUnitCube()
    {
        // the vertices for the cube (note that all sides have a length of 1)
        Vertex v1 = new Vertex(-0.25f, -0.5f,  0.1f);
        Vertex v2 = new Vertex(-0.25f,  0.5f,  0.1f);
        Vertex v3 = new Vertex( 0.25f,  0.1f,  0.1f);
        Vertex v4 = new Vertex( 0.25f, -1f,  0.1f);
        Vertex v5 = new Vertex(-0.25f, -0.5f, -0.1f);
        Vertex v6 = new Vertex(-0.25f,  0.5f, -0.1f);
        Vertex v7 = new Vertex( 0.25f,  0.1f, -0.1f);
        Vertex v8 = new Vertex( 0.25f, -1f, -0.1f);

        // draw the near face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v3.toVector(),v2.toVector(),v1.toVector(),v4.toVector()).submit();
            
            v3.submit();
            v2.submit();
            v1.submit();
            v4.submit();
        }
        GL11.glEnd();

        // draw the left face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v2.toVector(),v6.toVector(),v5.toVector(),v1.toVector()).submit();
            
            v2.submit();
            v6.submit();
            v5.submit();
            v1.submit();
        }
        GL11.glEnd();

        // draw the right face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v7.toVector(),v3.toVector(),v4.toVector(),v8.toVector()).submit();
            
            v7.submit();
            v3.submit();
            v4.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the top face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v7.toVector(),v6.toVector(),v2.toVector(),v3.toVector()).submit();
            
            v7.submit();
            v6.submit();
            v2.submit();
            v3.submit();
        }
        GL11.glEnd();

        // draw the bottom face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v4.toVector(),v1.toVector(),v5.toVector(),v8.toVector()).submit();
            
            v4.submit();
            v1.submit();
            v5.submit();
            v8.submit();
        }
        GL11.glEnd();

        // draw the far face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v6.toVector(),v7.toVector(),v8.toVector(),v5.toVector()).submit();
            
            v6.submit();
            v7.submit();
            v8.submit();
            v5.submit();
        }
        GL11.glEnd();
    }
    private void drawLauncher() {
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

    
