# MovieDisplay:
This ia a Application Where user can Show Popular Movie and Top Rated Movie.

# What Can be learn from this Code:
In this Project, I used the best practice of a Project Architecture. I used my J2EE concept in Android Development. 

# Used Android Components:
1. Activity 2. Fragments 3. LiveData 4. Retrofit 5. ViewModel 6. RecyclerView 7. CardView 8. DataBinding

# How to Set Movie DB API Key
 
 Change the Build Config filed [MOVIEDB_API_KEY] from [app->build.gradle]. 
 #Example:
 defaultConfig {
 
               ....................
               .....................
               buildConfigField "String", "MOVIEDB_API_KEY", "\"{INPUT_API_KEY}\""
               ...................................................................
               }
