# new feature
# Tags: optional
@bdd_demo
Feature: Create new repository

  Scenario: Login github website
    Given open ${host}
    * click MainPageAdv::SignInLnk
    * input ${username} into LoginPageAdv::UsernameFld
    * input ${password} into LoginPageAdv::PasswordFld
    When click LoginPageAdv::SignInBtn
    Then CHECK MainPageAdv::UserProfileImg visibility

  Scenario: Create new repo
    Given def repoName=_Random
    * click TopBarPage::CreateNewDropDown
    * click TopBarPage::NewRepoBtn
    * input ${repoName} into NewRepositoryPage::RepoNameFld
    * input This is a temp repo for demonstration into NewRepositoryPage::RepoDescFld
    * click NewRepositoryPage::PublicRadioBtn
    When click NewRepositoryPage::CreateRepoBtn
    Then CHECK hyperlink with text ${repoName} visibility

#  Scenario: verify repo through API
#    Given repo - 001 - get repository
#    * request method GET
#    * request headers ""
#    * request parameters ""
#    * request payload ""
#    When send request to /repos/${owner}/${repoName}
#    Then the status code is 200
#    * CHECK response {'name':'${repoName}'}
#
  Scenario: delete new repo
    Given click RepositoryPage::Setting
    And click RepositoryPage::DeleteRepo
    And input ${owner}/${repoName} into RepositoryPage::VerifyField
    When click RepositoryPage::SubmitBtn
    Then CHECK text Your repository "${owner}/${repoName}" was successfully deleted. on page
    And close the browser

    #  Scenario Outline: delete new repo
#    Given click TopBarPage::ViewProfileDropDown
#    Given click TopBarPage::RepoListBtn
#    Given get //a[@itemprop='name codeRepository'] with index 1 text and save as variable repoName
#    Given click //a[@itemprop='name codeRepository'] with index 1
#    Given click RepositoryPage::Setting
#    And click RepositoryPage::DeleteRepo
#    And input ${owner}/${repoName} into RepositoryPage::VerifyField
#    When click RepositoryPage::SubmitBtn
#    Then CHECK text Your repository "${owner}/${repoName}" was successfully deleted. on page
#    Examples:
#      |no|
#      |no|
