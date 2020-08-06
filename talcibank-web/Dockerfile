FROM centos

RUN yum install -y java-11-openjdk-devel

VOLUME /tmp
ADD /talcibank--web-0.0.2-SNAPSHOT.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.edg=file:/dev/./urandom", "-jar", "/myapp.j>

