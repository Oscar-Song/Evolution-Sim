pipeline {
    agent any

    stages {

      stage ('Verify Stage') {

          steps {
              withMaven(maven : 'maven_3_5_4') {
                  echo 'Verifying'
                  sh 'mvn clean validate'
                  }
              }
        }

        stage ('Compile Stage') {

            steps {
                withMaven(maven : 'maven_3_5_4') {
                    echo 'Compiling'
                    sh 'mvn compile'
                }
            }
        }

        stage ('Testing Stage') {

            steps {
                withMaven(maven : 'maven_3_5_4') {
                    echo 'Testing'
                    sh 'mvn test'
                }
            }
        }
        stage ('Documenting Stage'){

            steps {
                withMaven(maven : 'maven_3_5_4') {
                    echo 'Documenting'
                    sh 'mvn site'
                }
            }
        }
        stage ('Deploying Stage'){

            steps {
                withMaven(maven : 'maven_3_5_4') {
                    echo 'Deploying'
                    sh 'mvn deploy'
                }
            }
        }

    }
}
