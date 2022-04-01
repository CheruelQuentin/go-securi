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

        stage('') {
          steps {
            sh 'mvn test'
          }
        }

      }
    }

  }
}