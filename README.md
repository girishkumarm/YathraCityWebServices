# BrainBlox Webservices

> This application is responsible for serving clients of BrainBlox as this is the primary contact point for any BrainBlox client.
> The webservices are RESTful. When the project is built, it generates a warfile and the warfile can be placed into a webcontainer (Tomcat, etc,.) to start serving the clients.

### Setting up:
You will need to configure maven so that it points to custom reposities of Razorthink.
 - Copy the **settings.xml** (found in the root directory of the codebase) into **~/.m2/** (can be found in user's home directory)
 - Copy the **rzt-mvn-repo.crt** into **$JAVA_HOME/jre/lib/security** and **$JRE_HOME/lib/security** directories.
 - Execute the following commands to add .crt to Java keystore:
```sh
$JAVA_HOME/jre/bin/keytool -import -keystore $JAVA_HOME/jre/lib/security/cacerts -file $JAVA_HOME/jre/lib/security/rzt-mvn-repo.crt
```
```sh
$JRE_HOME/bin/keytool -import -keystore $JRE_HOME/lib/security/cacerts -file $JRE_HOME/lib/security/rzt-mvn-repo.crt
```
 - When prompted for password, type **changeit** and hit return.
 - A certificate fingerprint information is shown and it prompts for accepting the certificate. Type **yes** and hit enter. The certificate is now added to keystore.

### Clone project from Github:
Issue the following commands to clone the Git project locally:
```sh
$ mkdir <directory>
$ cd <directory>
$ git init
$ git remote add origin git@github.com:razorthink-inblox/BrainBloxWebServices.git
$ git pull origin master
```
### Building the project:
This builds the project and generates a warfile in the end.
```sh
mvn clean package install -Denv.type=<environment-type> -Denv.warDestination=<destination-path-for-warfile>
```
For example, you can build for local environment from the following command:
```sh
mvn clean package install -Denv.type=stg_internal -Denv.warDestination=/Users/username/
```


### Script for deployment:

```sh
ENV=<build environment>
SVN_CODEBASE_URL= https://github.com/razorthink-inblox/BrainBloxWebServices/trunk
SVN_USERNAME=<svn username>
SVN_PASSWORD=<svn password>
CODEBASE_ROOT=/path/to/codebase/$ENV
CODEBASE_ABSOLUTE=$CODEBASE_ROOT/<build version>
BUILD_ENV=$ENV

mkdir -p $CODEBASE_ABSOLUTE
echo "[Build environment: $BUILD_ENV]"
echo "Checking out/updating code from $SVN_CODEBASE_URL..."

svn co $SVN_CODEBASE_URL $CODEBASE_ABSOLUTE --username $SVN_USERNAME --password $SVN_PASSWORD

#cleanup codebase
svn cleanup $CODEBASE_ABSOLUTE --username $SVN_USERNAME --password $SVN_PASSWORD

#update code
svn update $CODEBASE_ABSOLUTE --username $SVN_USERNAME --password $SVN_PASSWORD

#change perms recursively
chmod -R 777 $CODEBASE_ROOT

#Build webapp
mvn -f $CODEBASE_ABSOLUTE/pom.xml clean package install -Denv.type=$BUILD_ENV -Denv.warDestination=$CODEBASE_ABSOLUTE/<webapp directory>

```
