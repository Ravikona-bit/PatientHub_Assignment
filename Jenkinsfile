pipeline {
    agent any

    environment {
        DOCKER_IMAGE_TAG = "patient-hub-service:${env.BUILD_NUMBER}"
    }

    stages {
        stage('Build') {
            steps {
                script {
                    // Build the Maven project
                    sh 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image
                    sh "docker build -t $DOCKER_IMAGE_TAG ."
                }
            }
        }

        stage('Push Docker Image to Registry') {
            steps {
                script {
                    // Push Docker image to Docker registry
                    sh "docker push $DOCKER_IMAGE_TAG"
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Deploy Docker image to Kubernetes or any other platform
                    // Example: kubectl apply -f deployment.yaml
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment successful'
        }
        failure {
            echo 'Deployment failed'
        }
    }
}
