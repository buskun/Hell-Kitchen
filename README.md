### How to use Bounding Area

1. Create `Bounding Area` and add components
    ```java
   import base.Scene;
   import utility.bounding.BoundingArea;
   
   class SomeScene extends Scene {
       // Create BoundingArea named map
       BoundingArea map = new BoundingArea();
   
       @Override
       void init() {
           /* ... */
           // Some random entity
           JLabel box = new JLabel();
           
           // Add that entity to map
           map.add("SolidBox", box);
           /* ... */
       }
   }
    ```
   
2. Detect collision
    ##### Assume that you have created the following Bounding Area

    ```java
   import base.Scene;
   import utility.bounding.BoundingArea;
   import javax.swing.*;
   
   class SomeScene extends Scene {
       BoundingArea map = new BoundingArea();
       JLabel character = null;
   
       @Override
       void init() {
           /* ... */
   
           // Some character entity
           character = new JLabel();
   
           /* ... */
       }
   }
    ```
   
   ##### You can detect the collision by
   
    ```java
    if (map.intersects(character)) {
       /* Do something */
   }
    ```
3. Listen to collision event
   ##### If you want to know anytime something checking for intersection by using intersects and it intersects
   
    ```java
   map.addIntersectionListener((name /* name of entity that intersects */ ) -> {
       /* Do something */
       return true;
   });
    ```
