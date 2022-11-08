@ChangeProfilePic
Feature: Change Profile Pic Module API Automation

  Scenario Outline: Verify Change profile pic to the application through API
    Given User add Headers and Bearer authorization for form data
    When User add request body for Change Profile Pic
    And User Send "POST" request for ChangeprofilePic endpoint
    Then User verify the status code is 200
    Then User verify the Change Profile pic response message matches "Profile updated Successfully"
