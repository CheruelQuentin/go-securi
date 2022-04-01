pipeline {
  agent any
  stages {
    stage('test Version') {
      parallel {
        stage('test Version') {
          steps {
            sh '''mvn --version;        
java -version'''
          }
        }

        stage('error') {
          steps {
            sh 'mvn test'
          }
        }

      }
    }

    stage('Test unitaire') {
      steps {
        sh 'mvn test'
      }
    }

  }
}