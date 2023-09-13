SCRIPT_DIR="$(dirname "$(readlink -f "$0")")"

docker image build -t db-migration-demo-job "${SCRIPT_DIR}/../database"
docker tag db-migration-demo-job stranger/db-migration-demo-job:1.1.2
docker push stranger/db-migration-demo-job:1.1.2
