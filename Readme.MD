###                                TileGame
###                             by Jayde Dragyn 
                         (jaydedragyn@outlook.com)

A simple tile puzzle game where clicking tiles changes them along a color
progression.  All tiles start as one color, and the object is to turn
them all the final color at the same time.  Each tile clicked also changes
its neighbors, and a tile of the final color will reset to the original
color if clicked or its neighbor is clicked.

Difficulty levels include changing the size of the board, and how many 
colors the tiles will cycle through.

Colors progressions are configurable in the settings menu by clicking 
on the tiles. 

--- 

This is not an original game; I saw a 3x3 version of it somewhere (I don't
remember where), but this is my original implementation of the game,
including the additional 3x5 and 5x5 board sizes, and longer lists of 
color progressions.  If these have been done before, I am not aware.

As of writing this Readme, a friend and I have confirmed that all 
configurations are solvable except possibly Hard on the 5x5 board...
Can you find the pattern to solve it?

---

Written in Java 17 (OpenJDK 17)

Developed with IntelliJ IDEA 2022.2.2 (Community Edition)

Assets created in Blender 3.3.0 and post-processed in GIMP 2.10
Except the fonts: 

* BauhausRegular.ttf downloaded from dafontfree.net
* BAUHS93.TTF downloaded from wFonts.com

As this is a personal project, I think the fonts are ok to use here?

I also lost the original GIMP file for the title art, so only have the
final art.  I do have the .blend files to create new buttons and new
tile colors as desired.

The program icon and background images were created from an earlier
version of the tiles that I didn't like so much in-game.

--- 

To run from the command line, go into src and compile all source files to the
assets folder with

    javac -d ../assets *.java

and then go into the assets folder and run the TileGame class with

    java TileGame

--- 

This is my first GitHub submission, and until now I've mostly developed 
little programs for my own enjoyment, usually just running them from the 
IDE with no regard for distribution.  I did not give any thought to 
distribution here, which is why the instructions above may seem odd. 

If you find this program interesting in any way, great!  I have not
created anything groundbreaking here (and probably broke at least one
Java coding convention), so don't expect much.  Any constructive feedback
is welcome.

--- 

### Future Plans

Easy on the 3x3 board takes 5 clicks to solve, and easy on the 3x5 board
takes 6 clicks to solve.  Beyond that I'm not sure...  one idea is to
record the number of clicks to solve each puzzle and report that during 
and/or after the game (maybe along the side).

Another idea is to time how long it takes to solve, starting from when
the Start button is clicked and stopping when the final tile change
wins the game (pausing the timer if the Help or Menu buttons are clicked,
and resuming when a Back button is clicked).  That could be displayed 
under the count, or on the other side perhaps?  

I might come back to this later as a way to challenge myself to update
a previous program, but for now I am done with this project.   

