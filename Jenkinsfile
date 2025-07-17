pipeline {
    agent any

    parameters {
        choice(name: 'group', choices: ['smoke', 'regression', 'positive tests', 'negative tests'], description: 'Select test group (for TestNG or Cucumber)')
        choice(name: 'browserName', choices: ['chrome', 'chromeheadless', 'firefox', 'edge'], description: 'Select browser')
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Determine Parameters from Commit Message and Run Tests') {
            steps {
                script {
                    def selectedGroup = params.group
                    def selectedBrowser = params.browserName
                    def testType = "testng" // default

                    // Get latest commit message
                    def commitMsg = bat(script: 'git log -1 --pretty=%%B', returnStdout: true).trim()
                    echo "Commit message: ${commitMsg}"

                    // Detect test type
                    if (commitMsg.contains("[cucumber]")) {
                        testType = "cucumber"
                    } else if (commitMsg.contains("[testng]")) {
                        testType = "testng"
                    }

                    // Detect test group and browser
                    if (commitMsg.contains("[smoke]")) {
                        selectedGroup = 'smoke'
                        selectedBrowser = 'chromeheadless'
                    } else if (commitMsg.contains("[regression]")) {
                        selectedGroup = 'regression'
                        selectedBrowser = 'chromeheadless'
                    } else if (commitMsg.contains("[positive]")) {
                        selectedGroup = 'positive tests'
                        selectedBrowser = 'firefox'
                    } else if (commitMsg.contains("[negative]")) {
                        selectedGroup = 'negative tests'
                        selectedBrowser = 'edge'
                    } else if (commitMsg.contains("[negative_chrome]")) {
                        selectedGroup = 'negative tests'
                        selectedBrowser = 'chrome'
                    } else if (commitMsg.contains("[positive_chrome]")) {
                        selectedGroup = 'positive tests'
                        selectedBrowser = 'chrome'
                    } else if (commitMsg.contains("[regression_chrome]")) {
                        selectedGroup = 'regression'
                        selectedBrowser = 'chrome'
                    }else if (commitMsg.contains("[smoke_chrome]")) {
                        selectedGroup = 'smoke'
                        selectedBrowser = 'chrome'
                    }

                    echo "Test Type: ${testType}"
                    echo "Group/Tag: ${selectedGroup}"
                    echo "Browser: ${selectedBrowser}"

                    if (testType == "testng") {
                        bat """
                            mvn clean test ^
                            -PTestng ^
                            -Dgroups="${selectedGroup}" ^
                            -Dbrowser="${selectedBrowser}"
                        """
                    } else if (testType == "cucumber") {
                        bat """
                            mvn clean test ^
                            -PCucumber ^
                            -Dtags="@${selectedGroup}" ^
                            -Dbrowser="${selectedBrowser}"
                        """
                    }
                }
            }
        }
    }
}
