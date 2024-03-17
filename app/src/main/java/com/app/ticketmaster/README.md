# Ticketmaster App

This is a sample application that demonstrates the use of the Ticketmaster API to display events in a selected city.

## Features

- **Event Listing:** Users can view a list of events available in a selected city.
- **Event Search:** Users can search specific details of an event by city.

## Installation

1. Clone this repository to your local machine.
2. Open the project in your preferred IDE (such as Android Studio).

## Configuration

Before running the application, make sure you have properly configured the Ticketmaster API key.

1. Obtain a Ticketmaster API key by following the instructions in the [official documentation](https://developer.ticketmaster.com/products-and-docs/apis/getting-started/).

## How to Run

After configuring the API key, you can run the application on an Android emulator or connected device. Make sure you have the Android SDK properly set up in your development environment.

## Architecture

This application follows the MVVM (Model-View-ViewModel) architecture for a clear separation of concerns and ease of maintenance and testing of the code.

- **Model:** Contains the business logic and data of the application.
- **View:** Responsible for displaying data and interacting with the user.
- **ViewModel:** Acts as an intermediary layer between the Model and the View, providing data for display and responding to user actions.

## Technologies Used

- **Kotlin:** Primary programming language.
- **Android Jetpack:** Set of recommended Android libraries by Google.
- **Retrofit:** Library for network communication.
- **Coil:** Library for image loading.
- **Coroutines:** For asynchronous programming.
- **LiveData:** Architecture component for observing changes in data.

## Testing

The application includes unit and integration tests to ensure the proper functioning of the code.

## Contributing

If you want to contribute to this project, follow these steps:

1. Fork this repository.
2. Create a new branch with your feature (`git checkout -b my-feature`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin my-feature`).
5. Create a pull request.


