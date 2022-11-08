@Login
Feature: Login Module API automation

  Scenario: Get User logtoken from login endpoint
    Given User add header
    When User add basic authentication for login
    And User send "POST" request for login endpoint
    Then User verify the status code is 200
    Then User verify the login response body firstName present as "Greens" and get the logtoken saved
