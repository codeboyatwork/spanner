pipeline{
agent{
label "node-2004"
}  
environment{
mvnHome = tool name: 'maven'
gsdk = tool 'googlesdk-agent'
}
stages {
	    // reference to maven
	    // ** NOTE: This 'maven-3.5.2' Maven tool must be configured in the Jenkins Global Configuration.   
	    stage('Clone Repo') { 
	     steps {
	      git 'https://github.com/codeboyatwork/spanner.git'              
	     }	      
	    } 
        stage("Init GCP"){
         steps{
            withCredentials([file(credentialsId: 'gcloud-spanner', variable: 'gcs')]) {
                sh "'${gsdk}/gcloud' auth activate-service-account --key-file '${gcs}'"
                sh "export GOOGLE_APPLICATION_CREDENTIALS='${gcs}'"
                sh "'${mvnHome}/bin/mvn' clean test"
                }
            }
        }
	    stage('Run Spanner Tests') {
	      steps {
	      	sh "'${mvnHome}/bin/mvn' clean test"
	      }    
	    }
}
}