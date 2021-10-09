pipeline{
agent{
label "node-2004"
}  
environment{
mvnHome = tool name: 'maven'
}
stages {
	    // reference to maven
	    // ** NOTE: This 'maven-3.5.2' Maven tool must be configured in the Jenkins Global Configuration.   
	    stage('Clone Repo') { 
	     steps {
	      git 'https://github.com/codeboyatwork/spanner.git'              
	     }	      
	    } 
	    stage('Run Spanner Tests') {
	      steps {
	      	sh "'${mvnHome}/bin/mvn' clean test"
	      }    
	    }
}
}