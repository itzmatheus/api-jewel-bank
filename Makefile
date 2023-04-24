install:
	chmod +x mvnw
	./mvnw clean package -Dmaven.test.skip=true

test: install
	./mvnw verify

run: install
	java -jar target/kotlin-*.jar

docker-up:
	docker-compose up -d

docker-down:
	docker-compose down