pipeline {
    agent any

    stages {

        stage('checkout'){
            steps {
                git url: 'https://github.com/chilager/jgsu-spring-petclinic.git'
                branch: 'main'
            }
        }

        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                                
                sh './mvnw clean package'

                // Run Maven on a Unix agent.
                //sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }

          
        }
    }
}
