pipeline {
  agent any
  stages {
    stage('Mvn and Java version') {
      steps {
        sh '''mvn --version;        
java -version'''
      }
    }

    stage('Unit tests') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Create jar') {
      steps {
        sh 'mvn clean;mvn install ;mvn compile assembly:single;'
      }
    }

    stage('Execute jar') {
      steps {
        sh 'java -jar gosecuri.jar /Documents/mspr;pwd'
      }
    }

    stage('Send files to web server') {
      steps {
        sh 'cd /Documents/mspr;sshpass -p "azerty" scp *.html quentin@192.168.1.42:/var/www/html;'
      }
    }

  }
}