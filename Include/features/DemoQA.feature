#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
	Scenario Outline: Forms Registration with DDT
	  Given User mengakses halaman registrasi form DemoQA
	  When User mengisi form dengan data: "<FirstName>", "<LastName>", "<DOB>", "<Email>", "<Gender>", "<Mobile>", "<Subject>", "<Hobbies>", "<Picture>", "<Current Address>", "<State>", "<City>"
		Then Hasil form submission harus diverifikasi: "<TC_ID>", "<FirstName>", "<LastName>", "<DOB>", "<Email>", "<Gender>", "<Mobile>", "<Subject>", "<Hobbies>", "<Picture>", "<Current Address>", "<State>", "<City>", "<ExpectedResult>"

  Examples: 
	|TC_ID  |FirstName  |LastName|DOB				  |Email					 |Gender |Mobile		   |Subject	 					|Hobbies						|Picture					 |Current Address	|State				 |City			|ExpectedResult   |
	|TC-P01 |John 			|Doe		 |12 FEB 1990 |john@test.com	 |Male	 |1234567890   |Maths, Physics    |Sports, Reading    |john.png					 |123 Main St			|NCR					 |Delhi		  |Success				  |
	|TC-N01 |Will		  	|Smith   |12 FEB 1990	|smith@.com	 		 |Male	 |9876543210   |Chemistry					|Music							|will.png					 |456 Elm St			|Haryana			 |Karnal    |Fail			        |
	|TC-N02 |Alice	  	|wonder	 |12 FEB 1990	|alice@test.com	 |Female |08788302     |Computer Science	|Music, Reading			|alice.png 				 |789 Oak Ave			|Uttar Pradesh |Lucknow		|Fail     			  |
	|TC-N03 |Peter			|Parker	 |12 FEB 1990	|peter@test.com	 |Male   |ABCDEFG      |Biology						|Reading						|spider.png 			 |321 Wall St			|Rajasthan		 |Jaipur		|Fail        			|
	|TC-N04 |Bob				|White	 |12 FEB 1990	|bob@.com				 |Other  |1112223334   |English						|Sports, Music			|bob.png	 				 |543 River Rd		|Uttar Pradesh |Agra		  |Fail       			|
	|TC-N05 |Charlie		|Brown	 |12 FEB 1990	|charlie@test.com|       |123				   |Economics					|Reading, Sports		|charilie.png			 |987 Peak Ave		|Rajasthan		 |Jaiselmer |Fail 						|
	|TC-N06 |David			|   	 	 |12 FEB 1990	|david@test.com  |Female |9998887776   |Chemistry					|Reading						|david.png	       |654 Lake Blv		|NCR				 	 |Noida		  |Fail     				|
	|TC-N07 |David			|Lee		 |12 FEB 2026	|david@test.com  |Female |9998887776   |Chemistry					|Reading						|david.png       	 |654 Lake Blv		|NCR					 |Noida		  |Fail     				|
