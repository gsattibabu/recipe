Introduction :

The application provides RESTfull API to retrive Food Recipe based on category, title or all the recipe at one shot.
Additional to retrieving recipe, app also provides API to validate and save a new recipe.

Prerequisite to run the app :
- apache maven 3 or higher
- java 8 or higher

Below are the steps to be followed to get the API's up and running :

1.	Clone the source code from git in local directory (for example : C:\users)
$ git clone https://github.com/mbyregow/recipeApp.git

2.	Move to the root directory of the RecipeApp project (C:\users\RecipeApp) and to build the source code and run the test cases below using below maven command

mvn clean install

3.	To start the application below command can be used

java -jar target/RMA-0.0.1-SNAPSHOT.jar


Reqest and Response :

Sample Request to save new recipe

{
        "recipeTitle": "Test Recipe",
        "recipeYield": "2",
        "cookingTime": "1 hour",
        "catagories": [
            {
                "catogoryId": 2,
                "description": "Chili"
            }
        ],
        "ingrediants": [
            {
                "title": "New",
                "ingrediant": [
                    {
                        "item": "Ground chuck or lean ground; beef",
                        "quantity": "1",
                        "unit": "pound"
                    },
                    {
                        "item": "Kidney beans; (12 oz)",
                        "quantity": "1",
                        "unit": "can"
                    }
                ]
            }
        ],
        "directions": [
            {
                "stepDetails":"test direction"
            }
        ]
    }

