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

    stage('exec JAR') {
      steps {
        sh 'java -jar gosecuri.jar $HOME/Documents/mspr;'
      }
    }

    stage('scp') {
      steps {
        sh 'cd $HOME/Documents; pwd;scp mspr/*.html quentin@192.168.1.42:/home/quentin;'
      }
    }

  }
}