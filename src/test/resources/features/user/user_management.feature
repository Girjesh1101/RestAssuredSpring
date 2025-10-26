Feature: User Management API
  As a PetStore API user
  I want to manage user accounts
  So that i cam perform user-related operations

  Background:
    Given I have a test user with username "testuser_${timestamp}"
    And I have the user API service available

    @UserManagement @Smoke
    Scenario:  Create a new user account
      When I create a new user with the following details:
        |firstName |John               |
        |lastName  |gupta              |
        |email     |John.gupta@test.com|
        |password  |John@123           |
        |phone     |9876543210         |
        |userStatus|0                  |
      Then  the user should be created succeesfully
      And the response status code should be 200

    @UserManagement @Regression
      Scenario: Retrieve user by username
        Given  I have created a user with username "testuser_${timestamp}"
        When I retrieve the user by username "testuser_${timestamp}"
        Then the user details should be returned successfully
        And the user response code should be 200
        And the user first name should be "John"
        And  the user last name should be "gupta"


  @UserManagement @Regression
  Scenario: Update user information
    Given I have created a user with username "testuser_${timestamp}"
    When I update the user with username "testuser_${timestamp}" with following details:
      | firstName | Jane           |
      | lastName  | Smith          |
      | email     | jane.smith@test.com |
    Then the user should be updated successfully
    And the response status code should be 200
    And when I retrieve the user by username "testuser_${timestamp}"
    Then the user first name should be "Jane"
    And the user last name should be "Smith"

  @UserManagement @Smoke
  Scenario: Delete an existing user
    Given I have created a user with username "testuser_${timestamp}"
    When I delete the user with username "testuser_${timestamp}"
    Then the user should be deleted successfully
    And the response status code should be 200
    And when I try to retrieve the user by username "testuser_${timestamp}"
    Then the response status code should be 404

  @UserManagement @Negative
  Scenario: Try to retrieve non-existent user
    When I retrieve the user by username "nonexistent_user_999999"
    Then the response status code should be 404
