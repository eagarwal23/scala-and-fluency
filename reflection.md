The goal of our redesigned Picture library was to make it easer to compose
operations without having to load and save an image each time. We accomplished
that goal, now it's time for critique. In this round of critique we're focusing
on the following question:

Is there anything that is still annoying or difficult about the operations we
have in the library (i.e., flipping, rotating, and grayscaling)?

I actually found it to be extremely convenient that instead of having to write out several lines of code, I was able to perform all three actions on the image within a single line. This, I believe, was because we no longer needed to save and and load the image into each function since the input and output type for each was a BufferedImage. However, the only modification I think could've been made would be to refactor the code since there are several repeated sections for each function, for instance, creating a new BufferedImage. Another thing I found to be inconvenient was that in order to load an image into the program, the user has to type out the entire path to it; I think it would be extremely convenient to thus create a function which would instead allow the user to simply write out the name of the image to import it.
