pipeline{
 
		agent {
			label 'Slave_Induccion'
		}


		triggers {
			pollSCM('@hourly')
		}

		options {
			buildDiscarder(logRotator(numToKeepStr:'5'))
			disableConcurrentBuilds()
		}
		
		tools{
			jdk 'JDK8_Centos'
			gradle 'Gradle4.5_Centos'
		}

		stages {

			stage('Checkout Git'){
				steps{
				   checkout([$class: 'GitSCM', branches: [[name: '${BRANCH_NAME}']], doGenerateSubmoduleConfigurations: false, extensions: [], gitTool: 'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'GitHub_alexanderzapata27', url: 'https://github.com/alexanderzapata27/RetoTL.git']]])
				}
			}
			 
			stage('Build project') { 
			   steps { 
					sh 'gradle clean'
					sh 'gradle build'
				}
			}

			stage('Unit Test & Coverage') {
				steps {
				    sh 'gradle test'
				    sh 'gradle jacocoTestReport'
				}
			}          
			
			stage('Static code revision SonarQube') {
			    when { anyOf { branch 'develop'; branch 'master' }
			    }
				steps{
				    withSonarQubeEnv('Sonar') {
				      				      sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dsonar.projectKey='RetoTL'.${BRANCH_NAME} -Dsonar.projectName='RetoTL'.${BRANCH_NAME} -Dproject.settings=./sonar-project.properties"
					} 
				    
				}
			}
		}
    
		post { 
			failure { 
				mail( to: 'jhon.zapata@ceiba.com.co' ,
					body: "Build failed in Jenkins: Project: ${env.JOB_NAME} Build /n Number: ${env.BUILD_NUMBER} URL de build: ${env.BUILD_NUMBER}/n/n Please go to ${env.BUILD_URL} and verify the build", 
					subject: "ERROR CI: Project name → ${env.JOB_NAME}")
            } 
        }
    }  
	