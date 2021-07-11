Project version: 2021-03-23.

Example usage using the provided test files:

  cd <project-root>
  mvn clean package
  java -jar target/SessionsJob-1.0.jar 'src/test/resources/input-statements.psv' 'target/actual-sessions.psv'
  
  
  
  java -jar target/SessionsJob-1.0.jar --->"defaults: <src/test/resources/input-statements.psv> <src/test/resources/actual-sessions.psv>"
