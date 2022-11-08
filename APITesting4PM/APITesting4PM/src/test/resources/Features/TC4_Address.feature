Feature: Address Module API Automation

  Scenario Outline: Verify add user address to the application through api
    Given User add header and bearer authorization for accessing address endpoints
    When User add request body for add new address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>",and "<address_type>"
    And User send "POST" request for addUserAddress endpoint
    Then User verify the status code is 200
    Then User verify the addUserAddress response message matches "Address added successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address | address_type |
      | Mani       | Raj       | 9944152058 | Prince    |   123 |   11 |     101 |  600081 | Chennai | Home         |












  Scenario Outline: Verify update user address to the application through api
    Given User add header and bearer authorization for accessing address endpoints
    When User add request body to update new address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>" and "<address_type>"
    And User send "PUT" request for update addUserAddress endpoint
    Then User verify the status code is 200
    Then User verify the update address response message matches "Address updated successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address | address_type |
      | Mani       | Raj       | 9944152058 | Prince    |   123 |   11 |      22 |  600081 | Chennai | Home         |

  Scenario Outline: Verify Get User Address to the application through API
    Given User add Headers and Bearer authorization for accessing Get address endpoints
    When User Send "GET" request for GetUserAddress endpoint
    Then User verify the status code is 200
    Then User verify the GetUserAddress response message matches "OK"

  Scenario Outline: Verify Delete User Address to the application through API
    Given User add header and bearer authorization for accessing address endpoints
    When User add request body for address id
    And User Send "DELETE" request for DeleteAddress endpoint
    Then User verify the status code is 200
    Then User verify the DeleteAddress response message matches "Address deleted successfully"
