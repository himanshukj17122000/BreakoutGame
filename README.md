game
====

This project implements the game of Breakout.

Name: Himanshu Jain

### Timeline

Start Date: 01/13/2020

Finish Date: 01/20/2020

Hours Spent: 25 

### Resources Used
Stack Overflow for doubts 
Javatpoint, Tutorialspoint and the official documentation of JavaFX
Youtube videos like newboston 

### Running the Program

Main class: src/breakout/Main

Data files needed: All of them have been included in the resources folder. Added some from google 

Key/Mouse inputs: 'W' for fast paddle and fast ball, 'U' for slowing the ball, 'Space' for elongating the paddle, 'C' for making the paddle's width cover the screen, 
'R' for restarting the game, 'P' for starting the ball from its position, 'L' for an extra life, '1' for Level 1, '2' for Level 2, '3' for Level 3,
Arrow keys for playing the game

Cheat keys: 1,2,3 change levels; L adds a life; 'C' makes the width of the paddle equal to the screen width; 'W' for fast paddle and fast ball; 'R' for restarting the level

Known Bugs: The ball's speed increases every time a new game starts. This can be decreased by using the U key but I tried resetting the bouncer speed in multiple functions but for some reason,
it still increases the speed. I also believe that when the ball collides with the bricks, it counts the collision as multiple strikes so even though the bricks have different number of hits,
they all break when the ball hits them. 

Extra credit: The paddle moves up when only 10 bricks are left and the game becomes upside-down when only 4 bricks are left; Special bomb brick that destoys the top and the bottom
bricks; audio is played when a brick is destroyed 


### Notes/Assumptions
I had a number of boolean values that I use to check for power-ups like elongating the paddle. I have also used the inbuilt timer function to change the power-up values after 10 seconds. 
In checking the collision of the ball with the paddle, I have added a buffer of 3 on the sides to make the bouncing off movement better. I have also assumed that the ball changes just the Y direction 
after hitting a brick. I have assumed that the screen size won't be changed since some values are according to the screen size and might get distorted if the screen size changes. 

To play the game after many tries, I would recommmend using 'U' to decrease the speed of the ball as the ball becomes really fast and it's really hard to get to it. I have made the starting direction of the ball
random and the brick's configuration also change in every game as I use the random function for decising the brickwall's distance from the top of the screen. 



### Impressions
I believe that working through the project taught me a lot about encountering a problem and solving it without assistance from someone. I learnt a great amount of 
troubleshooting. I also think the project seemed daunting at first but became clear when I tried breaking it down into smaller pieces of code. I think there are some 
lagging issues with JavaFX that could have been better with other technologies. 


