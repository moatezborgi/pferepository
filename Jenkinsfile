pipeline {
    agent any
    stages {
        stage('Checkout the Git repository') {
            steps {
                script {
                    // Checkout the Git repository
                    git branch: 'main',
                        credentialsId: '128ac0fc-0848-4e5a-ac76-38d38c204400',
                        url: 'https://github.com/moatezborgi/pferepository.git'
                }
            }
        }
      
          stage("Build Artifact") {
            steps {
                sh 'mvn clean'
                sh 'mvn compile'
                sh 'mvn package -DskipTests'
            }
        }
          stage('SonarQube Analysis') {
            steps {
                // Ajoutez cette commande pour exécuter l'analyse SonarQube
                    sh 'mvn sonar:sonar -Dsonar.host.url=http://172.26.83.7:9000 -Dsonar.login=admin -Dsonar.password=1234'
            }
        }
         stage("Docker Build"){
           steps{
                sh 'docker build -t moatezborgi/pferepository .'
             }
    }
    }
}
