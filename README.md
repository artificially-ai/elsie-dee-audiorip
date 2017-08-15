# elsie-dee-audiorip
Elsie-Dee microservice used to rip audio out of video files.

# Dependencies

* Configuration Service
  * This microservice depends on the Configuration Service in order to retrieve its settings. It means that before starting this server,
    please make sure that the one it depends on is already running.
  * You can find out how to run the Configuration Service here: [Configuration Service](https://github.com/ekholabs/configuration-service)
* Eureka Service
  * As a second note, this microservice also dependes on the Eureka Service in order to register for service discovery. However,
    the Eureka Service does not need to be running before this one can be started.
  * You can find out how to run the Configuration Service here: [Eureka Service](https://github.com/ekholabs/eureka-service)

In a dependency order priority, the Eureka Service should be started before everything else. The second in the list must be the Configuration Service.

# Pulling the Docker Image

* ```docker pull ekholabs/elsie-dee-audiorip```

# Running the Docker Container

* ```docker run -d -p 8086:8086 --link configuration-service --link eureka-service --name=elsie-dee-audiorip ekholabs/elsie-dee-audiorip```

Elsie-Dee Audio Rip will run on the background. To check details about the container, execute the following:

* ```docker ps```

For logs:

* ```docker logs [container_id]```

# Actuator Endpoints

Once the application is running, the user/developer can find health status and metrics via the following endpoints:

* http://localhost:8086/elsie-dee-audiorip/health
* http://localhost:8086/elsie-dee-audiorip/metrics
* http://localhost:8086/elsie-dee-audiorip/env

# Processing Video Files

In order to process video files, one can use the ```/ripAudio``` endpoint with cURL.

## cURL

* ```curl -v -F video=@[mp4_file] http://localhost:8086/elsie-dee-audiorip/ripAudio > audio.wav```
