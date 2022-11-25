# Jordle
A java specific version of the popular game Wordle.

To compile the application please run:

javac --module-path javafx-sdk-11.0.2/lib/ --add-modules=javafx.controls Jordle.java

in the folder with all relevant files.

To run the application please run:

java --module-path javafx-sdk-11.0.2/lib/ --add-modules=javafx.controls Jordle    

in the folder with all relevant files.

I have provided the javafx-sdk-11.0.2 with this commit.

A project as part of the JavaFX module of the Intro to Object Oriented Programming Class at Georgia Tech (CS 1331).
Implemented UI design methodology and OOP principles to design a smooth gameplay experience.
Added a dark mode and a light mode radio button group. Automatically changes the theme of both the instructions and the main Jordle page.
Used a hash map to keep track of duplicate letters. Prioritizes the letters in the right positions first. 
Added an animation for getting the letter and its position right by scaling the panel out and back in.
Added an animation for getting the letter right but its position wrong by rotating the panel and making it look like it is shaking.


