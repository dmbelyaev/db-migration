SCRIPT_DIR="$(dirname "$(readlink -f "$0")")"
"${SCRIPT_DIR}/../mvnw" clean package

EXTRACTED="${SCRIPT_DIR}/../app/target/extracted"
mkdir "${EXTRACTED}"
java -Djarmode=layertools -jar "${SCRIPT_DIR}/../app/target/db-migration-demo-app.jar" extract --destination "${EXTRACTED}"

docker image build -t db-migration-demo-app "${SCRIPT_DIR}/../app"
docker tag db-migration-demo-app stranger/db-migration-demo-app:1.1.2
docker push stranger/db-migration-demo-app:1.1.2
