Feature: DemoApp
  @android
  Scenario: Android demo
    Given launch the app
    When click APIDemoPage::Views
    And swipe 1000 pixels UP from 200,500 on the screen
    And swipe 1000 pixels DOWN from 200,500 on the screen
    And tap LEFT of the screen and swipe UP
    And swipe UP 10 times until APIDemoPage::WebView is displayed

  @ios
  Scenario: iOS demo
    Given launch the app
    When click APIDemoPage::Login
