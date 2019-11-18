# MovieDisplay:
This ia a Application Where user can Show Popular Movie and Top Rated Movie.

# Perquisites:
    1. Java 8
    2. Android Studio
    3. Android SDK above [version: 26] 
    4. Register in Movie_DB (https://api.themoviedb.org/3/)  and get the API_KEY.


# What Can be learn from this Code:
In this Project, I used the best practice of a Project Architecture. I used my J2EE concept in Android Development. 

# Used Android Components:
       1. Activity 
       2. Fragments 
       3. LiveData 
       4. Retrofit 
       5. ViewModel 
       6. RecyclerView 
       7. CardView 
       8. DataBinding
       9. RxJava
      10. Picasso API 

# How to Set Movie DB API Key
 
 Change the value of the ext Variable [moviedb_api_key] from [app/build.gradle]. Please replace the [XXXXX] of line [ moviedb_api_key = "XXXXX" ] of the following [ext { }] blocks by Movie DB API Key.

# Example:

    ext {
        retrofit_version = "2.6.2"
        picasso_version = "2.71828"
        moviedb_api_key = "XXXXX"
        room_version = "2.2.1"
        flex_version = "1.1.1"
    }


 
