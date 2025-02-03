pipeline {
    agent any

    environment {
        EC2_USER = '<ec2_user>'
        EC2_HOST = '<e2_host>'
        EC2_KEY_PATH = '<path_to_aws_key>'
        APP_NAME = '<app_name>'
        APP_PATH = '<app_path>'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Hungpai/jagua-backend.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Deploy to EC2') {
            steps {
                bat """
                scp -i $EC2_KEY_PATH target/$APP_NAME $EC2_USER@$EC2_HOST:$APP_PATH/
                ssh -i $EC2_KEY_PATH $EC2_USER@$EC2_HOST "./run.sh $APP_NAME"
                """
            }
        }
    }

    post {
        success  {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}