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

    stage('') {
      steps {
        sh '''mvn clean;
mvn compile assembly:single;
'''
      }
    }

  }
}