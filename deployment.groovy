node {
   def mvnHome
   
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/Wassim-Kallel/BANK-ACCOUNT.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'M3'
      
   }
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "cd bank-account && '${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   
 
      stage('deploy artifacts') {
      // Run the maven build
      if (isUnix()) {
         sh "cd bank-account && '${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package deploy"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
  stage('Docker Build') {
    
     sh ' docker build -t app/tomcat:latest .'
      
    }
stage('Docker Running') {
     sh ' docker stop app0 || true && docker rm app0 || true'
     sh ' docker run -id --network=bridge --name=app0 app/tomcat:latest'
     sh ' docker start app0'
     }
     
stage('Docker Push') {
      
          sh "docker login -u admin -p admin123 ip_nexus:8082"
          sh 'docker tag app/tomcat:latest ip_nexus:8082/apptomcat:latest '
          sh 'docker push apptomcat:latest'
      
    }
}
  
