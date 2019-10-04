pipeline{
 
		agent {
			label 'Slave5'
		}
    
		parameters {
  			booleanParam defaultValue: false, description: 'Publish the JAR to Ceiba Artifactory', name: 'Publish'
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
			gradle 'Gradle4.9_Centos' 
		}
		
		environment {
			JAVA_TOOL_OPTIONS='-Dfile.encoding=UTF8'
			LANG='en_GB UTF-8'			
		}
 
		stages {
			
			stage('Checkout Git'){
				steps{
				   checkout([$class: 'GitSCM', branches: [[name: '${BRANCH_NAME}']], doGenerateSubmoduleConfigurations: false, extensions: [], gitTool: 'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'GitHub_alexanderzapata27', url: 'https://github.com/alexanderzapata27/RetoTL.git']]])
				}
			}

 
			stage('Build Backend') { 
			   steps { 
					sh 'gradle --b ./Reto-Back/infrastructure/build.gradle clean'
					sh 'gradle --b ./Reto-Back/infrastructure/build.gradle build'
					sh 'gradle --b ./Reto-Back/infrastructure/build.gradle jar'
				}
			}
 
			stage('Unit Test & Coverage Backend') {
				steps {
				    sh 'gradle --b ./Reto-Back/infrastructure/build.gradle test'
				    sh 'gradle --b ./Reto-Back/infrastructure/build.gradle jacocoTestReport'
				}
			} 
			
			stage('Static code revision SonarQube') {
				when{
					anyOf{
						branch 'develop'
						branch 'master'
					}
				}
				steps{
					withSonarQubeEnv('Sonar') {
						sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dsonar.projectKey=co.com.ceiba:retotl.${BRANCH_NAME} -Dsonar.projectName=RetoTL-${BRANCH_NAME} -Dproject.settings=sonar-project.properties"
					}
				}
			}
    
		post { 
			failure { 
				mail( to: 'jhon.zapata@ceiba.com.co ,
					body: "Build failed in Jenkins: Project: ${env.JOB_NAME} Build /n Number: ${env.BUILD_NUMBER} URL de build: ${env.BUILD_NUMBER}/n/n Please go to ${env.BUILD_URL} and verify the build", 
					subject: "ERROR CI: SVE - ’ ${env.JOB_NAME}")
            } 
        }
    }  