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

    stage('Compile') {
      steps {
        sh 'mvn clean;mvn install ;mvn compile assembly:single;'
      }
    }

  }
}