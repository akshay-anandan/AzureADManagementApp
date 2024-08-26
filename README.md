# Azure AD Management App

A Java Spring Boot web application to manage Azure AD components much faster than through the portal. This application utilizes the Azure Graph API as well as the ServiceNow API.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Contributing](#contributing)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/akshay-anandan/AzureADManagementApp.git
   ```
2. Install dependencies:
   ```bash
   mvn clean install
   ```
3. Set up environment variables if necessary:
   ```bash
   cp .env.example .env
   ```
4. Set up local server:
   ```bash
   Install Apache Tomcat or any tool that will let you run a web app in localhost
   ```

## **Usage**

To configure the application:
```bash
Add your associated Azure client_id & client_secret & request url within the GraphToken class
```
```bash
Add your associated ServiceNow authString (secret) & request url within the ServiceNow class
```

## **Features**
Features include:
```bash
Creating/Deleting a Group
```
```bash
Adding/Removing Members from Groups 
```
```bash
Adding/Removing Owners from Groups
```
```bash
ServiceNow Integration
```

## **Contributing**
Contributions are welcome! Please follow these steps:

Fork the repository.
Create a new branch (git checkout -b feature-branch).
Make and commit your changes (git commit -m 'Add new feature').
Push to the branch (git push origin feature-branch).
Create a Pull Request.
