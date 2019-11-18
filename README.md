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
 
 Change the value of the ext Variable <b><i>[moviedb_api_key]</i></b> from <b><i>[app/build.gradle]</i></b>. Please replace the <b><i>[INPUT_API_KEY]</i></b> of line <b><i>[moviedb_api_key = "[INPUT_API_KEY]"]</i></b> of the following <b><i>[ext { }]</i></b> blocks by Movie DB API Key.

# Example:

 Before Changing:

    ext {
        retrofit_version = "2.6.2"
        picasso_version = "2.71828"
        <span style="background-color: #FFFF00">moviedb_api_key = "[INPUT_API_KEY]"</span>
        room_version = "2.2.1"
        flex_version = "1.1.1"
    }
    
 After Changing:
  
      ext {
          retrofit_version = "2.6.2"
          picasso_version = "2.71828"
          <span style="background-color: #FFFF00">moviedb_api_key = "as1111........"</span>
          room_version = "2.2.1"
          flex_version = "1.1.1"
      }


 
