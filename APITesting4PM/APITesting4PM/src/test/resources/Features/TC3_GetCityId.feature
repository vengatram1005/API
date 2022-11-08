@State
Feature: State Module API Automation

  Scenario: Verify User Get StateList  through api
    Given User add headers for to StateList
    When User send "GET" request for StateList endpoint
    Then User verify the status code is 200
    Then User verify the stateList response message matches "Tamil Nadu" and saved state id
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  Scenario: Verify User Get City list  through api
    Given User add header  for to get CityList
    When User add request body state id for  get city list 
    And User send "POST" request for CityList endpoint
    Then User verify the status code is 200
    And User verify the cityList response message matches "Adikaratti" and saved city id
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    