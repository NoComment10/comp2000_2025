# Welcome to COMP2000 - Object Oriented Programming Practices
## Session 2, 2025

Instructions to run

- Run program.
- Click actor cell then click a neighbor cell thats surrounds it to move the actor. Only the orange dog and cream cat is user controlled.
- Move to collect items while avoiding the red birds. The orange dog can only collect the white bone and the cream cat can only collect the blue fish.
- When a valid collection occuers, an announcement will be made in the terminal.
- If the user controlled actor (dog or cat) touch a red bird, the canvas will close and an announcement will be made in the terminal saying game over along with how many items were collected by each actor
- If the user controlled actors (dog and cat) collect all of their respective items, the cavas will close and an announcement will be made in the terminal saying congratulations along with how many items were collected by each actor




Analysis

Interfaces and Inheritance
Through the use of inheritance, I can avoid repeating code that the Bone and Fish items share, this being shape, colour, location, and drawing logic. Placing the common features into the abstract Item class will allow for the simpler common code to be written in one place (the Item class) while allowing me to add code to each individual item that makes it unique, such as the shape. By minimising the duplication, it makes the code easier to read as it is not in multiple files. The design also supports adding future shared features to items like animations or adding conditions to be collected. 

The Collectible interface improves the design by making sure all items define what will happen if the item is collected without defining how. By letting Bone.java and Fish.java to implement their own rules, it maintain consistency in items while allowing for flexibility. The addition of this interface also means that if I wanted to add a new item like buffing (collecting potions) or debuffing items (stepping in poop), then I would only need to extend from Item and implement onCollect without changing the rest of the game logic.


Generics
The class Bag.java uses generics to allow each bag instance to store specific objects that are collected by the actors. For example, the dog actor's bag can only collect bones while the cat's bag only collects fish. This allows me to ensure that they can only collect their respective items. The bag class can also be reused for different future items to create an inventory to let the user choose when to activate items like potions. This reuse prevents the duplication of code, allowing for flexibility. This shows how generics can enforce constraints to allow for the prevention of mistakes at compile time.