End User Documentation
Project Overview
Module Name: MIS

Purpose:
The MIS module is designed to manage user authentication and password updates. It provides endpoints for user registration, login, and password management, including sending emails and verifying OTPs for password updates.

Installation
Prerequisites:

Java Development Kit (JDK) 11 or higher
Apache Maven
Spring Boot
Steps:

Clone the repository to your local machine.
Navigate to the project directory.
Run the following command to build the project:
mvn clean install
Start the application using:
mvn spring-boot:run
Configuration
CORS Configuration:

The module allows cross-origin requests from https://misapp.netlify.app and http://localhost:5173.
Configured to accept HTTP methods: GET, POST, PUT, DELETE, OPTIONS.
Security Configuration:

Basic HTTP authentication is enabled.
In-memory user details are configured with default users: user and admin.
Usage
Authentication
Register User:

Endpoint: POST /api/auth/register
Description: Registers a new user.
Request Body: RegisterUserDao (JSON)
Login User:

Endpoint: POST /api/auth/login
Description: Authenticates a user.
Request Body: LoginDao (JSON)
Password Management
Update Password:

Endpoint: PUT /api/user/updatePassword
Description: Updates the user's password.
Request Body: UpdatePasswordDao (JSON)
Send Email:

Endpoint: POST /api/user/updatePassword/email
Description: Sends an email for password update.
Request Body: SendEmailDao (JSON)
Check OTP:

Endpoint: POST /api/user/updatePassword/check
Description: Verifies the OTP sent to the user's email.
Request Body: CheckOTPDao (JSON)
Troubleshooting
Common Issues:

Ensure all dependencies are installed and configured correctly.
Verify that the application is running on the correct port.
Error Messages:

Authentication errors may occur if incorrect credentials are provided.
Support and Resources
For further assistance, please contact the support team at [support@example.com].
Additional resources and documentation can be found on the project's GitHub repository.
