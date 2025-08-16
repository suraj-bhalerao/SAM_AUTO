pipeline {
    agent any

    tools {
        maven 'Maven 3.9.10'
        jdk 'Java 21'
    }

    environment {
        BROWSER = 'chrome'
        HEADLESS = 'true'
        HEADED = 'false'
    }

    triggers {
        cron('H 10 * * *') // runs daily at 10 AM (10:00 hours)
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/suraj-bhalerao/SAM_AUTO.git'
            }
        }

        stage('Verify Environment') {
            steps {
                sh 'mvn -v'
                sh 'java -version'
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean compile -Dbrowser=${BROWSER} -Dheadless=${HEADLESS}"
            }
        }

        stage('Run Tests') {
            steps {
                sh "mvn test -Dbrowser=${BROWSER} -Dheadless=${HEADLESS}"
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML([
                    reportDir: 'reports',
                    reportFiles: 'index.html',
                    reportName: 'Test Report'
                ])
            }
        }
    }
}
