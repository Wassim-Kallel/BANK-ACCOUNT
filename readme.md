
  mvn spring-boot:run
  
 
 http://localhost:8080/swagger-ui.html 

## configure your classpath 
vi ~/.profile 
MINISHIFT=$INSTALL_ROOT_MINISHIFT/minishift 
OC_HOME=$INSTALL_ROOT_OC
export PATH=$MINISHIFT:$OC_HOME:$PATH 
## start minishift 
 minishift start
## create project in openshift 
oc new-project formation --display-name="Formation" --description="Formation"
## delete project in openshift ###
oc delete project formation 
## viewing project in openshift 
oc get projects 
oc project formation
## checking project status 
oc status
eval $(minishift oc-env) 
eval $(minishift docker-env) 

# deployment in openshift

oc login -u developer -p developer 
## authenticate to openshift registery 
docker login -u developer -p $(oc whoami -t) $(minishift openshift registry)
## build images docker 
docker build -t tutorialbank -f  .
docker images
## tag,push container for deployment app 
docker tag tutorialbank $(minishift openshift registry)/formation/tutorialbank
docker push $(minishift openshift registry)/formation/tutorialbank
## pull mysql container image
docker pull openshift/mysql-56-centos7
##create a new pod for mysql service
oc new-app -e MYSQL_USER=root -e MYSQL_PASSWORD=azerty123$ -e MYSQL_DATABASE=sampledb openshift/mysql-56-centos7
oc get pods
## connect to ssh mysql
oc rsh mysql-56-centos7-1-nvth9
## update authenticate root user
sh-4.2$ mysql -u root
CREATE USER 'root'@'%' IDENTIFIED BY 'azerty123$';
Query OK, 0 rows affected (0.00 sec)
 
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
Query OK, 0 rows affected (0.00 sec)
 
FLUSH PRIVILEGES;
Query OK, 0 rows affected (0.00 sec)
 
exit
oc get svc
oc new-app -e spring_datasource_url=jdbc:mysql://172.30.123.55:3306/sampledb -e spring_datasource_username=root -e spring_datasource_password=azerty123$ --image-stream=tutorialbank
## view all pods in project
oc get pods
## view logs 
oc logs -f tutorialbank
## view all exposed services
oc get svc
oc expose svc tutorialbank
oc get route
