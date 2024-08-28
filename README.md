# JavaEE Point of Sale (POS) System API

## Overview

The JavaEE Point of Sale (POS) System API is a robust backend solution designed to manage core POS functionalities such as customer management, item inventory, order processing, and detailed order tracking. Built using Jakarta EE, this API ensures enterprise-grade scalability and performance, making it ideal for businesses aiming to streamline their point-of-sale operations.

## Objectives

- **Ease of Integration**: Provides a simple yet powerful API to facilitate quick integration into various POS systems.
- **Scalability**: Designed to handle a large number of transactions and users without performance degradation.
- **Security**: Follows best practices in database configuration and data handling to ensure a secure and reliable system.

## Technologies Used

- **Jakarta EE Version 9**: The core framework for developing the application, offering robustness and scalability.
- **Tomcat Version 11.0.0**: The application server used for deploying the Jakarta EE-based backend.
- **MySQL Version 8.2**: Manages the relational database, ensuring data integrity and performance.
- **Parsson Version 1.1.2**: Used for JSON processing and binding within the application.
- **Yasson Version 2.0.4**: Implements Jakarta JSON Binding for seamless JSON serialization and deserialization.

## Key Features

- **Customer Management**: Full CRUD (Create, Read, Update, Delete) operations for managing customer data.
- **Item Management**: Maintain inventory with functionalities for adding, updating, and deleting items.
- **Order Management**: Efficient handling of orders, including creation and tracking of orders and order details.
- **Dynamic Content Loading**: Utilizes AJAX/Fetch API for asynchronous communication between the client and server.
- **Logging**: Comprehensive logging strategy across different levels (INFO, DEBUG, ERROR, WARN) for maintainability and troubleshooting.

## Setup & Installation

### Prerequisites

- **Jakarta EE Environment**: Ensure your development environment is configured with Jakarta EE.
- **Tomcat Server**: Install Tomcat version 11.0.0 or later.
- **MySQL Database**: Set up MySQL version 8.2 for managing the database.

### Steps to Set Up

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/Sam1ndaFernando/Web-POS-System-Backend.git
    ```

2. **Configure MySQL Database**:
    - Create a new database for the POS system.
    - Update the JNDI configuration in your Jakarta EE application server’s context file to point to the MySQL database.

3. **Deploy the Application**:
    - Build the project using your preferred tool (e.g., Maven).
    - Deploy the project artifact to the Tomcat server.
    - In Tomcat, go to **Edit Configuration > Deployment** and add your project artifact.

4. **Access the Frontend**:
    - Use the provided frontend URL or integrate with a custom frontend:
      - **Frontend URL**: https://sam1ndafernando.github.io/Web-POS-System-Frontend/
      - **Frontend GitHub Repository**: https://github.com/Sam1ndaFernando/Web-POS-System-Frontend.git

## Licensing

This project is released under the MIT License, allowing flexibility for modification, distribution, and private use. See the [MIT LICENSE](LICENSE) file for more details.
```

This README file is clear and provides a comprehensive guide to understanding, setting up, and using the JavaEE POS System API.
