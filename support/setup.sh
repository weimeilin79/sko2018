#minishift config set memory 8384
#minishift config set cpus 4
#minishift config set disk-size 30g



oc login -u system:admin
oc project openshift
oc create -f fis-image-streams.json
oc create -f https://raw.githubusercontent.com/strimzi/strimzi/0.1.0/kafka-inmemory/resources/openshift-template.yaml -n openshift

############################################################################################

oc login -u developer

echo "Create new projects"
oc new-project sko

echo "Start up AMQ Streaming"
oc new-app strimzi


echo "Start up MySQL for database access"
oc create -f https://raw.githubusercontent.com/openshift/origin/master/examples/db-templates/mysql-ephemeral-template.json
oc new-app --template=mysql-ephemeral --param=MYSQL_PASSWORD=password --param=MYSQL_USER=dbuser --param=MYSQL_DATABASE=sampledb

#TODO MANUAL STEP!!!!
echo "Setup mysqlDB"
#oc get pods
#oc rsync db-mysql <mysql_pod_name>:/var/lib/mysql/data
#oc rsh <mysql_pod>
#cd /var/lib/mysql/data/db-mysql
#mysql sampledb --user=dbuser --password 
# <ENTER PASSEORD>
# source schema.sql


echo "Set up Nodejs template"
oc create -f nodejs.json 


echo "Start up POSTGRESQL for database access"
oc create -f https://raw.githubusercontent.com/openshift/openshift-ansible/master/roles/openshift_examples/files/examples/v1.3/db-templates/postgresql-ephemeral-template.json
oc new-app --template=postgresql-ephemeral --param=POSTGRESQL_USER=dbuser --param=POSTGRESQL_PASSWORD=password --param=POSTGRESQL_DATABASE=sampledb


#TODO MANUAL STEP!!!!
echo "Setup postgresqkDB"
#oc get pods
#oc rsync db-postgresql <postgresql_pod_name>:/var/lib/pgsql/data/userdata
#oc rsh <mysql_pod>
#cd /var/lib/pgsql/data/userdata/db-postgresql/
#psql -U dbuser -d sampledb -a -f schema.sql



#Create Transform Camel
echo "Create Transform Camel"
oc create -f transform-camel.yml
oc new-app transform-camel

############################################################################################

#SEAT Listener
echo "Install SEAT Listener "
oc create -f seat-ui-listener.yml
oc new-app seat-ui-listener

#SEAT Reader
echo "Install SEAT Reader "
oc create -f seat-ui-reader.yml
oc new-app seat-ui-reader

# SEAT UI
echo "Install SEAT UI "
oc new-app --template=nodejs-example --param=NAME=seat-ui --param=SOURCE_REPOSITORY_URL=https://github.com/weimeilin79/sko2018.git --param=CONTEXT_DIR=seat-ui --param=SOURCE_REPOSITORY_REF=master


############################################################################################

echo "Install Registration Form "
#Registration Form
oc create -f registration-ui.json
oc new-app fuse-eap

echo "Install Registration Monitor UI "
oc new-app --template=nodejs-example --param=NAME=registration-live-ui --param=SOURCE_REPOSITORY_URL=https://github.com/weimeilin79/sko2018.git --param=CONTEXT_DIR=registration-live-ui --param=SOURCE_REPOSITORY_REF=master

#Registration Command Center
echo "Install Registration Command Center "
oc create -f registration-command.yml
oc new-app registration-command

############################################################################################
#Analytic
echo "Install Analytic Listener"
oc create -f analytic-listener.yml
oc new-app analytic-listener

echo "Install Analytic UI"
oc new-app --template=nodejs-example --param=NAME=analytic-ui --param=SOURCE_REPOSITORY_URL=https://github.com/weimeilin79/sko2018.git --param=CONTEXT_DIR=analytic-ui --param=SOURCE_REPOSITORY_REF=master


############################################################################################

#Simulator
echo "Install Simulator"
oc create -f seat-reserve-simulator.yml
oc new-app seat-reserve-simulator











