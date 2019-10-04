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
			 
			stage('Build Frontend'){
				parallel{
					stage('DEV Environment') { 
			   			when{
							branch 'develop'
						}
			   			steps { 
							dir('SVE-Frontend'){
								sh 'npm install && npm run buildprod'
							}		
						}
					}
 
					stage('Default Environment') { 
						when{
							not{
								branch 'develop'
							}
						}
			   			steps { 
							dir('SVE-Frontend'){
								sh 'npm install && npm run buildprod'
							}
						}
					}
				}
			}
 
			stage('Build Backend') { 
			   steps { 
					sh 'gradle --b ./SVE-Backend/core/build.gradle clean'
					sh 'gradle --b ./SVE-Backend/core/build.gradle build'
					sh 'gradle --b ./SVE-Backend/core/build.gradle jar'
					sh 'gradle --b ./SVE-Backend/core/build.gradle moverLibreriasADockerLib'
				}
			}
 
			stage('Unit Test & Coverage Backend') {
				steps {
				    sh 'gradle --b ./SVE-Backend/core/build.gradle test'
				    sh 'gradle --b ./SVE-Backend/core/build.gradle jacocoTestReport'
				}
			} 
			
			stage('Static code revision SonarQube') {
				when{
					anyOf{
						branch 'develop'
						branch 'master'
						branch 'fargate'
					}
				}
				steps{
					withSonarQubeEnv('Sonar') {
						sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dsonar.projectKey=co.com.suramericana:sve.${BRANCH_NAME} -Dsonar.projectName=Sura-SVE-${BRANCH_NAME} -Dproject.settings=sonar-project.properties"
					}
				}
			}
			
			stage('Publish Artifactory') {
                when {
                    anyOf{
						branch 'develop'
					}
					expression { params.Publish }
                }
                steps{
                    sh 'gradle --b ./SVE-Backend/core/build.gradle createPom'
					sh 'gradle --b ./SVE-Backend/core/build.gradle generateArtifactoryProperties'
					load "${WORKSPACE}/artifactory.properties"
					script{
				        def server = Artifactory.server 'ar7if4c70ry@c318a'
				        def uploadSpec = 
						"""{"files": [{
				        "pattern": "SVE-Backend/core/build/libs/*",
				        "target": "${target}/${version}/"
				        }]}"""
				        def buildInfo = server.upload(uploadSpec)
				        server.publishBuildInfo(buildInfo)

			        }
			    }          
		    }

			stage('Build image Dllo') {
				 when {
					 anyOf{
						 branch 'develop'
					}					
                }
				steps{
					sh 'docker build -t sve-cocacola_dllo/engine .'
					sh 'docker tag sve-cocacola_dllo/engine:latest registry.ceiba.com.co/sve/sve-cocacola_dllo/engine:latest'					
				}
			}

			stage('Push image Dllo to registry') {
				 when {
					 anyOf{
						 branch 'develop'
					}					
                }
				steps{
					withDockerRegistry([ credentialsId: 'dockerHarborSVE', url: 'http://registry.ceiba.com.co/sve' ]) {
						sh 'docker push registry.ceiba.com.co/sve/sve-cocacola_dllo/engine:latest'						
					}
				}
			}

			stage('Deploy Dllo in to serve') {
                when {
                    anyOf{
						branch 'develop'
					}					
                }
                steps{
                    sshPublisher(
						publishers: [
							sshPublisherDesc(
								configName:'SVEDllo', 
									transfers: [
										sshTransfer(
											execCommand: 'sh /home/sve/run_docker_sve_dllo.sh'											
										)
									], 
									usePromotionTimestamp: false, 
									useWorkspaceInPromotion: false, 
									verbose: false
							)
						]
					)
			    }          
		    }


			stage('Build image Fargate') {
				 when {
					 anyOf{
						 branch 'fargate'
					}
					expression { params.PublishImageFargateRegistry }					
                }
				steps{
					sh 'docker build -t sve-cocacola_lab/engine .'
					sh 'docker tag sve-cocacola_lab/engine:latest registry.ceiba.com.co/sve/sve-cocacola_lab/engine:latest'					
				}
			}

			stage('Push image Fargate to registry') {
				 when {
					 anyOf{
						 branch 'fargate'
					}
					expression { params.PublishImageFargateRegistry }					
                }
				steps{
					withDockerRegistry([ credentialsId: 'dockerHarborSVE', url: 'http://registry.ceiba.com.co/sve' ]) {
						sh 'docker push registry.ceiba.com.co/sve/sve-cocacola_lab/engine:latest'						
					}
				}
			}

			stage('Deploy fargate in to serve') {
                when {
                    anyOf{
						branch 'fargate'
					}
					expression { params.PublishImageFargateRegistry }					
                }
                steps{
                    sshPublisher(
						publishers: [
							sshPublisherDesc(
								configName:'SVEDllo', 
									transfers: [
										sshTransfer(
											execCommand: 'sh /home/sve/run_docker_sve_lab.sh'											
										)
									], 
									usePromotionTimestamp: false, 
									useWorkspaceInPromotion: false, 
									verbose: false
							)
						]
					)
			    }          
		    }

			/*stage('Deploy') {
                when {
                    anyOf{
						branch 'develop'
					}
					expression { params.Publish }
                }
                steps{
                    sshPublisher(
						publishers: [
							sshPublisherDesc(
								configName:'SVEDllo', 
									transfers: [
										sshTransfer(
											cleanRemote: 
												false, 
												excludes: '', 
												execCommand: 'sh /home/sve/run_sve.sh', 
												execTimeout: 250000, 
												flatten: false, 
												makeEmptyDirs: false, 
												noDefaultExcludes: false, 
												patternSeparator: '[, ]+', 
												remoteDirectory: '', 
												remoteDirectorySDF: false, 
												removePrefix: '', 
												sourceFiles: 'SVE-Backend/core/build/libs/*.jar'
										)
									], 
									usePromotionTimestamp: false, 
									useWorkspaceInPromotion: false, 
									verbose: false
							)
						]
					)
			    }          
		    }*/
			
		}
    
		post { 
			failure { 
				mail( to: 'jhon.zapata@ceiba.com.co ,
					body: "Build failed in Jenkins: Project: ${env.JOB_NAME} Build /n Number: ${env.BUILD_NUMBER} URL de build: ${env.BUILD_NUMBER}/n/n Please go to ${env.BUILD_URL} and verify the build", 
					subject: "ERROR CI: SVE - ’ ${env.JOB_NAME}")
            } 
        }
    }  