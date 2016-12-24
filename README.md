# This repository is for the Applicaster Task, created by Eli Levi(eli.levi.1320@gmail.com)
### Name : Twitter hashtag reader android app ###

### To get started: ###
- Validate that you have **Android Studio 2.2.3**(most new).
- Validate that you have **sdk 25**, **build tools 25.0.2** installed and **build gradle 2.2.3**.
- Download the repository by clicking **Clone or download** and Download zip file.
- Extract the zip and import to Android studio, select **open an existing Android Studio project** or just **file->open** (if you have already opened project) .
- Check the **local.properties** if sdk dir point to your android sdk.
- If evrything OK you can build and run.

### Using and terms: ###
- Typing hashtag in the toolbar action search button and click on next/search in the keyboard.
- If you write hashtag without "#" in the beginning it automatically added for you, so you can search without typing "#" every time.
- If you want to refresh the results I added swipe to refresh layout.
- The list are refreshed every 10 second with the current hashtag.
- The list will contain 20(if exists) related recent tweets.

### Test: ###
- I added UI automation test using the espresso test environment, for running the test: Go to the package name androidTest right click on it and select run Tests...
- When the test run it will inject UI event, the tests will check :
  - Search empty hashtag and assert nothing happen.
  - Search for "#" hashtag, because it's illegal and need to return empty to.
  - Search for "#test" hashtag and assert there is results.
  - Search for empty hashtag again and assert that nothing happened to previous results.
  - Search for string in length of 140 chars and assert that nothing happened to previous results.
  - Search for string in length of 200 chars and assert that nothing happened to previous results, this because that the string will be cut to the first 140 chars so I want to check it.
- Meanwhile the list are refreshed every 10 second during the test, so I wanted to test that the data still valid and nothing goes bad.
