name := "test"
 
version := "1.0" 
      
lazy val `test` = (project in file(".")).enablePlugins(PlayJava)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
scalaVersion := "2.11.11"

libraryDependencies ++= Seq( javaJdbc , javaWs )
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "3.0.0"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      