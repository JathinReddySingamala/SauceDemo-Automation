pipeline {
    agent any

    parameters {
        choice(name: 'group', choices: ['smoke', 'regression', 'positive tests', 'negative tests'], description: 'Select test group')
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
                    // Initialize default values from parameters
                    def selectedGroup = params.group
                    def selectedBrowser = params.browserName

                    // Get latest commit message
                    def commitMsg = bat(script: 'git log -1 --pretty=%%B', returnStdout: true).trim()
                    echo "Commit message: ${commitMsg}"

                    // Update values based on commit message keywords
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
                    } else {
                        echo "No keyword found in commit message, using parameter defaults."
                    }

                    echo "Final Selected Group: ${selectedGroup}"
                    echo "Final Selected Browser: ${selectedBrowser}"

                    // Run Maven command with resolved values
                
                bat """
                mvn test ^
                -DsuiteXmlFile=testng.xml ^
                -Dgroups="${selectedGroup}" ^
                -Dbrowser="${selectedBrowser}"
                """

                    

                }
            }
        }
    }
}
