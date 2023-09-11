SCRIPT_DIR="$(dirname "$(readlink -f "$0")")"
kubectl delete -f $SCRIPT_DIR/../k8s/app-deployment.yaml