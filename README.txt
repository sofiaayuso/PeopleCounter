People Counter
Hinge Engineering Homework Assignment
Candidate: Sofia Ayuso

- For this assignment, the architecture pattern I used is MVC. The layers of this model are related to my code in the following way:

	Model: My MainActivity class. It contains all the business logic. This class also handles the logic to store data through the different states of the application's lifecycle and is owned by the Controller (the activity itself). This layer is what preserves the UI and counters across sessions.

	View: The XML layout. All my files containing colors, strings, and themes  belong in this layer, as well as my activity_main.xml which takes care of the UI configuration as a whole. My View provides the visualization of the data in my MainActivity class (Model) but has no knowledge of it. This is also owned by the Controller.

	Controller: My Main Activity. It establishes the relationship between my logic and UI (aka my Model and View) and updates these as needed.

I chose this architecture because of the quick implementation and clear separation of concerns between layers. One disadvantage is that the Controller can be hard to manage for complex and extensive code. The code for this app is simple so that was not an issue.

I also considered the MVVM model because it helps with scalability and is great for testing. However, MVVM has a more complex implementation and requires more code. This People Counter app has a basic purpose and is not hard to test, so I ultimately decided on the simpler MVC model.


- Another decision I made related to architecture was using SharedPreferences instead of an SQLite database to preserve the UI and counts across sessions. This is because the configuration details that I need to save do not have a specific structure and are also a small amount of data. I can save the UI configuration and counters using the key-value pairings that SharedPreferences offer. If I needed to store larger amounts of data with more flexibility in terms of structure, I could have chosen an SQLite database instead.


- Lastly, I considered making an interface with methods needed for saving state instead of having this code in the Controller. This way, it would be easier to change from SharedPreferences to an SQLite database in the future if more complex data storage was needed. Because of the simplicity of the app, I did not implement the interface and left the saving state functionality in the Controller.