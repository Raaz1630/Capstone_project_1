pipeline {
    agent any 

    tools {
        maven 'Maven'
        jdk 'Java-17'
    }

    environment {
        // Using SonarQube environment variable for Sonar login
        SONAR_SECRET = credentials('sonarqube-token') // Ensure SONAR_SECRET is stored in Jenkins credentials
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Raaz1630/Capstone-project.git', branch: 'main'
            }
        }

        stage('Static Code Analysis Using SonarQube') {
            steps {
                withSonarQubeEnv('sonarqube-10.6') {
                    sh "mvn clean verify sonar:sonar -Dsonar.login=$SONAR_SECRET"
                }      
            }
        }

        stage('Stage-1 : Clean') { 
            steps {
                sh 'mvn clean'
            }
        }

        stage('Stage-2 : Validate') { 
            steps {
                sh 'mvn validate'
            }
        }

        stage('Stage-3 : Compile') { 
            steps {
                sh 'mvn compile'
            }
        }

        stage('Stage-4 : Test') { 
            steps {
                sh 'mvn test'
            }
        }

        stage('Stage-5 : Install') { 
            steps {
                sh 'mvn install'
            }
        }

        stage('Stage-6 : Verify') { 
            steps {
                sh 'mvn verify'
            }
        }

        stage('Stage-7 : Package') { 
            steps {
                sh 'mvn package'
            }
        }

        stage('Quality Gate Check') { 
            steps {
                script {
                    timeout(time: 5, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }

        stage('Stage-8 : Deploy an Artifact to Artifactory Manager i.e. Nexus/Jfrog') { 
            steps {
                sh 'mvn deploy'
            }
        }

        stage('Stage-9 : Deploy WAR to Tomcat') { 
            steps {
                script {
                    def warFile = findFiles(glob: 'target/*.war')[0].name
                    sh """
                    curl -u admin:Rajesh@123 -T target/${warFile} "http://3.90.188.137:8080/manager/text/deploy?path=/capstone-app&update=true"
                    """
                }
            }
        }

        stage('Stage-10 : Smoke Test') { 
            steps {
                sh 'curl --retry-delay 10 --retry 5 "http://3.90.188.137:8080/capstone-app"'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace'
            cleanWs()
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs for details.'
        }
    }
}
