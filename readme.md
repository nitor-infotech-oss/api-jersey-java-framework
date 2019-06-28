# Iugo Test Automation Framework and TestSuite

## Table of Contents
- [Iugo Middleware Test Automation Framework and TestSuite](#iugo-test-automation-framework-and-testsuite)
  * [Setup](#setup)
  * [Usage](#usage)
  
## Setup
Follow below steps:
1.	Download Eclipse
2.	Within Eclipse, 

	a.	Install TestNG-Eclipse plugin

	b.	install M2E plugin of Maven
		
3.	Download both repos on your system
4.	Within Eclipse

	a.	Import both as maven projects in Eclipse

	b.	Go to J-Dux, run maven configuration with “install” task
	
	![Iugo Install Task](./images/install-iugo.png)

	c.	Go to IUGO, run maven configuration with “clean test” task
	
	![Iugo Clean Test Task](./images/clean-test-iugo.png)

## Usage
1.	Check run configuration referred in above step 4c. Just make sure that you have correct parameters given in "environment" and "tests"



