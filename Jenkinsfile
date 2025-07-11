pipeline {
    agent any

    parameters {
        choice(name: 'group', choices: ['smoke', 'regression', 'positive tests', 'negative tests'], description: 'Select test group')
        choice(name: 'browserName', choices: ['chrome', 'chromeheadless', 'firefox', 'edge'], description: 'Select browser')
    }

    environment {
        // Default to parameter values
        SELECTED_GROUP = "${params.group}"
        SELECTED_BROWSER = "${params.browserName}"
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Determine Parameters from Commit Message') {
            steps {
                script {
                    // Get the latest commit message
                    def commitMsg = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                    echo "Commit message: ${commitMsg}"

                    // Set parameters based on keywords in commit message
                    if (commitMsg.contains("[smoke]")) {
                        env.SELECTED_GROUP = 'smoke'
                        env.SELECTED_BROWSER = 'chromeheadless'
                    } else if (commitMsg.contains("[regression]")) {
                        env.SELECTED_GROUP = 'regression'
                        env.SELECTED_BROWSER = 'chromeheadless'
                    } else if (commitMsg.contains("[positive]")) {
                        env.SELECTED_GROUP = 'positive tests'
                        env.SELECTED_BROWSER = 'firefox'
                    } else if (commitMsg.contains("[negative]")) {
                        env.SELECTED_GROUP = 'negative tests'
                        env.SELECTED_BROWSER = 'edge'
                    }else if (commitMsg.contains("[negative_chrome]")) {
                        env.SELECTED_GROUP = 'negative tests'
                        env.SELECTED_BROWSER = 'chrome'
                    } else if (commitMsg.contains("[positive_chrome]")) {
                        env.SELECTED_GROUP = 'negative tests'
                        env.SELECTED_BROWSER = 'chrome'
                    } else if (commitMsg.contains("[regression_chrome]")) {
                        env.SELECTED_GROUP = 'regression'
                        env.SELECTED_BROWSER = 'chrome'
                    }  else {
                        echo "No keyword found in commit message, using parameter defaults."
                    }

                    echo "Selected Group: ${env.SELECTED_GROUP}"
                    echo "Selected Browser: ${env.SELECTED_BROWSER}"
                }
            }
        }

        stage('Run Maven Tests') {
            steps {
                sh """
                    mvn clean test \
                    -DsuiteXmlFile=testng.xml \
                    -Dgroups='${env.SELECTED_GROUP}' \
                    -Dbrowser='${env.SELECTED_BROWSER}'
                """
            }
        }
    }
}
