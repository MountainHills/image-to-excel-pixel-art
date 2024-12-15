# image-to-excel-pixel-art
As the name suggest, ***image-to-excep-pixel-art*** converts any image format into pixel art. However, the output would be saved as an **(a) image (.jpg)** and an **(b) Excel file (.xlsx)**.

The project was created with a very simple mission in mind: *create small projects to inspire other developers to create.*

This project will showcase that a project doesn't have to be grand. It doesn't have to solve a major problem also. It's simply about having fun and playing around.

A project only needs to be something you're interested in.

> *No project is ever to small to create.*
# Design
The design is very simple.
1. Get a random image from the internet or your computer
2. Run the system
3. Generate a pixel art from the given image in Excel

![Program Design](https://github.com/MountainHills/image-to-excel-pixel-art/blob/main/docs/program-design.png?raw=true)
### Example
Here is an example image of *Lebron James* turned into pixel art.
![Pixelation Example](https://github.com/MountainHills/image-to-excel-pixel-art/blob/main/docs/pixelation-output.png?raw=true)

That's it. The program has only one function in mind. Turn the image into a pixel art and put it on an Excel file. There's no gimmick nor tricks behind it. It's really that simple.
# Logic
Here we will discuss the decisions I've made to implement this program.

I've learned a thing or two when creating this project. I learned more about pixelation algorithms, the Apache POI library, and some *prompt engineering.*

### Pixelation Algorithm
When I was first starting this project, I have no idea how to pixelate an image.

So, I searched about it and learned that there are multiple algorithms that you can use to pixelate an image.

Admittedly, I was overwhelmed and didn't know which one's the simplest and the most time-efficient.

So, I used ChatGPT (*wow! shocking. no. it's not. use your tools to make your life easier*) to let it handle the pixelation.

I originally thought about implementing the simplest pixelation, but I was lazy and decided to use the in-built image scaler in Java to achieve the same effect.

![Pixelation process](https://github.com/MountainHills/image-to-excel-pixel-art/blob/main/docs/pixelation-process.png?raw=true)

### Excel File
This one is easy.

I used *Apache POI* and looked up the documentation how to create a workbook, sheet, how to style a cell, and all that crap.

It was so easy to be honest, since the library provides a decent documentation to follow with examples.

The idea here was that I'm going to use the original pixelated image, get the width and height. Go through each pixel and its color. I will use that color to style the cell.

From there, it's just two for loops for rows and columns. Then, we get the final output.

![Excel Pixel Art](https://github.com/MountainHills/image-to-excel-pixel-art/blob/main/docs/excel-pixel-art.png?raw=true)
# Final Output
Here's a GIF so you could see how it all goes down.
![image-to-excel-pixel-art](https://github.com/MountainHills/image-to-excel-pixel-art/blob/main/docs/image-to-excel-pixel-art.gif?raw=true)