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
    }
}
