@Searchproduct
Feature: Search Product Module API Automation

  Scenario Outline: Verify Search Product to the application through API
    Given User add Headers for Search products
    When User add request body for Search product "<ProductName>"
    And User Send "POST" request for Search product endpoint
    Then User verify the status code is 200
    Then User verify the search product response message matches "OK"

    Examples:
      | ProductName |
      | nuts        |