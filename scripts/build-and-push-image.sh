SCRIPT_DIR="$(dirname "$(readlink -f "$0")")"
"${SCRIPT_DIR}/../mvnw" clean package

EXTRACTED="${SCRIPT_DIR}/../target/extracted"
mkdir "${EXTRACTED}"
java -Djarmode=layertools -jar "${SCRIPT_DIR}/../target/db-migration-demo-app.jar" extract --destination "${EXTRACTED}"

docker image build -t db-migration-demo-app "${SCRIPT_DIR}/../"
docker tag db-migration-demo-app stranger/db-migration-demo-app:1.0
docker push stranger/db-migration-demo-app:1.0
