pipeline {
  agent any
  stages {
    stage('test Version') {
      steps {
        sh '''mvn --version;        
java -version'''
      }
    }

    stage('Test unitaire') {
      steps {
        sh 'mvn test'
      }
    }

  }
}